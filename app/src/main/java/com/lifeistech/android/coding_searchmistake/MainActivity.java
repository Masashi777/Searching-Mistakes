package com.lifeistech.android.coding_searchmistake;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button[] buttons = new Button[9];
    private int[] buttonIds = {R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};
    private String[] colors = {"#F5F5F5", "#79D1B0", "#FDC44F", "#33BFDB", "#5EBABA", "#44DEDE", "#8DCF3F", "#93B8CA", "#F58E7E"};

    private TextView textView;
    private Button nextBtn;
    private int correct;

    private Random random = new Random();

    private CountDownTimer countDownTimer;
    public long COUNT_TIME = 2000;
    public long COUNT_INTERVAL = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // CountDownTimer
        countDownTimer = new CountDownTimer(COUNT_TIME, COUNT_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                // 1秒ごとに呼ばれる
            }

            @Override
            public void onFinish() {
                // 終了後に呼ばれる
                for (int i = 0; i < 9; i++) {
                    buttons[i].setEnabled(true);
                }
                nextQuestion();
            }
        };

        // Buttons
        for (int i = 0; i < 9; i++) {
            // ひもづけ
            buttons[i] = findViewById(buttonIds[i]);
            buttons[i].setText("");

            // リスナー
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == buttonIds[correct]) {
                        // 正解
                        textView.setText("正解！");

                        for (int i = 0; i < 9; i++) {
                            buttons[i].setEnabled(false);
                        }
                        countDownTimer.start();

                    } else {
                        // 間違い
                        textView.setText("間違い！");
                    }
                }
            });
        }

        // Others
        textView = findViewById(R.id.textView);
        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        setTitle("間違い探し！");

        nextQuestion();

    }

    private void nextQuestion () {

        textView.setText("間違いを探せ！");

        correct = random.nextInt(9);

        int numA = random.nextInt(9);
        int numB = numA;
        while (numB == numA) {
            numB = random.nextInt(9);
        }

        for (int i = 0; i < 9; i++) {

            if (i == correct) {
                // 正解のボタン
                buttons[i].setBackgroundColor(Color.parseColor(colors[numA]));
            } else {
                // 間違いのボタン
                buttons[i].setBackgroundColor(Color.parseColor(colors[numB]));
            }
        }

    }

}
