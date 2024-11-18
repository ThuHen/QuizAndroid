package com.example.qiuz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER = "thuhen.quiz.answer";
    private static final String EXTRA_ANSWER_RESULT = "thuhen.quiz.answer_result";
    private Button buttonAnswer;
    private TextView txtAnswer;
    private boolean mAnswer;// dap an cau hoi

    // ham tao 1 intent có chưa cặp du lieu key-value
    public static Intent newIntent(Context pakageContext, boolean answer) {
        Intent i = newIntent(pakageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER, answer);
        return i;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        buttonAnswer = findViewById(R.id.btn_show_answer);
        txtAnswer = findViewById(R.id.text_view_cheat);
        buttonAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(mAnswer)
                   txtAnswer.setText(R.string.answer_correct);
               else
                   txtAnswer.setText(R.string.answer_incorrect);
               setAnswerResult(true);
            }
        });

        mAnswer = getIntent().getBooleanExtra(EXTRA_ANSWER, false);//lay du lieu value tu intent extra


    }

    private void setAnswerResult(boolean isAnswerShow) {
        Intent data= new Intent();
        data.putExtra(EXTRA_ANSWER_RESULT,isAnswerShow);
        setResult(RESULT_OK,data);
    }
    public static boolean getAnswerShow(Intent intent){
        return intent.getBooleanExtra(EXTRA_ANSWER_RESULT,false);
    }
}