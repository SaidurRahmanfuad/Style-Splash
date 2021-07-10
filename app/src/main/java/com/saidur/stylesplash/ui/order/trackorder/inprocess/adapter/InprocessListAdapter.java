package com.saidur.stylesplash.ui.order.trackorder.inprocess.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.customer.network.CustomerListData;
import com.saidur.stylesplash.ui.order.trackorder.inprocess.InprocessActivity;
import com.saidur.stylesplash.ui.order.trackorder.inprocess.model.InpModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InprocessListAdapter extends RecyclerView.Adapter<InprocessListAdapter.InproorderVH> {


    Context context;
    List<InpModel> inprocessList;


    public InprocessListAdapter(Context context) {
        this.context = context;
    }

    public void filterListFun(ArrayList<InpModel> FilterItem) {
        inprocessList = FilterItem;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InproorderVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recy_allprocessitem, parent, false);
        InproorderVH vh = new InproorderVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull InproorderVH holder, int position) {
        if (inprocessList != null) {
            InpModel ct = inprocessList.get(position);
            holder.ocodeTv.setText(ct.getoCode());
            holder.custNameTv.setText(ct.getCustomerName());
            holder.custNumberTv.setText(ct.getMobile());
            holder.priceTv.setText(String.valueOf(ct.gettAmount()));
            holder.dateTv.setText(parseDateandTime(ct.getoDate(),"dd-MM-yyy"));
            // holder.deliveryTv.setText(ct.getdOption());
            //    holder.statusTv.setText(ct.getStatus());

            holder.oid = ct.getOid();
            // Toast.makeText(context, "status "+ct.getStatus(), Toast.LENGTH_SHORT).show();
            holder.ll2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // context.startActivity(new Intent(context, InprocessActivity.class));
                    Intent in=new Intent(context,InprocessActivity.class);
                    in.putExtra("date",ct.getoDate());
                    in.putExtra("payment",String.valueOf(ct.gettAmount()));
                    context.startActivity(in);
                }
            });
        } else {
            Toast.makeText(context, "No Order Found", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public int getItemCount() {
        if (inprocessList != null) {
            return inprocessList.size();
        } else {
            return 0;
        }

    }

    public void setInprocessList(List<InpModel> inprocessLists) {
        inprocessList = inprocessLists;
        notifyDataSetChanged();
    }


    public class InproorderVH extends RecyclerView.ViewHolder {

        TextView ocodeTv, custNameTv, custNumberTv, priceTv, dateTv, deliveryTv, statusTv;
        String oid;
        CardView ll2;

        public InproorderVH(@NonNull View v) {
            super(v);
            ocodeTv = v.findViewById(R.id.inptracknumber);
            custNameTv = v.findViewById(R.id.inpcustname);
            custNumberTv = v.findViewById(R.id.inpnumber);

            priceTv = v.findViewById(R.id.inpprice);
            dateTv = v.findViewById(R.id.inpdate);
            ll2 = v.findViewById(R.id.ll2);
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
