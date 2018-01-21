package com.example.sachin.match_pronounciation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.sachin.match_pronounciation.R;
import com.example.sachin.match_pronounciation.network.ApiConstants;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btLessonOne, btLessonTwo, btLessonThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xmlItemId();
        btLessonOne.setOnClickListener(this);
        btLessonTwo.setOnClickListener(this);
        btLessonThree.setOnClickListener(this);
    }

    private void xmlItemId() {

        btLessonOne = (Button)findViewById(R.id.lessonOneButton);
        btLessonTwo = (Button)findViewById(R.id.lessonTwoButton);
        btLessonThree = (Button)findViewById(R.id.lessonThreeButton);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lessonOneButton:
                Intent intent = new Intent(this, SlideOneActivity.class);
                intent.putExtra(ApiConstants.IntentConst.LEARN_INDEX, 0);
                intent.putExtra(ApiConstants.IntentConst.QUESTION_INDEX, 1);
                startActivity(intent);
                break;
            case R.id.lessonTwoButton:
                Intent intent1 = new Intent(this, SlideOneActivity.class);
                intent1.putExtra(ApiConstants.IntentConst.LEARN_INDEX, 2);
                intent1.putExtra(ApiConstants.IntentConst.QUESTION_INDEX, 3);
                startActivity(intent1);
                break;
            case R.id.lessonThreeButton:
                Intent intent2 = new Intent(this, SlideOneActivity.class);
                intent2.putExtra(ApiConstants.IntentConst.LEARN_INDEX, 4);
                intent2.putExtra(ApiConstants.IntentConst.QUESTION_INDEX, 5);
                startActivity(intent2);
                break;
        }
    }
}
