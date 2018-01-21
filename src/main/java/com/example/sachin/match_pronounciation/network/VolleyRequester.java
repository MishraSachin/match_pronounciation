package com.example.sachin.match_pronounciation.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sachin.match_pronounciation.Listeners.AsyncTaskCompleteListener;
import com.example.sachin.match_pronounciation.app.AppController;

/**
 * Created by Sachin on 21-11-2017.
 */

public class VolleyRequester {

    private String TAG = VolleyRequester.this.getClass().getName();
    private AsyncTaskCompleteListener asyncTaskCompleteListener;
    private Context mContext;

    public VolleyRequester(Context context, int method_type, String URL, AsyncTaskCompleteListener
            asyncTaskCompleteListener) {
        this.mContext = context;
        this.asyncTaskCompleteListener = asyncTaskCompleteListener;
        if (method_type == Request.Method.GET) {

            volley_stringRequestGet(method_type, URL);
            //Log.e(TAG+" URL-->", URL);
        } else if (method_type == Request.Method.POST) {
        }

    }


    public void volley_stringRequestGet(int method, String url) {

        StringRequest stringRequest = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.w(TAG+" RESPONSE-->", response);
                asyncTaskCompleteListener.onTaskCompleted(response, mContext);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w(TAG+" error", error.toString());
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

}
