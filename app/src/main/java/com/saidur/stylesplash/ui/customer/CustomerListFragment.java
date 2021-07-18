package com.saidur.stylesplash.ui.customer;

import android.app.ProgressDialog;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.customer.adapter.AllCustomerAdapter;
import com.saidur.stylesplash.ui.customer.network.CustomerListData;
import com.saidur.stylesplash.ui.order.OrderListVM;
import com.saidur.stylesplash.ui.order.adapter.AllOrderAdapter;
import com.saidur.stylesplash.ui.order.network.OrderListData;

import java.util.ArrayList;
import java.util.List;


public class CustomerListFragment extends Fragment {

    CustListVM custListVM;
    RecyclerView rv_allcust;
    AllCustomerAdapter allCustomerAdapter;
    List<CustomerListData> newCustlist;
    SwipeRefreshLayout swip_cust;
    EditText search_cust;
    ProgressDialog pd;

    public CustomerListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_customer_list, container, false);
        pd=new ProgressDialog(getActivity());
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        pd.show();
        custListVM = new ViewModelProvider(this).get(CustListVM.class);
        custListVM.AllCustApiCall("fromcustomerlist");
        prepareRecyclerView(v);
        custListVM.getAllcustObserver().observe(getViewLifecycleOwner(), new Observer<List<CustomerListData>>() {
            @Override
            public void onChanged(List<CustomerListData> customerListData) {
                if(customerListData!=null)
                {
                    newCustlist=customerListData;
                    allCustomerAdapter.setCusttList(newCustlist);
                }
            }
        });

        custListVM.CustloadDoneObserver().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s.equals("lcomplete"))
                {
                    pd.dismiss();
                }if(s.equals("lincomplete")) {
                    pd.dismiss();
                    Toast.makeText(getActivity(), "Data Not Loded correctly", Toast.LENGTH_SHORT).show();
                }

            }
        });
        swip_cust.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                custListVM.AllCustApiCall("fromcustomerlistswip");
                swip_cust.setRefreshing(false);
            }
        });
        return v;
    }
    private void prepareRecyclerView(View v) {
        search_cust = v.findViewById(R.id.search_cust);
        rv_allcust = v.findViewById(R.id.allcustrv);
        swip_cust = v.findViewById(R.id.swip_cust);
       // rv_allcust.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rv_allcust.setLayoutManager(new LinearLayoutManager(getActivity()));
        allCustomerAdapter = new AllCustomerAdapter(getActivity(), newCustlist);
        rv_allcust.setAdapter(allCustomerAdapter);

        search_cust.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                rv_allcust.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

                filter(editable.toString());
                rv_allcust.setVisibility(View.VISIBLE);
            }
        });
    }

    private void filter(String word) {
        ArrayList<CustomerListData> filterwordlist=new ArrayList<>();
        for (CustomerListData words : newCustlist)
        {
            if (words.getCustomerName().toLowerCase().contains(word.toLowerCase())||words.getMobile().toLowerCase().contains(word.toLowerCase())
            /*||words.getAddress().toLowerCase().contains(word.toLowerCase())*/)
            {
                filterwordlist.add(words);
            }
        }

        allCustomerAdapter.filterListFun(filterwordlist);
        allCustomerAdapter.notifyDataSetChanged();


    }
}