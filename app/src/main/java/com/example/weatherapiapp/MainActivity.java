package com.example.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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

import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private static final String TAG = "MainActivity";
    
    Button btn_cityID,btn_getWeatherByID,btn_WeatherByCityName;
    EditText et_datainput;
    ListView lv_weatherReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //assign values to each control in the layout
        btn_cityID = (Button)findViewById(R.id.btn_cityID);
        btn_getWeatherByID=(Button)findViewById(R.id.btn_WeatherByCityID);
        btn_WeatherByCityName=(Button)findViewById(R.id.btn_WeatherByCityName);
        et_datainput = (EditText)findViewById(R.id.et_datainput);
        lv_weatherReport=(ListView)findViewById(R.id.lv_weatherReports);

        /**
         * click listeners for buttons
         *
         * MainActivity.this = getApplicationContext()
         *
         */

        final WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);

        btn_cityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Didn't return anything
                 weatherDataService.getCityID(et_datainput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this,"Error occurred",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String cityID) {
                        Log.d(TAG, "onResponse: getCityID ");
                        Toast.makeText(MainActivity.this,"Returned as ID of:"+cityID,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


       btn_getWeatherByID.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {

               weatherDataService.getCityForecastByID(et_datainput.getText().toString(), new WeatherDataService.ForeCastByIDResponse() {

                   @Override
                   public void onError(String message) {
                       Log.e(TAG, "onError: getCityForecastByID in MainActivity");
                       Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                   }

                   @Override
                   public void onResponse(List<WeatherReportModel> weatherReportModel) {

                       // put the entire list into ListView control
                       Log.d(TAG, "onResponse: getWeatherByID");
                       ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,weatherReportModel);

                       // assign arrayAdapter to ListView
                       lv_weatherReport.setAdapter(arrayAdapter);
                   }
               });
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