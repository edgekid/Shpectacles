package com.example.black.shpectacles;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity{

    private Button btn;
    SharedPreference sharedPreference;
    Intent intent , intent2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_settings);

        intent = new Intent(SettingsActivity.this , MainActivity.class);
        intent2 = new Intent(SettingsActivity.this , MainActivityKidsAfterTut.class);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreference = new SharedPreference();
                if(sharedPreference.getMode(SettingsActivity.this) == null) {
                    sharedPreference.saveMode(SettingsActivity.this, "true");
                    startActivity(intent2);
                }
                else {
                    sharedPreference.saveMode(SettingsActivity.this, null);
                    startActivity(intent);
                }
            }
        });
    }
}
