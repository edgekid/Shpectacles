package com.example.black.shpectacles;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity{

    public String hours , minutes , record , warnings , averagetime;
    private int records;
    private double average;
    TextView tv,tv2,tv3,tv4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_stats);

        SharedPreference sharedPreference = new SharedPreference();

        hours = sharedPreference.getTimeH(StatsActivity.this);
        minutes = sharedPreference.getTimeM(StatsActivity.this);
        //String text0 = sharedPreference.getTimeS(StatsActivity.this);
        tv = (TextView) findViewById(R.id.textView10);
        tv.setText(hours + " hrs & " + minutes + " mins");

        tv2 = (TextView) findViewById(R.id.textView11);
        if(sharedPreference.getWarnings(StatsActivity.this) == null){}
        else{
            warnings = sharedPreference.getWarnings(StatsActivity.this);
            tv2.setText(warnings);
        }

        tv3 = (TextView) findViewById(R.id.textView12);
        if(sharedPreference.getRecord(StatsActivity.this)== null){}
        else {
            record = sharedPreference.getRecord(StatsActivity.this);
            records = Integer.valueOf(record);
            tv3.setText(records/3600+ " hrs & " + (records/60)%60 + " mins");
        }

        tv4 = (TextView) findViewById(R.id.textView13);
        if(sharedPreference.getAverage(StatsActivity.this) == null){}
        else{
            averagetime = sharedPreference.getAverage(StatsActivity.this);
            average = Double.valueOf(averagetime);
            tv4.setText(average /3600 + " hrs & " + (average / 60) % 60 + " mins");
        }
    }
}
