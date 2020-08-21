package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private String result[] = new String[5];
    private Button mtruebutton;
    private Button mfalsebutton;
    private TextView questionTextView;
    private Button mnextbutton;
    private int correct=0,incorrect=0;
    private Quiz questionBank[] = new Quiz[]{
            new Quiz(R.string.question_1,true),
            new Quiz(R.string.question_2,false),
            new Quiz(R.string.question_3,true),
            new Quiz(R.string.question_4,true),
            new Quiz(R.string.question_5,true),
    };
    private int currentIndex=0;

    private void setTextView(int index) throws InterruptedException {
        questionTextView = (TextView) findViewById(R.id.QuestionDisplay);

        if(index >= 5){
            index = 0;
            for(int i=0;i<5;i++){
                if(result[i] == "Correct")
                    correct +=1;
                else incorrect +=1 ;
            }
            questionTextView.setText("Quiz Over\nCorrect Answers : "+correct+"\nIncorrect Answers : "+incorrect);
            correct =0;
            incorrect =0;
            currentIndex = -1;
            return;
        }
        if(index < 0){
            Toast.makeText(MainActivity.this,"There is No Previous Question",Toast.LENGTH_LONG).show();
            currentIndex++;
            return;
        }
        int question = questionBank[index].getQuestion();
        questionTextView.setText(question);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting the textView for each question
        try {
            setTextView(currentIndex);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //true button listener
        mtruebutton = (Button) findViewById(R.id.true_button);
        mtruebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(currentIndex == 0 || currentIndex == 2 || currentIndex == 3 || currentIndex == 4 || currentIndex == 5) {
                    Toast toast = Toast.makeText(MainActivity.this, "Your Answer is Correct!!", Toast.LENGTH_SHORT);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                    if(currentIndex >= 0)
                        result[currentIndex] = "Correct";
                    try {
                        setTextView(++currentIndex);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast toast = Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                    if(currentIndex >= 0)
                        result[currentIndex] = "Incorrect";
                    try {
                        setTextView(++currentIndex);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //Toast.makeText(MainActivity.this,R.string.correct_toast,Toast.LENGTH_SHORT).show();
            }
        });

        //false  button listener
        mfalsebutton = (Button) findViewById(R.id.false_button);
        mfalsebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(currentIndex == 0 || currentIndex == 2 || currentIndex == 3 || currentIndex == 4 || currentIndex == 5) {
                    Toast.makeText(MainActivity.this, "InCorrect!!", Toast.LENGTH_SHORT).show();
                    if(currentIndex >= 0)
                        result[currentIndex] = "Incorrect";
                    try {
                        setTextView(++currentIndex);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Your Answer is Correct!!", Toast.LENGTH_SHORT).show();
                    if(currentIndex >= 0)
                        result[currentIndex] = "Correct";
                    try {
                        setTextView(++currentIndex);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //next button listener
        mnextbutton = (Button)findViewById(R.id.previous_button);
        mnextbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    setTextView(--currentIndex);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
