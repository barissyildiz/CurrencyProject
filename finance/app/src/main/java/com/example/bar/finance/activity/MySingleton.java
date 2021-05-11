package com.example.bar.finance.activity;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class MySingleton {

    public static MySingleton mInstance;
    Context context;
    RequestQueue requestQueue;

    public MySingleton(Context context) {

        this.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized MySingleton getInstance(Context context){
        // If Instance is null then initialize new Instance
        if(mInstance == null){
            mInstance = new MySingleton(context);
        }
        // Return MySingleton new Instance
        return mInstance;
    }


    public RequestQueue getRequestQueue() {

        if(requestQueue == null) {

            RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        }

        return requestQueue;
    }

    public<T> void addToRequestQueue(Request<T> request){
        // Add the specified request to the request queue
        getRequestQueue().add(request);
    }
}
