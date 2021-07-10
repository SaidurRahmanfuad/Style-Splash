package com.saidur.stylesplash.ui.product;

import android.app.ProgressDialog;
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
import com.saidur.stylesplash.ui.customer.network.CustomerListData;
import com.saidur.stylesplash.ui.order.OrderListVM;
import com.saidur.stylesplash.ui.order.adapter.AllOrderAdapter;
import com.saidur.stylesplash.ui.order.network.OrderListData;
import com.saidur.stylesplash.ui.product.adapter.StockAdapter;
import com.saidur.stylesplash.ui.product.network.StockData;

import java.util.ArrayList;
import java.util.List;


public class ProductStockFragment extends Fragment {

    OrderListVM orderListVM;
    RecyclerView rv_stock;
    StockAdapter stockAdapter;
    List<StockData> newStocklist;
    SwipeRefreshLayout swip_prod;
    EditText search_prod;
    ProgressDialog pd;
    public ProductStockFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_product_stock, container, false);
        pd=new ProgressDialog(getContext());
        pd.setMessage("Please wait...");
        pd.setCancelable(false);
        pd.show();

        orderListVM = new ViewModelProvider(this).get(OrderListVM.class);


        rv_stock=v.findViewById(R.id.stockrv);
        rv_stock.setLayoutManager(new LinearLayoutManager(getActivity()));
        stockAdapter=new StockAdapter(getActivity(),newStocklist);
        rv_stock.setAdapter(stockAdapter);

        swip_prod=v.findViewById(R.id.swip_prod);

        orderListVM.AllStockApiCall();

        orderListVM.StockloadDoneObserver().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s.equals("done"))
                {
                    pd.dismiss();
                }if(s.equals("notdone")) {
                    pd.dismiss();
                    Toast.makeText(getActivity(), "Data Not Loded correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });
        orderListVM.getAllStockObserver().observe(getViewLifecycleOwner(), new Observer<List<StockData>>() {
            @Override
            public void onChanged(List<StockData> stockData) {
                if(stockData!=null)
                {
                    newStocklist=stockData;
                    stockAdapter.setStockList(stockData);

                }
            }
        });
        swip_prod.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                orderListVM.AllStockApiCall();
                swip_prod.setRefreshing(false);
            }
        });
        search(v);
        return v;
    }

    private void search(View v) {
        search_prod=v.findViewById(R.id.search_prod);
        search_prod.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                rv_stock.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

                filter(editable.toString());
                rv_stock.setVisibility(View.VISIBLE);
            }
        });
    }

    private void filter(String word) {
        ArrayList<StockData> filterwordlist=new ArrayList<>();
        for (StockData words : newStocklist)
        {
            if (words.getProductName().toLowerCase().contains(word.toLowerCase()))
            {
                filterwordlist.add(words);
            }
        }
        stockAdapter.filterListFun(filterwordlist);
        stockAdapter.notifyDataSetChanged();


    }
}