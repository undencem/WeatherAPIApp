package com.example.weatherapiapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Singleton example
 */
public class MySingleton {
    private static MySingleton instance;
    private RequestQueue requestQueue;
    private static Context ctx;


    /**
     * getRequestQueue() method
     * is called
     * @param context
     */
    private MySingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    /**
     * Only one instance of the class is instantiated
     * @param context
     * @return
     */
    public static synchronized MySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new MySingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            /** getApplicationContext() is key, it keeps you from leaking the
             *  Activity or BroadcastReceiver if someone passes one in.
             */
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}