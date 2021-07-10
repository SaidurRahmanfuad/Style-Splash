package com.saidur.stylesplash.ui.order.adapter;

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
import com.saidur.stylesplash.ui.product.network.StockData;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemVH> {
    Context context;
    //  ArrayList<Invoice_Single_item> invoiceItemList;
    ArrayList<StockData> ItemList;

    public ItemsAdapter(Context context, ArrayList<StockData> ItemList) {
        this.context = context;
        this.ItemList = ItemList;
    }

    @NonNull
    @Override
    public ItemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.recy_items,parent,false);
        ItemVH vh=new ItemVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVH holder, int position) {
        StockData insi=ItemList.get(position);
        //holder.invoice_item_id.setText(insi.getProductId());
        //holder.invoice_item_id.setText(ItemList.get(position));
        holder.invoice_item_name.setText(insi.getProductName());
        holder.invoice_item_price.setText(String.valueOf(insi.getSprice()));
        holder.invoice_item_subtotal.setText(String.valueOf(insi.getSubtotal()));
        holder.invoice_itemintoqty.setText(insi.getTotalPices());
        holder.invoice_item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, "delete"+getItemId(position), Toast.LENGTH_SHORT).show();
              //  Toast.makeText(context, "delete"+ItemList.get(position).getProduct(), Toast.LENGTH_SHORT).show();
                // invoiceItemList.get(position).getId().position();
                ItemList.remove(position);
                //notifyItemRangeRemoved(0,getItemCount());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ItemList.size();
    }

    public class ItemVH extends RecyclerView.ViewHolder {

        private TextView invoice_item_name,invoice_item_price,invoice_item_subtotal,invoice_item_id,invoice_itemintoqty;
        private ImageView invoice_item_delete;
        public ItemVH(@NonNull View v) {
            super(v);

            invoice_item_id=v.findViewById(R.id.invoice_item_id);
            invoice_item_name=v.findViewById(R.id.invoice_item_name);
            invoice_item_price=v.findViewById(R.id.invoice_item_price);
            invoice_item_subtotal=v.findViewById(R.id.invoice_item_subtotal);
            invoice_itemintoqty=v.findViewById(R.id.invoice_itemintoqty);
            invoice_item_delete=v.findViewById(R.id.invoice_item_delete);
        }
    }
}
