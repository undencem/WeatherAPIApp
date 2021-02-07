package com.example.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
                Toast.makeText(getApplicationContext(),"You clicked 1",Toast.LENGTH_LONG).show();
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