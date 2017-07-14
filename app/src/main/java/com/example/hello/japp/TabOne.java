package com.example.hello.japp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TabOne extends Fragment {

    private enum Pages {Input, List, Detail}
    private final List<String> PageNames;
//    private CustomViewPager mVP = null;
//    private CustomPagerAdapter mPA = null;
    private int curFragment;

    public TabOne() {
        PageNames = new ArrayList<>();
        PageNames.add(Pages.Input.toString());
//        PageNames.add(Pages.Picker.toString());
        PageNames.add(Pages.List.toString());
        PageNames.add(Pages.Detail.toString());

        curFragment = Pages.Input.ordinal();
    }

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup vg, Bundle savedInstanceState) {

        View v = inf.inflate(R.layout.fragment_tab_one, vg, false);

        CustomViewPager cvp = v.findViewById(R.id.tab_1_cvp_container);
        CustomPagerAdapter cpa = new CustomPagerAdapter(getChildFragmentManager());
        cpa.addFragment(new Tab1Input(), PageNames.get(Pages.Input.ordinal()));
        // TODO add picker fragment
        cpa.addFragment(new Tab1List(), PageNames.get(Pages.List.ordinal()));
        cpa.addFragment(new Tab1Detail(), PageNames.get(Pages.Detail.ordinal()));
        cvp.setAdapter(cpa);
        cvp.setSwipeable(false);
        cvp.setScrollable(false);
        cvp.setCurrentItem(curFragment);

        return v;
    }

    public boolean handleBackPressed() {
        if (Pages.Input.ordinal() == curFragment)
            return false;

        ((CustomViewPager) getView().findViewById(R.id.tab_1_cvp_container))
                .setCurrentItem(--curFragment);
        return true;
    }

    public void showPage(Pages p) {
        curFragment = p.ordinal();
        ((CustomViewPager) getView().findViewById(R.id.tab_1_cvp_container))
                .setCurrentItem(curFragment);
    }

    public void updateListTab(List<String> data) {
        Tab1List tabList = (Tab1List) getChildFragmentManager()
                .findFragmentByTag("android:switcher:" + R.id.tab_1_cvp_container + ":" + Pages.List.ordinal());
        tabList.updateList(data);
        showPage(Pages.List);
    }

    public void updateDetailTab(String s) {
        Tab1Detail tabDetail = (Tab1Detail) getChildFragmentManager()
                .findFragmentByTag("android:switcher:" + R.id.tab_1_cvp_container + ":" + Pages.Detail.ordinal());
        tabDetail.updateInfo(s);
        showPage(Pages.Detail);
    }
}