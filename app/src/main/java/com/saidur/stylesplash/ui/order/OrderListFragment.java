package com.saidur.stylesplash.ui.order;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.order.adapter.AllOrderAdapter;
import com.saidur.stylesplash.ui.order.network.OrderListData;
import com.saidur.stylesplash.ui.order.trackorder.Counter;
import com.saidur.stylesplash.ui.product.network.StockData;
import com.saidur.stylesplash.utils.Const;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class OrderListFragment extends Fragment {

    SwipeRefreshLayout swiporder;
    OrderListVM orderListVM;
    RecyclerView rv_allorder;
    AllOrderAdapter allOrderAdapter;
    List<OrderListData> newOrderlist;
    String msg, Sdate, Tdate, filterStatus;
    TextView selectDate, todayTv;
    EditText searchdateTv;
    ProgressDialog pd;
    private DatePickerDialog datePickerDialog;
   
    public static int ass;
    public OrderListFragment(/*Counter counter*/) {
        // Required empty public constructor
        //this.counter=counter;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order_list, container, false);
        Tdate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        pd = new ProgressDialog(getContext());
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        pd.show();



        orderListVM = new ViewModelProvider(this).get(OrderListVM.class);
        initView(v);
        orderListVM.AllOrderApiCall();
        prepareRv(v);
        orderListVM.getAllOrderObserver().observe(getViewLifecycleOwner(), new Observer<List<OrderListData>>() {
            @Override
            public void onChanged(List<OrderListData> orderListData) {
                if (orderListData != null) {
                    newOrderlist = orderListData;
                    allOrderAdapter.setOrderList(orderListData);

                    int count = 0;
                    if (allOrderAdapter != null) {
                        count = allOrderAdapter.getItemCount();
                    }

                }
            }
        });
        orderListVM.AllOrderloadDoneObserver().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s.equals("ok"))
                {
                    pd.dismiss();
                }if(s.equals("notok")) {
                    pd.dismiss();
                    Toast.makeText(getActivity(), "Data Not Loded correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });
        orderListVM.deleteOrderObserver().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                msg = s;
            }
        });

        swiporder.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                orderListVM.AllOrderApiCall();
                allOrderAdapter.notifyDataSetChanged();
                swiporder.setRefreshing(false);
            }
        });



        return v;
    }

    private void initView(View v) {
        swiporder = v.findViewById(R.id.swiporder);
        searchdateTv = v.findViewById(R.id.searchdateTv);
        todayTv = v.findViewById(R.id.todayTv);

        switch (Const.from)
        {
            case "dailyrep":

                searchdateTv.setText(Tdate);
                search(v);
                break;
        }
        todayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchdateTv.setText(Tdate);
                search(v);
            }
        });
    }

    private void initpopup(View v) {
        PopupMenu popupMenu = new PopupMenu(getActivity(), v);
        popupMenu.getMenuInflater().inflate(R.menu.status, popupMenu.getMenu());
        popupMenu.show();


        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.men_process:
                        filterStatus = "1";
              /*          Toast.makeText(getActivity(), "process click ", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), "stas " + filterStatus, Toast.LENGTH_SHORT).show();*/
                        break;
                    case R.id.men_sale:
                        filterStatus = "2";
             /*           Toast.makeText(getActivity(), "sale click ", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), "stas " + filterStatus, Toast.LENGTH_SHORT).show();*/
                        break;
                    case R.id.men_cancle:
                        filterStatus = "5";
                     /*   Toast.makeText(getActivity(), "cancle click ", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), "stas " + filterStatus, Toast.LENGTH_SHORT).show();*/
                        break;
                }
                return false;
            }
        });
    }

    public void prepareRv(View v) {
        rv_allorder = v.findViewById(R.id.orderlistRv);
        rv_allorder.setLayoutManager(new LinearLayoutManager(getActivity()));
        allOrderAdapter = new AllOrderAdapter(getActivity(), newOrderlist);
        rv_allorder.setAdapter(allOrderAdapter);
    }


    private void search(View v) {

        searchdateTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(Tdate);
                rv_allorder.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //rv_allorder.setVisibility(View.GONE);
                filter(charSequence.toString());
                rv_allorder.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

                filter(editable.toString());
                rv_allorder.setVisibility(View.VISIBLE);
            }
        });
    }

    private void filter(String word) {
        ArrayList<OrderListData> filterwordlist = new ArrayList<>();
        for (OrderListData words : newOrderlist) {

            if (words.getoDate().toLowerCase().contains(word.toLowerCase())) {
                filterwordlist.add(words);
            }
        }

        allOrderAdapter.filterListFun(filterwordlist);
        allOrderAdapter.notifyDataSetChanged();
    }
}