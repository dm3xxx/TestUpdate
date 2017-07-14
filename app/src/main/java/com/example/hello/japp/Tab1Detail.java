package com.example.hello.japp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

public class Tab1Detail extends Fragment {

    private static final String Pref_Tag = "Tab_1_Detail_Pref_Tag";
    private static final String Str_Tag = "Tab_1_Detail_Str_Tag";
    private String mStr;

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup vgc, Bundle savedInstanceState) {

        SharedPreferences prefs = this.getActivity().getSharedPreferences(Pref_Tag, MODE_PRIVATE);
        mStr = prefs.getString(Str_Tag, "Nothing selected!!!");

        View v = inf.inflate(R.layout.fragment_tab1_detail, vgc, false);
        TextView txtV = v.findViewById(R.id.tab1_detail_textview);
        txtV.setText(mStr);

        return v;
    }

    @Override
    public void onStop() {
        super.onStop();

        System.out.println("Tab1Detail::onStop");

        SharedPreferences.Editor ed = this.getActivity().getSharedPreferences(Pref_Tag, MODE_PRIVATE).edit();
        ed.putString(Str_Tag, mStr);
        ed.apply();
    }

    public void updateInfo(String s) {
        mStr = s;
        ((TextView) getView().findViewById(R.id.tab1_detail_textview)).setText(mStr);
    }

}
