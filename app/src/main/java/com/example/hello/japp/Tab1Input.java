package com.example.hello.japp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Tab1Input extends Fragment implements Button.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup vgc, Bundle savedInstanceState) {

        View v = inf.inflate(R.layout.fragment_tab1_input, vgc, false);

        Button clrBtn = v.findViewById(R.id.tab1_input_color_btn);
        Button subBtn = v.findViewById(R.id.tab1_input_submit_btn);
        EditText digEtx = v.findViewById(R.id.tab1_input_digit_input);
        EditText nameEtx = v.findViewById(R.id.tab1_input_name_input);

        clrBtn.setOnClickListener(this);
        subBtn.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

        switch (v.getId()) {
            case R.id.tab1_input_color_btn:
                break;

            case R.id.tab1_input_submit_btn:
                ((MainActivity) getActivity()).updateListPage();
                break;
        }
    }
}