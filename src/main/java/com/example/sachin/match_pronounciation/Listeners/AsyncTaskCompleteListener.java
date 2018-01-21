package com.example.sachin.match_pronounciation.Listeners;

import android.content.Context;

import com.android.volley.VolleyError;

/**
 * Created by Sachin on 21-11-2017.
 */

public interface AsyncTaskCompleteListener {

    void onTaskCompleted(String response, Context context);
    void onError(int servicecode, VolleyError error);
}
