package com.saidur.stylesplash.ui.order.trackorder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.order.OrderListVM;
import com.saidur.stylesplash.ui.order.trackorder.cancle.model.CancleData;
import com.saidur.stylesplash.ui.order.trackorder.sales.adapter.SalesListAdapter;

import java.util.ArrayList;
import java.util.List;


public class SalesFragment extends Fragment {


    public SalesFragment() {
        // Required empty public constructor
    }

    SalesListAdapter salesListAdapter;
    OrderListVM orderListVM;

    RecyclerView rv_salelist;
    SwipeRefreshLayout swip_sal;
    EditText search_sale;
    List<CancleData> salelist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_delivered, container, false);
        orderListVM = new ViewModelProvider(this).get(OrderListVM.class);
        salesListAdapter = new SalesListAdapter(getActivity());
        orderListVM.OrdergetSaleListApiCall();

        orderListVM.getSaleOrderListObserver().observe(getViewLifecycleOwner(), new Observer<List<CancleData>>() {
            @Override
            public void onChanged(List<CancleData> cancleData) {
                salelist=cancleData;
                salesListAdapter.setCancelList(cancleData);
                rv_salelist.setAdapter(salesListAdapter);
            }
        });
        initView(v);
        swip_sal.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                orderListVM.OrdergetSaleListApiCall();
                salesListAdapter.notifyDataSetChanged();
                swip_sal.setRefreshing(false);
            }
        });
        searchview(v);
        return v;
    }

    private void initView(View v) {
        rv_salelist = v.findViewById(R.id.recy_delivereditm);
        swip_sal = v.findViewById(R.id.swipsal);
        rv_salelist.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    private void searchview(View v) {
        search_sale=v.findViewById(R.id.search_sale);
        search_sale.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                rv_salelist.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
                rv_salelist.setVisibility(View.VISIBLE);
            }
        });
    }
    private void filter(String word) {
        ArrayList<CancleData> filterwordlist=new ArrayList<>();
        for (CancleData words : salelist)
        {
            if (words.getMobile().toLowerCase().contains(word.toLowerCase()))
            {
                filterwordlist.add(words);
            }
        }

        salesListAdapter.filterListFun(filterwordlist);
        salesListAdapter.notifyDataSetChanged();
    }
}