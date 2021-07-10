package com.saidur.stylesplash.ui.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.order.adapter.TabpreAdapter;

public class ProductMainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem t0,t1;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_main);
        initView();
    }
    private void initView() {
        tabLayout=(TabLayout)findViewById(R.id.producttab);
        t0=findViewById(R.id.t0);
        t1=findViewById(R.id.t1);


        viewPager=(ViewPager)findViewById(R.id.productViewpager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        TabStockAdapter adapter = new TabStockAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
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