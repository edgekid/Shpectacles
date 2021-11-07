package com.example.black.shpectacles;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AchievsAdapter extends BaseAdapter{
    private Activity context;
    private ArrayList<Achievs> achievs;

    public AchievsAdapter( Activity context ) {
        this.context = context;
        achievs = new ArrayList<Achievs>();
    }
    public void addAchiev(String Name , int resource){
        Achievs a = new Achievs();
        a.desc = Name;
        a.image = resource;
        achievs.add(a);
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return achievs.size();
    }

    @Override
    public Object getItem(int i) {
        return achievs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v;
        LayoutInflater in = context.getLayoutInflater();
        v = (View) in.inflate(R.layout.a_achievs_itemlist , null);

        TextView name = v.findViewById(R.id.tv_name_element);
        ImageView image = v.findViewById(R.id.iv_image_element);

        name.setText(achievs.get(i).desc);
        image.setImageResource(achievs.get(i).image);

        return v;
    }

}
