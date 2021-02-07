package com.example.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    
    Button btn_cityID,btn_WeatherByCity,btn_WeatherByCityName;
    EditText et_datainput;
    ListView lv_weatherReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign values to each control in the layout
        btn_cityID = (Button)findViewById(R.id.btn_cityID);
        btn_WeatherByCity=(Button)findViewById(R.id.btn_WeatherByCityID);
        btn_WeatherByCityName=(Button)findViewById(R.id.btn_WeatherByCityName);
        et_datainput = (EditText)findViewById(R.id.et_datainput);
        lv_weatherReport=(ListView)findViewById(R.id.lv_weatherReports);

        /**
         * click listeners for buttons
         *
         * MainActivity.this = getApplicationContext()
         *
         */
        btn_cityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: btn_cityID button: " );

                // Instantiate the RequestQueue.
                // RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url ="https://www.metaweather.com/api/location/search/?query="+et_datainput.getText().toString();

                JsonArrayRequest  request =
                        new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                    String cityID="";
                                    try{
                                        JSONObject cityInfo = response.getJSONObject(0);
                                        cityID=cityInfo.getString("woeid");
                                    }catch(JSONException e){
                                        e.printStackTrace();
                                    }
                                    Toast.makeText(getApplicationContext(),"City ID:"+cityID,Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),"Error occurred",Toast.LENGTH_LONG).show();
                            }
                        });

               MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);
            }
        });

       btn_WeatherByCity.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               Log.e(TAG, "onClick: btn_WeatherByCity" );
                Toast.makeText(getApplicationContext(),"You clicked 2",Toast.LENGTH_LONG).show();
           }
       });

       btn_WeatherByCityName.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.e(TAG, "onClick: btn_WeatherByCityName ");
               Toast.makeText(MainActivity.this,"You clicked 3",Toast.LENGTH_LONG).show();
           }
       });

    }
}