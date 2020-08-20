package com.example.assessmenttest;

import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

public class Counter {

    private static volatile TextView countv;
    private static long time;

    static boolean countStarted;

    public static void setTextView(TextView tv) {

        countv = tv;

    }

    public static void start() {
        if (countStarted) return;

        countStarted = true;

        new CountDownTimer(180000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.d("test", "running");
                if (countv != null) {

                    long min = millisUntilFinished / 60000;

                    long reminingSecondsInMilies = millisUntilFinished % 60000;

                    long remainingSeconds = reminingSecondsInMilies / 1000;

                    countv.setText(min + ":" + remainingSeconds);
                }
            }

            public void onFinish() {
                countv.setText("done!");
            }
        }.start();

    }
}
