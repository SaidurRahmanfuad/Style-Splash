package com.saidur.stylesplash.ui.report.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.report.model.SorData;

import java.util.List;

public class SOReportAdapter extends RecyclerView.Adapter<SOReportAdapter.SorVH> {

    Context context;
    List<SorData> sorList;

    public SOReportAdapter(Context context/*, List<SorData> sorList*/) {
        this.context = context;
        //this.sorList = sorList;
    }
    public void setDataList(List<SorData> sorList) {
        this.sorList = sorList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SorVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recy_stuffreportitem, parent, false);
        SorVH vh = new SorVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SorVH holder, int position) {
        if (sorList != null) {
            SorData ct = sorList.get(position);
            holder.oidTv.setText(ct.getOid());
            holder.sr_custnameTv.setText(ct.getCustomerName());
            holder.sr_custmobTv.setText(ct.getMobile());

            holder.sr_amountTv.setText(ct.gettAmount());
            holder.sr_dateTv.setText(ct.getoDate());



            if(ct.getStatus().equals("1"))
            {
                holder.sr_statusTv.setText("In Process");
                holder.sr_statusTv.setTextColor(ContextCompat.getColor(context,R.color.white));
                holder.sr_statusTv.setBackgroundResource(R.drawable.trcknmbr_inprog);
            }
            else if(ct.getStatus().equals("2"))
            {
                holder.sr_statusTv.setText("Sale");
                holder.sr_statusTv.setTextColor(ContextCompat.getColor(context,R.color.white));
                holder.sr_statusTv.setBackgroundResource(R.drawable.tracknmbr_bg);
            }
            else {
                holder.sr_statusTv.setText("Cancel");
                holder.sr_statusTv.setTextColor(ContextCompat.getColor(context,R.color.white));
                holder.sr_statusTv.setBackgroundResource(R.drawable.trcknmbr_cancel);
            }


        } else {
            Toast.makeText(context, "Order Not Found", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        if(sorList!=null)
        { return sorList.size();}
        else {
            return 0;
        }

    }

  /*  public void filterListFun(ArrayList<StockData> FilterItem)
    {
        sorList=FilterItem;
        notifyDataSetChanged();
    }*/
    public class SorVH extends RecyclerView.ViewHolder {

        TextView oidTv, sr_custnameTv, sr_custmobTv,sr_statusTv,sr_amountTv,sr_dateTv;


        public SorVH(@NonNull View v) {
            super(v);
            oidTv = v.findViewById(R.id.oid);
            sr_custnameTv = v.findViewById(R.id.sr_custname);
            sr_custmobTv = v.findViewById(R.id.sr_custmob);
            sr_statusTv = v.findViewById(R.id.sr_status);
            sr_amountTv = v.findViewById(R.id.sr_amount);
            sr_dateTv = v.findViewById(R.id.sr_date);

        }
    }
}
