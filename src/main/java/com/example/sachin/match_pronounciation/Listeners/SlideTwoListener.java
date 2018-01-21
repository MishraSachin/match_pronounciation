package com.example.sachin.match_pronounciation.Listeners;

/**
 * Created by home on 20-Jan-18.
 */

public interface SlideTwoListener {

    interface SlideTwoView{
        void showMatchPercentage(int per);
    }

    interface SlideTwoPresenter{
        void matchStrings();
        void matchPercentage(int per);
    }

    interface SlideTwoInteractor{
        void calculatePercentageOfMatch(SlideTwoPresenter slideTwoPresenter, String s1, String s2);
    }
}
