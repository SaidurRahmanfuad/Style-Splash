package com.saidur.stylesplash.ui.order.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.saidur.stylesplash.ui.order.OrderListFragment;
import com.saidur.stylesplash.ui.order.trackorder.CancleFragment;
import com.saidur.stylesplash.ui.order.trackorder.SalesFragment;
import com.saidur.stylesplash.ui.order.trackorder.inprocess.InprogressFragment;

public class TabpreAdapter extends FragmentPagerAdapter {
    int totalTabs;

    public TabpreAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        totalTabs = behavior;

    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

               // tab.setBackgroundResource(R.drawable.tabselected_bg);

                return new OrderListFragment();

            case 1:

                return new SalesFragment();
            case 2:
                return new InprogressFragment();
            case 3:
                return new CancleFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
