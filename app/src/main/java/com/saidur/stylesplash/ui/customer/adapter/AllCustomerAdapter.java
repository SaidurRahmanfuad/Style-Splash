package com.saidur.stylesplash.ui.customer.adapter;

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
import com.saidur.stylesplash.ui.customer.network.CustomerListData;

import java.util.ArrayList;
import java.util.List;

public class AllCustomerAdapter extends RecyclerView.Adapter<AllCustomerAdapter.AllcustVH> {


    Context context;
    List<CustomerListData> custList;

    public AllCustomerAdapter(Context context, List<CustomerListData> custList) {
        this.context = context;
        this.custList = custList;
    }
    public void setCusttList(List<CustomerListData> custList) {
        this.custList = custList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AllcustVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recy_allcustomerlist, parent, false);
        AllcustVH vh = new AllcustVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AllcustVH holder, int position) {
        if (custList != null) {
            CustomerListData ct = custList.get(position);
            holder.custlNameTv.setText(ct.getCustomerName());
            holder.custlNumberTv.setText(ct.getMobile());
            holder.custladdTv.setText(ct.getAddress());


        } else {
            Toast.makeText(context, "No Order Found", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        if(custList!=null)
        { return custList.size();}
        else {
            return 0;
        }

    }

    public void filterListFun(ArrayList<CustomerListData> FilterItem)
    {
        custList=FilterItem;
        notifyDataSetChanged();
    }
    public class AllcustVH extends RecyclerView.ViewHolder {

        TextView custlNameTv, custlNumberTv, custladdTv,priceTv,dateTv,deliveryTv,statusTv;
        ImageView categoryDelet;

        public AllcustVH(@NonNull View v) {
            super(v);
            custlNameTv = v.findViewById(R.id.custlNameTv);
            custlNumberTv = v.findViewById(R.id.custlNumberTv);
            custladdTv = v.findViewById(R.id.custladdTv);

        }
    }
}
