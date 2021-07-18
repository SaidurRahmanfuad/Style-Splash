package com.saidur.stylesplash.ui.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.order.adapter.AllOrderAdapter;
import com.saidur.stylesplash.ui.order.adapter.FragmentAdapter;
import com.saidur.stylesplash.ui.order.network.OrderListData;
import com.saidur.stylesplash.ui.order.trackorder.Counter;
import com.saidur.stylesplash.ui.order.trackorder.cancle.model.CancleData;
import com.saidur.stylesplash.ui.order.trackorder.inprocess.model.InpModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static android.system.Os.sendto;

public class AllOrdersAc extends AppCompatActivity {
    TabLayout tabLayout;
    FragmentAdapter adapter;
    ViewPager2 viewPager;

    OrderListVM orderListVM;
    String tdayshow;
    FragmentManager fm;
    String countAO, countS, countI, countC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_order);
        tdayshow = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        orderListVM = new ViewModelProvider(this).get(OrderListVM.class);

        orderListVM.AllOrderApiCall();
        orderListVM.OrdergetSaleListApiCall();
        orderListVM.OrdergetInprocessListApiCall();
        orderListVM.OrdergetCancleListApiCall();

        prepareTab();

    }


    public void prepareTab() {
        tabLayout = (TabLayout) findViewById(R.id.servicetab);
        viewPager = (ViewPager2) findViewById(R.id.serviceViewpager);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle(), this/*,tabname,tcc*/);
        viewPager.setAdapter(adapter);
        final List<String> colors = new ArrayList<String>() {
            {
                add("#DADADA");
                add("#FFFFFF");
                add("#FFFFFF");
                add("#FFFFFF");
            }
        };
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        LayoutInflater layoutInflater = LayoutInflater.from(AllOrdersAc.this);
                        View tabView = layoutInflater.inflate(R.layout.tab_customlayout, null, false);
                        ConstraintLayout selection = (ConstraintLayout) tabView.findViewById(R.id.selection);
                        TextView tabtag = (TextView) tabView.findViewById(R.id.tabtagz);
                        TextView tabitemcounts = (TextView) tabView.findViewById(R.id.tabitemcounts);
                        tab.setCustomView(tabView);

                        tab.view.setBackgroundColor(Color.parseColor(colors.get(position)));
                        switch (position) {
                            case 0:
                                orderListVM.getAllOrderObserver().observe(AllOrdersAc.this, new Observer<List<OrderListData>>() {
                                    @Override
                                    public void onChanged(List<OrderListData> orderListData) {
                                        if (orderListData != null) {
                                            int icountAO = orderListData.size();
                                            countAO = String.valueOf(icountAO);
                                            tabtag.setText("All Order");
                                            tabitemcounts.setText(countAO);
                                        } else {
                                            try {
                                                tabtag.setText("All Order");
                                                tabitemcounts.setText("0");
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });

                                break;
                            case 1:
                                orderListVM.getSaleOrderListObserver().observe(AllOrdersAc.this, new Observer<List<CancleData>>() {
                                    @Override
                                    public void onChanged(List<CancleData> cancleData) {
                                        if (cancleData != null) {
                                            countS = String.valueOf(cancleData.size());
                                            tabtag.setText("SALES");
                                            tabitemcounts.setText(countS);
                                        } else {
                                            try {
                                                tabtag.setText("SALES");
                                                tabitemcounts.setText("0");
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });

                                break;
                            case 2:
                                orderListVM.getInprocessOrderListObserver().observe(AllOrdersAc.this, new Observer<List<InpModel>>() {
                                    @Override
                                    public void onChanged(List<InpModel> inpModels) {
                                        if (inpModels != null) {
                                            countI = String.valueOf(inpModels.size());
                                            tabtag.setText("IN PROCESS");
                                            tabitemcounts.setText(countI);
                                        } else {
                                            try {
                                                tabtag.setText("IN PROCESS");
                                                tabitemcounts.setText("0");
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });
                                break;
                            case 3:
                                orderListVM.getCancleOrderListObserver().observe(AllOrdersAc.this, new Observer<List<CancleData>>() {
                                    @Override
                                    public void onChanged(List<CancleData> cancleData) {
                                        if (cancleData != null) {
                                            countC = String.valueOf(cancleData.size());
                                            tabtag.setText("CANCEL");
                                            tabitemcounts.setText(countC);
                                        } else {
                                            try {
                                                tabtag.setText("CANCEL");
                                                tabitemcounts.setText("0");
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });
                                break;

                        }

                    }
                }).attach();
        prepareViewpager();
    }
    private void prepareViewpager() {


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                final List<String> colors = new ArrayList<String>() {
                    {
                        add("#DADADA");
                        //add("#F7FFFB");
                        add("#F0FBF6");
                        add("#EEF8FA");
                        add("#FBF3F3");
                    }
                };

                if (tab.getPosition() == 0) {
                    // tab.view.setBackgroundColor(Color.parseColor("#DADADA"));
                    tab.view.setBackgroundColor(Color.parseColor(colors.get(0)));
                } else {
                    tab.view.setBackgroundColor(Color.parseColor(colors.get(tab.getPosition())));
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                final List<String> white = new ArrayList<String>() {
                    {
                        add("#FFFFFF");
                        add("#FFFFFF");
                        add("#FFFFFF");
                        add("#FFFFFF");
                    }
                };
                //tab.view.setAlpha(1);
                tab.view.setBackgroundColor(Color.parseColor(white.get(tab.getPosition())));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
}