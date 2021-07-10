package com.saidur.stylesplash.ui.product.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.customer.adapter.AllCustomerAdapter;
import com.saidur.stylesplash.ui.customer.network.CustomerListData;
import com.saidur.stylesplash.ui.product.network.StockData;

import java.util.ArrayList;
import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.StockVH> {

    Context context;
    List<StockData> stockList;

    public StockAdapter(Context context, List<StockData> stockList) {
        this.context = context;
        this.stockList = stockList;
    }
    public void setStockList(List<StockData> stockList) {
        this.stockList = stockList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StockVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recy_stocklist, parent, false);
        StockVH vh = new StockVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull StockVH holder, int position) {
        if (stockList != null) {
            StockData ct = stockList.get(position);
            holder.prodcodeTv.setText(ct.getProductcode());
            holder.stockprodnameTv.setText(ct.getProductName());
            holder.totalpicesTv.setText(ct.getTotalPices());
            holder.totaldamageTv.setText(ct.getDtquantity());


        } else {
            Toast.makeText(context, "No Order Found", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        if(stockList!=null)
        { return stockList.size();}
        else {
            return 0;
        }

    }

    public void filterListFun(ArrayList<StockData> FilterItem)
    {
        stockList=FilterItem;
        notifyDataSetChanged();
    }
    public class StockVH extends RecyclerView.ViewHolder {

        TextView prodcodeTv, stockprodnameTv, totalpicesTv,totaldamageTv,priceTv,dateTv,deliveryTv,statusTv;
        ImageView categoryDelet;

        public StockVH(@NonNull View v) {
            super(v);
            prodcodeTv = v.findViewById(R.id.prodcodeTv);
            stockprodnameTv = v.findViewById(R.id.stockprodnameTv);
            totalpicesTv = v.findViewById(R.id.totalpicesTv);
            totaldamageTv = v.findViewById(R.id.totaldamageTv);

        }
    }
}
