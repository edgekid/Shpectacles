package com.example.black.shpectacles;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AchievsActivity extends AppCompatActivity{

    private ListView listOfAchievs;
    private List<String> achievs;
    public int imagine;
    AchievsAdapter adapt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_achievs);

        adapt = new AchievsAdapter(AchievsActivity.this);
        imagine = R.drawable.ic_action_name;
        listOfAchievs = (ListView) findViewById(R.id.lv_items);
        achievs = new ArrayList<String>();

        achievs.add("You used the glasses 10 minutes without getting any warning!");
        achievs.add("You used the glasses 30 minutes without getting any warning!");
        achievs.add("You used the glasses 1 hour without getting any warning!");
        achievs.add("You used the glasses 2 hours without getting any warning!");

        for(int i=0; i<achievs.size() ; ++i)
            adapt.addAchiev( achievs.get(i), imagine);

        listOfAchievs.setAdapter(adapt);
    }

}
