package com.example.sachin.match_pronounciation.presenter_layer;

import com.example.sachin.match_pronounciation.Listeners.SlideTwoListener;

/**
 * Created by home on 20-Jan-18.
 */

public class SlideTwoPresenterImpl implements SlideTwoListener.SlideTwoPresenter {

    private SlideTwoListener.SlideTwoView slideTwoView;
    private SlideTwoListener.SlideTwoInteractor slideTwoInteractor;
    private String s1, s2;

    public SlideTwoPresenterImpl(SlideTwoListener.SlideTwoView slideTwoView, SlideTwoListener.SlideTwoInteractor
            slideTwoInteractor, String s1, String s2) {

        this.slideTwoView = slideTwoView;
        this.slideTwoInteractor = slideTwoInteractor;
        this.s1 = s1;
        this.s2 = s2;
        matchStrings();
    }

    @Override
    public void matchStrings() {
        slideTwoInteractor.calculatePercentageOfMatch(this, s1, s2);
    }

    @Override
    public void matchPercentage(int per) {

        slideTwoView.showMatchPercentage(per);
    }
}
