package com.example.black.shpectacles;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivityKidsAfterTut extends AppCompatActivity{

    Button turnOn;
    private boolean turn=false  , pornit=false;
    private String mJsonResponse , url;
    RequestQueue queue;
    private int dist2;
    private int timpDeWarning=0;
    MediaPlayer mediaPlayer;
    private ConstraintLayout layout;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
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
                layout.setBackgroundResource(R.drawable.withoutstayaway);
                mediaPlayer = MediaPlayer.create(getApplicationContext()
                        , R.raw.beeps);
                mediaPlayer.start();
            }
            if(pornit && dist2>=20) {
                pornit = false;
                layout.setBackgroundResource(R.drawable.day);
                mediaPlayer.stop();
                timpDeWarning = 0;
            }
            if(timpDeWarning==10) {
                pornit = true;
                timpDeWarning = 0;
            }
            handler.postDelayed(runnable , 1000);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.k_layout_at);

        queue = Volley.newRequestQueue(this);
        url ="http://192.168.11.121:5000/data";

        layout = (ConstraintLayout) findViewById(R.id.constraint);
        layout.setBackgroundResource(R.drawable.mswakeupbutton);

        turnOn = (Button) findViewById(R.id.button7);
        turnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turn==false) {
                    handler.post(runnable);
                    turn = true;
                    turnOn.setBackgroundResource(R.drawable.turndaybutton);
                    layout.setBackgroundResource(R.drawable.withoutstayaway);

                }
                else {
                    handler.removeCallbacks(runnable);
                    turn = false;
                    turnOn.setBackgroundResource(R.drawable.turnnighton);
                    layout.setBackgroundResource(R.drawable.mswakeupbutton);
                }
            }
        });
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        handler.removeCallbacks(runnable);
        mediaPlayer.stop();
    }
}
