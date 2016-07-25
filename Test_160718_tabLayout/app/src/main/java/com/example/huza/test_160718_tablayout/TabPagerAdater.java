package com.example.huza.test_160718_tablayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by HuZA on 2016-07-18.
 */
public class TabPagerAdater extends FragmentPagerAdapter{

    int tabCount;

    public TabPagerAdater(FragmentManager fm, int numOfTab) {
        super(fm);
        this.tabCount = numOfTab;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                Tab1_fragment tab1 = new Tab1_fragment();
                return tab1;
            case 1:
                Tab2_fragment tab2 = new Tab2_fragment();
                return tab2;
            case 2:
                Tab3_fragment tab3 = new Tab3_fragment();
                return tab3;
            case 3:
                Tab4_fragment tab4 = new Tab4_fragment();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
