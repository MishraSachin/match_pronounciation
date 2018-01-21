package com.example.sachin.match_pronounciation.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sachin.match_pronounciation.Listeners.SlideTwoListener;
import com.example.sachin.match_pronounciation.R;
import com.example.sachin.match_pronounciation.model_layer.SlideTwoInteractorImpl;
import com.example.sachin.match_pronounciation.network.ApiConstants;
import com.example.sachin.match_pronounciation.presenter_layer.SlideTwoPresenterImpl;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by home on 20-Jan-18.
 */

public class SlideTwoActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener, View.OnClickListener, SlideTwoListener.SlideTwoView{

    private ImageView ivPlay, ivMic;
    private TextView tvEnglish;
    private MediaPlayer mp;
    private int playbackPosition;
    private String audioUrl, coceptName, pronounciation;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private SlideTwoPresenterImpl presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_two);
        xmlItemId();
        getIntentData();

        mp = new MediaPlayer();
        tvEnglish.setText(coceptName);
        playAudio();
        mp.setOnCompletionListener(this);
        ivPlay.setOnClickListener(this);
        ivMic.setOnClickListener(this);
    }

    private void xmlItemId() {
        ivPlay = (ImageView)findViewById(R.id.ivPlay);
        tvEnglish = (TextView) findViewById(R.id.tvEnglish1);
        ivMic = (ImageView)findViewById(R.id.ivMic);
    }

    private void getIntentData(){
        Intent intent = getIntent();
        audioUrl = intent.getStringExtra(ApiConstants.IntentConst.AUDIO);
        coceptName = intent.getStringExtra(ApiConstants.IntentConst.CONCEPT_NAME);
        pronounciation = intent.getStringExtra(ApiConstants.IntentConst.PRONOUNCIATION);
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        try {
            ivPlay.setImageResource(R.drawable.ic_volume_up);
            mp.stop();
            playbackPosition = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivPlay:
                if (mp != null && mp.isPlaying()) {
                    playbackPosition = mp.getCurrentPosition();
                    mp.pause();
                    ivPlay.setImageResource(R.drawable.ic_volume_up);
                } else if (mp != null && !mp.isPlaying()) {
                    if (playbackPosition != 0) {
                        mp.seekTo(playbackPosition);
                        mp.start();
                    } else {
                        playAudio();
                    }
                    ivPlay.setImageResource(R.drawable.ic_play);
                }
                break;
            case R.id.ivMic:
                askSpeechInput();
                break;
        }
    }

        private void playAudio() {
            try {
                mp.reset();
                mp.setDataSource(audioUrl);
                mp.prepare();
                mp.start();
                ivPlay.setImageResource(R.drawable.ic_play);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    private void askSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "speak and wait");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    Toast.makeText(this, result.get(0).toString(), Toast.LENGTH_SHORT).show();
                    presenter = new SlideTwoPresenterImpl(this, new SlideTwoInteractorImpl(), pronounciation.toLowerCase(), result.get(0).toString());
                }
                break;
            }
        }
    }

    @Override
    public void showMatchPercentage(int per) {
        Toast.makeText(SlideTwoActivity.this, "pronounciation match "+per+"%", Toast.LENGTH_LONG).show();
    }
}
