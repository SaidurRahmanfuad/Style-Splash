package com.saidur.stylesplash.ui.order.trackorder.cancle.adapter;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.customer.network.CustomerListData;
import com.saidur.stylesplash.ui.order.trackorder.cancle.model.CancleData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CancelListAdapter extends RecyclerView.Adapter<CancelListAdapter.CancelorderVH>{


    Context context;
    Application application;
    List<CancleData> cancelList;


    public CancelListAdapter(Context context/*, List<CancleData> cancelList*/) {
        this.context = context;
        //this.cancelList = cancelList;
    }
    public void filterListFun(ArrayList<CancleData> FilterItem)
    {
        cancelList=FilterItem;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CancelorderVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recy_allcancelitem, parent, false);
        CancelorderVH vh = new CancelorderVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CancelorderVH holder, int position) {
        if (cancelList != null) {
            CancleData ct = cancelList.get(position);
            holder.ocodeTv.setText(ct.getoCode());
            holder.custNameTv.setText(ct.getCustomerName());
            holder.custNumberTv.setText(ct.getMobile());
            holder.priceTv.setText(String.valueOf(ct.gettAmount()));
            holder.dateTv.setText(parseDateandTime(ct.getoDate(),"dd-MM-yyy"));
           // holder.deliveryTv.setText(ct.getdOption());
            //    holder.statusTv.setText(ct.getStatus());

            holder.oid = ct.getOid();
           // Toast.makeText(context, "status "+ct.getStatus(), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, "No Order Found", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public int getItemCount() {
        if (cancelList != null) {
            return cancelList.size();
        } else {
            return 0;
        }

    }

    public void setCancelList(List<CancleData> cancelLists) {
        cancelList = cancelLists;
        notifyDataSetChanged();
    }


    public class CancelorderVH extends RecyclerView.ViewHolder {

        TextView ocodeTv, custNameTv, custNumberTv, priceTv, dateTv, deliveryTv, statusTv;
        String oid;

        public CancelorderVH(@NonNull View v) {
            super(v);
            ocodeTv = v.findViewById(R.id.cancelnumber);
            custNameTv = v.findViewById(R.id.cancelName);
            custNumberTv = v.findViewById(R.id.cancelMobile);
            priceTv = v.findViewById(R.id.cancelprice);
            dateTv = v.findViewById(R.id.cancelDate);
        }
    }

    public String parseDateandTime(String dates, String outputPattern) {
        String inputPattern = "yyyy-MM-dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(dates);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

}
