package tw.edu.ncnu.csie.universitydatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private RequestQueue mQueue;
    private String mUrl;
    private TextView msg;
    private JsonArrayRequest getRequest;
    private String jsonResponse;
    private Button mGetMsgButton;
    ArrayList<CompoundButton> selected=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msg = (TextView) findViewById(R.id.txv);
        mGetMsgButton = (Button) findViewById(R.id.button);
        int[] chk_id = {R.id.chk1, R.id.chk2, R.id.chk3, R.id.chk4,
                R.id.chk5,R.id.chk6,R.id.chk7,R.id.chk8,R.id.chk9,R.id.chk10};
        for (int id : chk_id) {
            CheckBox chk = (CheckBox) findViewById(id);
            chk.setOnCheckedChangeListener(this);
        }
        mQueue = Volley.newRequestQueue(this);
        mGetMsgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonResponse = "";
                msg.setText(jsonResponse);
                for (CompoundButton chk : selected) {
                    if(chk.getId()==R.id.chk1) {
                        mUrl = "http://163.22.17.158/classtype1.php";
                    }
                    if(chk.getId()==R.id.chk2){
                        mUrl = "http://163.22.17.158/classtype2.php";
                    }

                    if(chk.getId()==R.id.chk3){
                        mUrl = "http://163.22.17.158/classtype3.php";
                    }

                    if(chk.getId()==R.id.chk4){
                        mUrl = "http://163.22.17.158/classtype4.php";
                    }

                    if(chk.getId()==R.id.chk5){
                        mUrl = "http://163.22.17.158/classtype5.php";
                    }

                    if(chk.getId()==R.id.chk6){
                        mUrl = "http://163.22.17.158/classtype6.php";
                    }

                    if(chk.getId()==R.id.chk7){
                        mUrl = "http://163.22.17.158/classtype7.php";
                    }

                    if(chk.getId()==R.id.chk8){
                        mUrl = "http://163.22.17.158/classtype8.php";
                    }

                    if(chk.getId()==R.id.chk9){
                        mUrl = "http://163.22.17.158/classtype9.php";
                    }

                    if(chk.getId()==R.id.chk10){
                        mUrl = "http://163.22.17.158/classtype10.php";
                    }
                    getRequest = new JsonArrayRequest(mUrl,
                            new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    try {
                                        // Parsing json array response
                                        // loop through each json object
                                        for (int i = 0; i < response.length(); i++) {
                                            JSONObject person = (JSONObject) response.get(i);
                                            String oid = person.getString("oid");
                                            String schoolname = person.getString("schoolname");
                                            String foundingtime = person.getString("foundingtime");
                                            String recalibrationupgradedtime = person.getString("recalibrationupgradedtime");
                                            String address = person.getString("address");
                                            String schoolground = person.getString("schoolground");

                                            jsonResponse += oid + ". ";
                                            jsonResponse += "\t學校名稱： " + schoolname + "\n";
                                            jsonResponse += "\t\t" + "創校時間： " + foundingtime + "\n";
                                            jsonResponse += "\t\t" + "復校/升格時間： " + recalibrationupgradedtime + "\n";
                                            jsonResponse += "\t\t" + "學校地址： " + address + "\n";
                                            jsonResponse += "\t\t" + "校地： " + schoolground + "\n\n";
                                        }
                                        msg.setText(jsonResponse);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(getApplicationContext(),
                                                "Error: " + e.getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    msg.setText(volleyError.getMessage());
                                }
                            });

                    mQueue.add(getRequest);

                }
            }
        });

    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)
            selected.add(buttonView);
        else
            selected.remove(buttonView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
