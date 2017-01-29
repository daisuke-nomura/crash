package com.kyaracter.crash.example;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTask<Void, Void, Void>() {

            @UiThread
            @Override
            protected Void doInBackground(Void... params) {
                //crash here
                return null;
            }
        }.execute();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @WorkerThread
            @Override
            public void run() {
                //crash here
            }
        }, 0);
    }
}
