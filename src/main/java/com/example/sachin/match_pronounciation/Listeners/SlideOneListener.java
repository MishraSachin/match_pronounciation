package com.example.sachin.match_pronounciation.Listeners;

import android.content.Context;

import com.example.sachin.match_pronounciation.pojo.LessonPojo;

import java.util.List;

/**
 * Created by Sachin on 19-01-2018.
 */

public interface SlideOneListener {

    interface SlideOneView {
        void showProgress();

        void hideProgress();

        void playAudio(List<LessonPojo> list);

        void showError();
    }

    interface SlideOnePresenter {

        void loadData();

        void onResponse(List<LessonPojo> list);

        void onError();
    }

    interface SlideOneInteractor {

        void apiHandler(Context context, SlideOnePresenter presenter, String url);
    }
}
