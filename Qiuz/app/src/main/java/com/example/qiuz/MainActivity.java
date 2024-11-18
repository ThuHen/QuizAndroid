package com.example.qiuz;



import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button_Cheat;
    private Button button_True;
    private Button button_False;
    private ImageButton button_next;
    private ImageButton button_pre;
    private TextView question;
    private Question[] questionBank =new Question[]{
        new Question(R.string.question_1,false),
            new Question(R.string.question_2,false),
            new Question(R.string.question_3,true),
            new Question(R.string.question_4,true),
            new Question(R.string.question_5,false)

    };
    private int indexQuestion = 0;
    private boolean mIsCheater;
    private void updateQuestion(){
        question.setText(questionBank[indexQuestion].getQuestion());
    }
    private void HienDapAn(boolean ans){
        boolean answerTrue= questionBank[indexQuestion].isAnswer();
        int mess;
        if (mIsCheater)//co cheat
            mess= R.string.toast_cheating;
        else{
            if (ans==answerTrue) {
                mess = R.string.answer_correct;
                question.setTextColor(Color.parseColor("#FF30E137"));
            }
            else {
                mess = R.string.answer_incorrect;
                question.setTextColor(Color.parseColor("#FFF30909"));
            }
            Toast.makeText(MainActivity.this, mess, Toast.LENGTH_SHORT).show();
        }

    }
    private static final String KEY_INDEX="index";
    private static final String TAG="MainActivity";
    ActivityResultLauncher<Intent> startActivity4Result= registerForActivityResult();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"inCreate() called");
    //============
        if(savedInstanceState!=null){
            indexQuestion=savedInstanceState.getInt(KEY_INDEX,0);

        }
    //=============
        button_Cheat= findViewById(R.id.btn_cheat);
        button_True= findViewById(R.id.btn_true);
        button_False= findViewById(R.id.btn_false);
        button_next= findViewById(R.id.btn_next);
        button_pre= findViewById(R.id.btn_previous);
        question=findViewById(R.id.text_view_question);
    //===========
        updateQuestion();
        button_True.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                HienDapAn(true);
            }
        });
        button_False.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HienDapAn(false);
            }
        });
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsCheater= true;
                question.setTextColor(getApplication().getResources().getColor(R.color.purple_500));
                if (indexQuestion != 4) {
                    indexQuestion = (indexQuestion + 1);
                   updateQuestion();
                } else Toast.makeText(MainActivity.this, "het cau hoi", Toast.LENGTH_SHORT).show();
            }
        });
        button_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question.setTextColor(getApplication().getResources().getColor(R.color.purple_500));
                if (indexQuestion != 0) {
                    indexQuestion = (indexQuestion - 1);
                    updateQuestion();
                }
                else
                    Toast.makeText(MainActivity.this, "khong co cau hoi", Toast.LENGTH_SHORT).show();
            }
        });
        button_Cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i= new Intent(MainActivity.this,CheatActivity.class);

                boolean answer=questionBank[indexQuestion].isAnswer();
                Intent i= CheatActivity.newIntent(MainActivity.this,answer);
//                i.putExtra("answer",answer);
                //=====
                startActivity(i);

            }
        });

    }
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstanceState");
        outState.putInt(KEY_INDEX,indexQuestion);
    }




}