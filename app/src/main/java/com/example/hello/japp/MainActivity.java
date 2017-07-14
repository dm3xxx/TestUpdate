package com.example.hello.japp;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private enum Tabs {Tab_One, Tab_Two, Tab_Three}

    private final List<String> TabNames = new ArrayList<>();
    private final ArrayList<ArrayList<String>> mData = new ArrayList<ArrayList<String>>();
    private CustomViewPager mCVP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabNames.add(Tabs.Tab_One.toString());
        TabNames.add(Tabs.Tab_Two.toString());
        TabNames.add(Tabs.Tab_Three.toString());

        mCVP = (CustomViewPager) findViewById(R.id.main_tab_container);
        CustomPagerAdapter cpa = new CustomPagerAdapter(getSupportFragmentManager());
        cpa.addFragment(new TabOne(), TabNames.get(Tabs.Tab_One.ordinal()));
        cpa.addFragment(new TabTwo(), TabNames.get(Tabs.Tab_Two.ordinal()));
        cpa.addFragment(new TabThree(), TabNames.get(Tabs.Tab_Three.ordinal()));
        mCVP.setAdapter(cpa);
        mCVP.setSwipeable(false);
        mCVP.setScrollable(false);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.bottom_tabs);
        tabLayout.setupWithViewPager(mCVP);
    }

    private TabOne getTab1() {
        return (TabOne) getSupportFragmentManager()
                .findFragmentByTag("android:switcher:" + R.id.main_tab_container + ":0");
    }

    @Override
    public void onBackPressed() {
        if (Tabs.Tab_One.ordinal() == mCVP.getCurrentItem()) {
            TabOne t1 = getTab1();
            if ((null != t1) && t1.handleBackPressed()) {
                return;
            }
        }

        super.onBackPressed();
    }

    public void updateListPage() {
        mData.add(new ArrayList<>(Arrays.asList(Integer.toString(mData.size()), "Hello" + mData.size())));

        List<String> list = new ArrayList<>();
        for (List<String> ls : mData) {
            list.add(ls.get(0));
        }
        getTab1().updateListTab(list);
    }

    public void updateDetailPage(int pos) {
        getTab1().updateDetailTab(mData.get(pos).get(0));
    }
}