package com.saidur.stylesplash.ui.order.trackorder.inprocess;

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
import android.widget.Toast;

import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.order.OrderListVM;
import com.saidur.stylesplash.ui.order.trackorder.cancle.adapter.CancelListAdapter;
import com.saidur.stylesplash.ui.order.trackorder.cancle.model.CancleData;
import com.saidur.stylesplash.ui.order.trackorder.inprocess.adapter.InprocessListAdapter;
import com.saidur.stylesplash.ui.order.trackorder.inprocess.model.InpModel;

import java.util.ArrayList;
import java.util.List;

public class InprogressFragment extends Fragment {

    public InprogressFragment() {
        // Required empty public constructor
    }
    InprocessListAdapter inprocessListAdapter;
    OrderListVM orderListVM;

    RecyclerView rv_inplist;
    SwipeRefreshLayout swip_inp;
    EditText search_inprog;
    List<InpModel> Inplist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_inprogress, container, false);
        orderListVM = new ViewModelProvider(this).get(OrderListVM.class);
        inprocessListAdapter=new InprocessListAdapter(getActivity());
        orderListVM.OrdergetInprocessListApiCall();
        orderListVM.getInprocessOrderListObserver().observe(getViewLifecycleOwner(), new Observer<List<InpModel>>() {
            @Override
            public void onChanged(List<InpModel> cancleData) {
                inprocessListAdapter.setInprocessList(cancleData);
                rv_inplist.setAdapter(inprocessListAdapter);
                Inplist=cancleData;

                int count = 0;
                if (inprocessListAdapter != null) {
                    count = inprocessListAdapter.getItemCount();
                }
                Toast.makeText(getActivity(), "Total Inprocess : "+count, Toast.LENGTH_SHORT).show();
            }
        });

        initView(v);
        swip_inp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                orderListVM.OrdergetCancleListApiCall();
                inprocessListAdapter.notifyDataSetChanged();
                swip_inp.setRefreshing(false);
            }
        });
        searchview(v);
        return v;
    }

    private void initView(View v) {
        rv_inplist=v.findViewById(R.id.inprogressitm);
        swip_inp=v.findViewById(R.id.swipinpro);

        rv_inplist.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    private void searchview(View v) {
        search_inprog=v.findViewById(R.id.search_inprog);
        search_inprog.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                rv_inplist.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
                rv_inplist.setVisibility(View.VISIBLE);
            }
        });
    }
    private void filter(String word) {
        ArrayList<InpModel> filterwordlist=new ArrayList<>();
        for (InpModel words : Inplist)
        {
            if (words.getCustomerName().toLowerCase().contains(word.toLowerCase())||words.getMobile().toLowerCase().contains(word.toLowerCase())
                /*||words.getAddress().toLowerCase().contains(word.toLowerCase())*/)
            {
                filterwordlist.add(words);
            }
        }

        inprocessListAdapter.filterListFun(filterwordlist);
        inprocessListAdapter.notifyDataSetChanged();


    }
}