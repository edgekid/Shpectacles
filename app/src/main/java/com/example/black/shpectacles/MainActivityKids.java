package com.example.black.shpectacles;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivityKids extends AppCompatActivity{

    ImageView imageView;
    SharedPreference sharedPreference;
    boolean b1 =false,b2=false,b3=false,b4=false,b5=false,deschidere=false;
    Handler handler1 = new Handler();
    Handler handler2 = new Handler();
    Handler handler3 = new Handler();
    Handler handler4 = new Handler();
    Handler handler5 = new Handler();

    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            //imageView.setBackgroundResource(R.drawable.character4);
            if (b1 == false) {
                handler2.postDelayed(runnable2,5000);
                b1=true;
                imageView.setBackgroundResource(R.drawable.character1);
                //Toast.makeText(getApplicationContext() , "1" , Toast.LENGTH_SHORT).show();
            }
        }
    };
    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
           // imageView.setBackgroundResource(R.drawable.character3);
            if (b2 == false) {
                handler1.removeCallbacks(runnable1);
                handler3.postDelayed(runnable3,5000);
                b2=true;
                imageView.setBackgroundResource(R.drawable.character2);
               // Toast.makeText(getApplicationContext() , "2" , Toast.LENGTH_SHORT).show();
            }
        }
    };
    Runnable runnable3 = new Runnable() {
        @Override
        public void run() {
           // imageView.setBackgroundResource(R.drawable.character2);
            if (b3 == false) {
                handler2.removeCallbacks(runnable2);
                handler4.postDelayed(runnable4,5000);
                b3=true;
                imageView.setBackgroundResource(R.drawable.character3);
               // Toast.makeText(getApplicationContext() , "3" , Toast.LENGTH_SHORT).show();
            }
        }
    };
    Runnable runnable4 = new Runnable() {
        @Override
        public void run() {
           // imageView.setBackgroundResource(R.drawable.character5);
            if (b4 == false) {
                handler3.removeCallbacks(runnable3);
                handler5.postDelayed(runnable5,5000);
                b4=true;
                imageView.setBackgroundResource(R.drawable.character5);
               // Toast.makeText(getApplicationContext() , "4" , Toast.LENGTH_SHORT).show();
            }
        }
    };
    Runnable runnable5 = new Runnable() {
        @Override
        public void run() {
           // imageView.setBackgroundResource(R.drawable.character1);
            if (b5 == false) {
                handler4.removeCallbacks(runnable2);
                imageView.setBackgroundResource(R.drawable.character4);
               // Toast.makeText(getApplicationContext() , "5" , Toast.LENGTH_SHORT).show();
            }
            else if(deschidere == false){
                Intent in = new Intent(MainActivityKids.this , MainActivityKidsAfterTut.class);
                startActivity(in);
                handler5.removeCallbacks(runnable5);
                deschidere = true;
            }
            b5=true;
            handler5.postDelayed(runnable5,5000);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.k_layout);

        imageView = (ImageView) findViewById(R.id.imageView3);
        sharedPreference = new SharedPreference();

        if(sharedPreference.getTutorial(MainActivityKids.this) == null){
            sharedPreference.saveTutorial(MainActivityKids.this, "a");
        }
        else {
            Intent in = new Intent(MainActivityKids.this , MainActivityKidsAfterTut.class);
            startActivity(in);
        }
        handler1.post(runnable1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
