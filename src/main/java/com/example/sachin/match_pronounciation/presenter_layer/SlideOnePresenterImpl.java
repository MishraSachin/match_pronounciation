package com.example.sachin.match_pronounciation.presenter_layer;

import android.content.Context;

import com.example.sachin.match_pronounciation.Listeners.SlideOneListener;
import com.example.sachin.match_pronounciation.pojo.LessonPojo;

import java.util.List;

/**
 * Created by Sachin on 19-01-2018.
 */

public class SlideOnePresenterImpl implements SlideOneListener.SlideOnePresenter {

    private SlideOneListener.SlideOneView slideOneView;
    private SlideOneListener.SlideOneInteractor slideOneInteractor;
    private Context mContext;
    private String mUrl;

    public SlideOnePresenterImpl(SlideOneListener.SlideOneView slideOneView, SlideOneListener.SlideOneInteractor slideOneInteractor,
                                 Context context, String url) {

        this.slideOneView = slideOneView;
        this.slideOneInteractor = slideOneInteractor;
        this.mContext = context;
        this.mUrl = url;
        loadData();
    }

    @Override
    public void loadData() {

        slideOneInteractor.apiHandler(mContext, this, mUrl);
    }

    @Override
    public void onResponse(List<LessonPojo> list) {

        slideOneView.playAudio(list);
        slideOneView.hideProgress();
    }

    @Override
    public void onError() {

    }
}
