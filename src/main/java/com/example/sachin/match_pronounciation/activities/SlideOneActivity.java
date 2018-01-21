package com.example.sachin.match_pronounciation.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sachin.match_pronounciation.Listeners.SlideOneListener;
import com.example.sachin.match_pronounciation.R;
import com.example.sachin.match_pronounciation.model_layer.SlideOneInteractorImpl;
import com.example.sachin.match_pronounciation.network.ApiConstants;
import com.example.sachin.match_pronounciation.pojo.LessonPojo;
import com.example.sachin.match_pronounciation.presenter_layer.SlideOnePresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by home on 19-Jan-18.
 */

public class SlideOneActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener,
        SlideOneListener.SlideOneView {

    private ImageView ivPlay;
    private TextView tvPronounciation, tvConceptName;
    private Button btNext;
    private ProgressDialog progressBar;
    private SlideOnePresenterImpl mPresenter;
    private MediaPlayer mp;
    private int playbackPosition;
    private List<LessonPojo> list;
    private int learnIndex, questionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_one);
        xmlItemId();
        getIntentData();
        ivPlay.setImageResource(R.drawable.ic_pause);
        ivPlay.setOnClickListener(this);
        btNext.setOnClickListener(this);
        mp = new MediaPlayer();
        mp.setOnCompletionListener(this);
        list = new ArrayList<>();
        showProgress();
    }

    private void xmlItemId() {
        ivPlay = (ImageView) findViewById(R.id.ivPlayPause);
        tvPronounciation = (TextView)findViewById(R.id.tvKanada);
        tvConceptName = (TextView)findViewById(R.id.tvEnglish);
        btNext = (Button) findViewById(R.id.btNextButton);
    }

    private void getIntentData(){
        Intent intent = getIntent();
        learnIndex = intent.getIntExtra(ApiConstants.IntentConst.LEARN_INDEX, 0);
        questionIndex = intent.getIntExtra(ApiConstants.IntentConst.QUESTION_INDEX, 0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivPlayPause:

                 if (mp != null && mp.isPlaying()) {
                    playbackPosition = mp.getCurrentPosition();
                    mp.pause();
                    ivPlay.setImageResource(R.drawable.ic_pause);
                } else if (mp != null && !mp.isPlaying()) {
                     if(playbackPosition != 0){
                         mp.seekTo(playbackPosition);
                         mp.start();
                     }else{
                         playAudio();
                     }

                    ivPlay.setImageResource(R.drawable.ic_play);
                }
                break;
            case R.id.btNextButton:

                Intent intent = new Intent(this, SlideTwoActivity.class);
                intent.putExtra(ApiConstants.IntentConst.AUDIO, list.get(questionIndex).getAudioUrl());
                intent.putExtra(ApiConstants.IntentConst.CONCEPT_NAME, list.get(questionIndex).getConceptName());
                intent.putExtra(ApiConstants.IntentConst.PRONOUNCIATION, list.get(questionIndex).getPronounciation());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        try {
            ivPlay.setImageResource(R.drawable.ic_pause);
            mp.stop();
            playbackPosition = 0;
            //mp = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showProgress() {
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage("Loading");
        progressBar.show();
        mPresenter = new SlideOnePresenterImpl(SlideOneActivity.this, new SlideOneInteractorImpl(), this, ApiConstants.LESSON_URL);

    }

    @Override
    public void hideProgress() {

        progressBar.dismiss();
    }

    @Override
    public void playAudio(List<LessonPojo> list) {

        this.list = list;
        try{
            tvPronounciation.setText(list.get(learnIndex).getPronounciation());
            tvConceptName.setText(list.get(learnIndex).getConceptName());
        }catch (Exception ex){

        }
        playAudio();
    }

    private void playAudio() {
        try {
            mp.reset();
            mp.setDataSource(list.get(learnIndex).getAudioUrl());
            mp.prepare();
            mp.start();
            ivPlay.setImageResource(R.drawable.ic_play);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showError() {

    }
}

