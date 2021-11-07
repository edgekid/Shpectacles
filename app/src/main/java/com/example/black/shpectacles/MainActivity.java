package com.example.black.shpectacles;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements WelcomeDialog.NoticeDialogListener{

    private Button stats , achievs , settings , info , turnOn ;
    private int seconds=0 , minutes=0 , hour=0 , record=0;
    private double average = 0;
    private SharedPreference sharedPreference;
    private String timp,timp2,timp0,welcome2;
    private boolean welcome=false , turn=false, pornit=false;
    private int dist2=0 , timpDeWarning=0 , warning=0;
    private String warnings , records;

    MediaPlayer mediaPlayer;

    private String mJsonResponse;
    RequestQueue queue;
    private String url;

    Handler handler = new Handler();
    public Runnable runnable;

    public void showNoticeDialog() {
        DialogFragment dialog = new WelcomeDialog();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        //Toast.makeText(getApplicationContext() , "fusei aci" , Toast.LENGTH_SHORT).show();
        sharedPreference.saveMode(MainActivity.this , "true");
        Intent in = new Intent(MainActivity.this , MainActivityKids.class);
        startActivity(in);
    }
    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);
        url ="http://192.168.11.121:5000/data";

        //

        sharedPreference = new SharedPreference();

        //WELCOME
        if(sharedPreference.getWelcome(MainActivity.this) == null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            WelcomeDialog cc = new WelcomeDialog();
            cc.show(fragmentTransaction , "dialog");
            welcome = true;
            welcome2 = String.valueOf(welcome);
            sharedPreference.saveWelcome(MainActivity.this , welcome2);
        }
        if(sharedPreference.getMode(MainActivity.this) == null){}
        else{
            Intent in = new Intent(MainActivity.this , MainActivityKids.class);
            startActivity(in);
        }

        if(sharedPreference.getTimeH(MainActivity.this) == null){}
        else
            hour = Integer.valueOf(sharedPreference.getTimeH(MainActivity.this));
        if(sharedPreference.getTimeM(MainActivity.this) == null){}
        else
            minutes = Integer.valueOf(sharedPreference.getTimeM(MainActivity.this));
        if(sharedPreference.getTimeS(MainActivity.this) == null){}
        else
            seconds = Integer.valueOf(sharedPreference.getTimeS(MainActivity.this));

        stats = (Button) findViewById(R.id.b_stats);
        achievs = (Button) findViewById(R.id.b_achievs);
        settings = (Button) findViewById(R.id.b_settings);
        info = (Button) findViewById(R.id.b_info);
        turnOn = (Button) findViewById(R.id.button4);

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , StatsActivity.class);
                startActivity(intent);
            }
        });
        achievs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , AchievsActivity.class);
                startActivity(intent);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , SettingsActivity.class);
                startActivity(intent);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                InfoActivity cc = new InfoActivity();
                cc.show(fragmentTransaction , "dialog");
            }
        });
        turnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turn==false) {
                    handler.post(runnable);
                    turn = true;
                    turnOn.setBackgroundResource(R.drawable.turnonbutton);
                }
                else {
                    handler.removeCallbacks(runnable);
                    turn = false;
                    turnOn.setBackgroundResource(R.drawable.turnoffbutton);
                }
            }
        });

         runnable = new Runnable() {
            @Override
            public void run() {
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                        url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String dist = response.getString("temp");

                            mJsonResponse = "";
                            mJsonResponse += dist;

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
                queue.add(jsonObjReq);
                //Toast.makeText(getApplicationContext() , "In exterior" + mJsonResponse + " " + timpDeWarning , Toast.LENGTH_SHORT).show();
                if(mJsonResponse == null){}
                else
                    dist2  = Integer.valueOf(mJsonResponse);
                if(dist2<15)
                    ++timpDeWarning;
                if(dist2>=20 && timpDeWarning!=0)
                    timpDeWarning=0;
                if(pornit){
                    Toast.makeText(getApplicationContext(), "You're too close. Please move away! " , Toast.LENGTH_SHORT).show();
                    mediaPlayer = MediaPlayer.create(getApplicationContext()
                            , R.raw.beeps);
                    mediaPlayer.start();
                }
                if(pornit && dist2>=20) {
                    pornit = false;
                    timpDeWarning=0;
                    mediaPlayer.stop();
                }
                if(timpDeWarning==10){
                    pornit = true;

                    //Toast.makeText(getApplicationContext() ,"aasa", Toast.LENGTH_SHORT).show();
                    records = String.valueOf(record);
                    if(sharedPreference.getAverage(MainActivity.this) == null) {
                        // Toast.makeText(getApplicationContext() ,"aasa1", Toast.LENGTH_SHORT).show();
                        sharedPreference.saveAverage(MainActivity.this, records);
                        sharedPreference.saveWarnings(MainActivity.this , "1");
                    }else{
                        warnings = sharedPreference.getWarnings(MainActivity.this);
                        warning = Integer.valueOf(warnings);
                        ++warning;
                        //Toast.makeText(getApplicationContext() , warning + " " , Toast.LENGTH_SHORT).show();
                        sharedPreference.saveWarnings(MainActivity.this , String.valueOf(warning));
                        // Toast.makeText(getApplicationContext() , sharedPreference.getWarnings(MainActivity.this) , Toast.LENGTH_SHORT).show();
                        average = Double.valueOf(sharedPreference.getAverage(MainActivity.this));
                        average = ((average*Integer.valueOf(sharedPreference.getWarnings(MainActivity.this)))+record)/warning;
                        sharedPreference.saveAverage(MainActivity.this , String.valueOf(average));
                    }
                    sharedPreference.saveRecord(MainActivity.this , records);
                    record=0;
                    timpDeWarning = 0;

                }

                ++seconds;
                ++record;
                if(seconds == 60){
                    ++minutes;
                    seconds=0;
                    timp2 = String.valueOf(minutes);
                    sharedPreference.saveTimeM(MainActivity.this, timp2);

                }
                if(minutes == 60){
                    minutes=0;
                    ++hour;
                    timp = String.valueOf(hour);
                    sharedPreference.saveTimeH(MainActivity.this, timp);
                }
                timp0 = String.valueOf(seconds);
                sharedPreference.saveTimeS(MainActivity.this , timp0);
                //Toast.makeText(getApplicationContext() , sharedPreference.getTimeS(MainActivity.this) , Toast.LENGTH_SHORT).show();
                handler.postDelayed(runnable, 1000);
            }
        };


    }

    @Override
    protected void onResume(){
        super.onResume();
        if(turn == true)
            handler.post(runnable);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        handler.removeCallbacks(runnable);
        mediaPlayer.stop();
    }
    //connect to
    //http://192.168.11.121:5000/data
}
