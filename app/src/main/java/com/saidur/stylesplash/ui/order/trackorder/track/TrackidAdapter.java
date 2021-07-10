package com.saidur.stylesplash.ui.order.trackorder.track;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.order.OrderListVM;
import com.saidur.stylesplash.utils.Const;

import java.util.List;

public class TrackidAdapter extends RecyclerView.Adapter<TrackidAdapter.TIVH> {
    Application ap;
    Context context;
    List<TrackData> trackidList;

    private ClickInterface itemListener;

    public TrackidAdapter(Context context,ClickInterface itemListener) {
        this.context = context;
        this.itemListener = itemListener;
    }
    public TrackidAdapter(Context context, List<TrackData> trackidList,ClickInterface itemListener) {
        this.context = context;
        this.trackidList = trackidList;
        this.itemListener = itemListener;
        notifyDataSetChanged();
    }
   /* public void setTrackidList(List<TrackData> trackidList) {
        this.trackidList = trackidList;
        notifyDataSetChanged();
    }*/

    @NonNull
    @Override
    public TIVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recy_trackkid, parent, false);
        TIVH vh = new TIVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TIVH holder, int position) {
        if (trackidList != null) {
            TrackData ct = trackidList.get(position);
            holder.trackid.setText(ct.getoCode());
            holder.trackname.setText(ct.getCustomerName());
            holder.trackdate.setText(ct.getoDate());

            holder.trackidcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemListener.onItemClick(position,ct.getoCode());
                }
            });


        } else {
            Toast.makeText(context, "No Order Found", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        if (trackidList != null) {
            return trackidList.size();
        } else {
            return 0;
        }

    }

    public class TIVH extends RecyclerView.ViewHolder {

        TextView trackid, trackname, trackdate;
        CardView trackidcard;


        public TIVH(@NonNull View v) {
            super(v);
            trackid = v.findViewById(R.id.trackid);
            trackname = v.findViewById(R.id.trackname);
            trackdate = v.findViewById(R.id.trackdate);
            trackidcard = v.findViewById(R.id.trackidcard);
        }
    }
}
