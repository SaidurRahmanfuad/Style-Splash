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
import com.saidur.stylesplash.ui.customer.network.CustomerListData;
import com.saidur.stylesplash.ui.order.OrderListVM;
import com.saidur.stylesplash.ui.order.trackorder.cancle.model.CancleData;
import com.saidur.stylesplash.ui.order.trackorder.cancle.adapter.CancelListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CancleFragment extends Fragment {


    public CancleFragment() {
        // Required empty public constructor
    }

CancelListAdapter cancelListAdapter;
   OrderListVM orderListVM;

   RecyclerView rv_canclelist;
   SwipeRefreshLayout swip_can;
   EditText search_cancel;
   List<CancleData> canceltest;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_cancle, container, false);
        orderListVM = new ViewModelProvider(this).get(OrderListVM.class);
        cancelListAdapter=new CancelListAdapter(getActivity());
        orderListVM.OrdergetCancleListApiCall();
        orderListVM.getCancleOrderListObserver().observe(getViewLifecycleOwner(), new Observer<List<CancleData>>() {
            @Override
            public void onChanged(List<CancleData> cancleData) {
                canceltest=cancleData;
                cancelListAdapter.setCancelList(cancleData);
                rv_canclelist.setAdapter(cancelListAdapter);

            }
        });

        initView(v);
        swip_can.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                orderListVM.OrdergetCancleListApiCall();
                 cancelListAdapter.notifyDataSetChanged();
                swip_can.setRefreshing(false);
            }
        });
        searchview(v);
        return v;
    }
    private void initView(View v) {
        rv_canclelist=v.findViewById(R.id.cancelitms);
        swip_can=v.findViewById(R.id.swipcancel);
        rv_canclelist.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    private void searchview(View v) {
        search_cancel=v.findViewById(R.id.search_cancel);
        search_cancel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                rv_canclelist.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
                rv_canclelist.setVisibility(View.VISIBLE);
            }
        });
    }
    private void filter(String word) {
        ArrayList<CancleData> filterwordlist=new ArrayList<>();
        for (CancleData words : canceltest)
        {
            if (words.getCustomerName().toLowerCase().contains(word.toLowerCase())||words.getMobile().toLowerCase().contains(word.toLowerCase())
                /*||words.getAddress().toLowerCase().contains(word.toLowerCase())*/)
            {
                filterwordlist.add(words);
            }
        }

        cancelListAdapter.filterListFun(filterwordlist);
        cancelListAdapter.notifyDataSetChanged();


    }
}