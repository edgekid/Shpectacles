package com.example.black.shpectacles;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

    public SharedPreference() {
        super();
    }
    //
    //
    //
    //
    public void saveTimeH(Context context, String text) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;

        editor = settings.edit();

        editor.putString("pass", text);

        editor.commit();
    }
    public void saveTimeM(Context context, String text) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;

        editor = settings.edit();

        editor.putString("pass1", text);

        editor.commit();
    }
    public void saveTimeS(Context context, String text) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;

        editor = settings.edit();

        editor.putString("pass2", text);

        editor.commit();
    }
    public void saveWelcome(Context context, String b) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;

        editor = settings.edit();

        editor.putString("pass3", b);

        editor.commit();
    }
    public void saveRecord(Context context, String text) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;

        editor = settings.edit();

        editor.putString("pass4", text);

        editor.commit();
    }
    public void saveWarnings(Context context, String text) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;

        editor = settings.edit();

        editor.putString("pass5", text);

        editor.commit();
    }
    public void saveAverage(Context context, String text) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;

        editor = settings.edit();

        editor.putString("pass6", text);

        editor.commit();
    }
    public void saveMode(Context context, String text) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;

        editor = settings.edit();

        editor.putString("pass7", text);

        editor.commit();
    }
    public void saveTutorial(Context context, String text) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;

        editor = settings.edit();

        editor.putString("pass8", text);

        editor.commit();
    }
    //
    //
    //
    //
    public String getTimeH(Context context) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);

        String text;
        text = settings.getString("pass", null);

        return text;
    }
    public String getTimeM(Context context) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);

        String text;
        text = settings.getString("pass1", null);

        return text;
    }
    public String getTimeS(Context context) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);

        String text;
        text = settings.getString("pass2", null);

        return text;
    }
    public String getWelcome(Context context) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);

        String text;
        text = settings.getString("pass3", null);

        return text;
    }
    public String getRecord(Context context) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);

        String text;
        text = settings.getString("pass4", null);

        return text;
    }
    public String getWarnings(Context context) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);

        String text;
        text = settings.getString("pass5", null);

        return text;
    }
    public String getAverage(Context context) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);

        String text;
        text = settings.getString("pass6", null);

        return text;
    }
    public String getMode(Context context) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);

        String text;
        text = settings.getString("pass7", null);

        return text;
    }
    public String getTutorial(Context context) {
        SharedPreferences settings = context.getSharedPreferences("NUME_FISIER", Context.MODE_PRIVATE);

        String text;
        text = settings.getString("pass8", null);

        return text;
    }
}
