package com.saidur.stylesplash.ui.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.order.OrderListVM;
import com.saidur.stylesplash.ui.order.adapter.TabpreAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AllOrdersAc extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem t0,t1,t2,t3;
    ViewPager viewPager;
    OrderListVM orderListVM;
    String tdayshow;
    TextView dateall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);
        tdayshow = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        orderListVM=new ViewModelProvider(this).get(OrderListVM.class);
        orderListVM.AllOrderApiCall();
        initView();

    }

    private void initView() {
        tabLayout=(TabLayout)findViewById(R.id.servicetab);
        t0=findViewById(R.id.t0);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);

        dateall=findViewById(R.id.dateall);
        dateall.setText(tdayshow);


        viewPager=(ViewPager)findViewById(R.id.serviceViewpager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        TabpreAdapter adapter = new TabpreAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        tabLayout.setTabTextColors(Color.parseColor("#FF000000"), Color.parseColor("#B80007"));
        // tabLayout.setBackgroundColor(Color.parseColor("#FA8B8F"), Color.parseColor("#B80007"));
        viewPager.setAdapter(adapter);
        prepareViewpager();
    }
    private void prepareViewpager() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
              /*  if(tab.getPosition()==1)
                {
                    t1.setBackgroundResource(R.drawable.tabselected_bg);
                   // tabLayout.getChildAt(1).setBackground();
                   // tabLayout.getChildAt(1).setBackgroundColor(Color.parseColor("#FF0001"));
                    //t2.setBackgroundResource(R.drawable.tabselected_bg);
                   // t2.setBackgroundColor(Color.GREEN);
                }*/
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}