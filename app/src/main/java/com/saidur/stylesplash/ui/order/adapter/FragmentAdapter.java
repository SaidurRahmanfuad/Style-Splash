package com.saidur.stylesplash.ui.order.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.saidur.stylesplash.ui.order.OrderListFragment;
import com.saidur.stylesplash.ui.order.trackorder.CancleFragment;
import com.saidur.stylesplash.ui.order.trackorder.SalesFragment;
import com.saidur.stylesplash.ui.order.trackorder.inprocess.InprogressFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    Context context;

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,Context context) {
        super(fragmentManager, lifecycle);
        this.context = context;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {

            case 1:
                return new SalesFragment();
            case 2:
                return new InprogressFragment();
            case 3:
                return new CancleFragment();
        }

        return new OrderListFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}