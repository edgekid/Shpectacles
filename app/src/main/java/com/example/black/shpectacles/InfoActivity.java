package com.example.black.shpectacles;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InfoActivity extends DialogFragment{
    View v;
    LayoutInflater inflater;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.a_info, null);

        builder.setView(inflater.inflate(R.layout.a_info, null));
        return builder.create();
    }
}
