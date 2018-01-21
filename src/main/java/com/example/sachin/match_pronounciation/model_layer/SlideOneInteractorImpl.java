package com.example.sachin.match_pronounciation.model_layer;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.example.sachin.match_pronounciation.Listeners.AsyncTaskCompleteListener;
import com.example.sachin.match_pronounciation.Listeners.SlideOneListener;
import com.example.sachin.match_pronounciation.network.ApiConstants;
import com.example.sachin.match_pronounciation.network.VolleyRequester;
import com.example.sachin.match_pronounciation.pojo.LessonPojo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sachin on 19-01-2018.
 */

public class SlideOneInteractorImpl implements SlideOneListener.SlideOneInteractor, AsyncTaskCompleteListener {

    private String TAG = SlideOneInteractorImpl.class.getName();
    private SlideOneListener.SlideOnePresenter slideOnePresenter;
    private VolleyRequester volleyRequester;
    private List<LessonPojo> lessonPojoList;

    @Override
    public void apiHandler(Context context, SlideOneListener.SlideOnePresenter slideOnePresenter, String url) {

        this.slideOnePresenter = slideOnePresenter;
        lessonPojoList = new ArrayList<>();
        volleyRequester = new VolleyRequester(context, ApiConstants.GET, url, this);
    }

    @Override
    public void onTaskCompleted(String response, Context context) {
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("lesson_data");
            int length = jsonArray.length();

            for(int i=0; i<length; i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                String type = jsonObject1.getString("type");
                String conceptName = jsonObject1.getString("conceptName");
                String pronounciation = jsonObject1.getString("pronunciation");
                String targetScript = jsonObject1.getString("targetScript");
                String audio_url = jsonObject1.getString("audio_url");

                LessonPojo lessonPojo = new LessonPojo();
                lessonPojo.setType(type);
                lessonPojo.setConceptName(conceptName);
                lessonPojo.setPronounciation(pronounciation);
                lessonPojo.setTargetScript(targetScript);
                lessonPojo.setAudioUrl(audio_url);

                lessonPojoList.add(lessonPojo);
            }
            slideOnePresenter.onResponse(lessonPojoList);
        }catch (Exception ex){
            Log.w(TAG+" Exception->", ex.toString());
        }
    }

    @Override
    public void onError(int servicecode, VolleyError error) {
        Log.w(TAG+" onError->", error.toString()+" "+servicecode);
    }
}
