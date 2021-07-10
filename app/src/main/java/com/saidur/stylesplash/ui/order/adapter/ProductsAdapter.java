package com.saidur.stylesplash.ui.order.adapter;

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
import com.saidur.stylesplash.ui.order.invoice.ProductData;
import com.saidur.stylesplash.ui.order.trackorder.cancle.model.CancleData;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.PVH>{


    Context context;
    List<ProductData> products;


    public ProductsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recy_invoiceitem, parent, false);
        PVH vh = new PVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PVH holder, int position) {
        if (products != null) {
            ProductData ct = products.get(position);

            holder.itemNameTv.setText(ct.getProductName());
            holder.itemQty.setText(ct.getoQnt());
            holder.itemUprice.setText(String.valueOf(ct.getoPrice()));
            holder.itemStotal.setText(ct.gettPrice());
            // holder.deliveryTv.setText(ct.getdOption());
            //    holder.statusTv.setText(ct.getStatus());

           // holder.oid = ct.getOid();
           // Toast.makeText(context, "status "+ct.getStatus(), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, "No Order Found", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public int getItemCount() {
        if (products != null) {
            return products.size();
        } else {
            return 0;
        }

    }

    public void setProductsList(List<ProductData> productsz) {
        products = productsz;
        notifyDataSetChanged();
    }


    public class PVH extends RecyclerView.ViewHolder {

        TextView itemNameTv, itemQty, itemUprice, itemStotal;


        public PVH(@NonNull View v) {
            super(v);

            itemNameTv = v.findViewById(R.id.itemName);
            itemQty = v.findViewById(R.id.itemQty);
            itemUprice = v.findViewById(R.id.itemUprice);
            itemStotal = v.findViewById(R.id.itemStotal);
        }
    }



}
