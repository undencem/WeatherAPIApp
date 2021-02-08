package com.example.weatherapiapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class WeatherDataService {

    public static final String QUERY_FOR_CITY_ID = "https://www.metaweather.com/api/location/search/?query=";

    Context context;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public String getCityID(String cityName){

        // Instantiate the RequestQueue.
        // RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url =QUERY_FOR_CITY_ID+cityName;

        JsonArrayRequest request =
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
                        Toast.makeText(context,"City ID:"+cityID,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,"Error occurred",Toast.LENGTH_LONG).show();
                    }
                });

        MySingleton.getInstance(context).addToRequestQueue(request);

        return null;
    };

    public List<WeatherReportModel> getCityForecastByID(String cityID){
        return null;
    };

    public List<WeatherReportModel> getCityForecastByName(String cityName){
        return null;
    };

}
