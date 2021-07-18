package com.saidur.stylesplash.ui.order.adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.order.InvoiceActivity;
import com.saidur.stylesplash.ui.order.OrderListVM;
import com.saidur.stylesplash.ui.order.network.OrderListData;
import com.saidur.stylesplash.ui.order.trackorder.Counter;
import com.saidur.stylesplash.ui.product.network.StockData;
import com.saidur.stylesplash.utils.Const;
import com.saidur.stylesplash.utils.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class AllOrderAdapter extends RecyclerView.Adapter<AllOrderAdapter.AllorderVH>{


    Context context;
    Application application;
    List<OrderListData> orderList;


 /*   public AllOrderAdapter(Context context,Counter counter) {
        this.context = context;
        this.counter = counter;
    }*/

    public AllOrderAdapter(Context context, List<OrderListData> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public AllorderVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recy_allorderlist, parent, false);
        AllorderVH vh = new AllorderVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AllorderVH holder, int position) {
        if (orderList != null) {
            OrderListData ct = orderList.get(position);
           // counter.ToalAllOrder(112);
            holder.ocodeTv.setText(ct.getoCode());
            holder.custNameTv.setText(ct.getCustomerName());
            holder.custNumberTv.setText(ct.getMobile());
            holder.priceTv.setText(String.valueOf(ct.gettAmount()));
            holder.dateTv.setText(ct.getoDate());
            holder.deliveryTv.setText(ct.getdOption());
           // holder.statusTv.setText(ct.getStatus());


            holder.oid = ct.getOid();
            holder.customerID = ct.getCustid();
            holder.totalPrice = ct.gettAmount();
            holder.shiping_cost = ct.getScost();
            holder.dOption = ct.getdOption();
            Session session=new Session(context);
            HashMap<String ,String> userdata;
            userdata=session.getUserInfo();
            holder.uid = userdata.get(Session.UID);

            String onprocess = "On Process";
            String salse = "Sales Order";
            String cancel = "Cancel Order";
            if (ct.getStatus().equals("1")) {
                holder.statusTv.setText(onprocess);
                holder.statusTv.setBackgroundResource(R.drawable.trcknmbr_inprog);
            }
            else if (ct.getStatus().equals("2")) {
                holder.statusTv.setText(salse);
                holder.statusTv.setBackgroundResource(R.drawable.tracknmbr_bg);
            }
           else if (ct.getStatus().equals("5")) {
                holder.statusTv.setText(cancel);
                holder.statusTv.setBackgroundResource(R.drawable.trcknmbr_cancel);

            }

        } else {
            Toast.makeText(context, "No Order Found", Toast.LENGTH_SHORT).show();
        }
        holder.orderActionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.orderactionmenu, popupMenu.getMenu());
                popupMenu.show();

                int position = holder.getAdapterPosition();
                String today=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                OrderListVM ovm = new OrderListVM(application);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                          /*  case R.id.action_edit:
                                Intent intent = new Intent(context, EditProductActivity.class);
                                intent.putExtra("id", ct.getCustomerID());
                                intent.putExtra("name", ct.getCustomerName());
                                intent.putExtra("address", ct.getAddress());
                                intent.putExtra("balance", ct.getBalance());
                                intent.putExtra("mobile", ct.getMobile());
                                intent.putExtra("email", ct.getEmail());

                                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                break;*/
                            case R.id.action_view:
                                Const.gotoinv="AddOrderAdapter";
                                Intent gotoinv=new Intent(context, InvoiceActivity.class);
                                gotoinv.putExtra("oid",holder.oid);
                                gotoinv.putExtra("order","fromadapter");
                                context.startActivity(gotoinv);
                                break;
                            case R.id.action_sale:
                               // ovm.getProductViewObserver(holder.oid);
                                HashMap<String,String> aftersl=new HashMap<>();
                                aftersl.put("date",today);
                                aftersl.put("customerID",holder.customerID);
                                aftersl.put("tPrice",holder.totalPrice);
                                aftersl.put("shiping_cost",holder.shiping_cost);
                                aftersl.put("dOption",holder.dOption);
                                aftersl.put("note","sale order");
                                aftersl.put("uid",holder.uid);
                                aftersl.put("oid",holder.oid);

                                ovm.SaveSaleApiCall(aftersl,holder.oid);
                                notifyDataSetChanged();
                                break;
                            case R.id.action_cancle:
                                Toast.makeText(context, "call cancle", Toast.LENGTH_SHORT).show();
                                ovm.OrderCancleApiCall(holder.oid);
                                break;

                            case R.id.action_delete:
                                Toast.makeText(context, "call del", Toast.LENGTH_SHORT).show();
                                orderList.remove(position);
                                notifyItemRemoved(position);
                                ovm.OrderDeleteApiCall(holder.oid);
                                break;
                        }
                        return false;
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        if (orderList != null) {
            return orderList.size();

        } else {
            return 0;
        }

    }
 /*   public void setToalOrder(List<OrderListData> orderlist) {
        orderList = orderlist;
        counter.ToalAllOrder(orderList.size());
        notifyDataSetChanged();
    }*/

    public void setOrderList(List<OrderListData> orderlist) {
        orderList = orderlist;
        notifyDataSetChanged();
    }
    public void filterListFun(ArrayList<OrderListData> FilterItem)
    {
        orderList=FilterItem;
        notifyDataSetChanged();
    }

    public class AllorderVH extends RecyclerView.ViewHolder {

        TextView ocodeTv, custNameTv, custNumberTv, priceTv, dateTv, deliveryTv, statusTv;
        ImageView categoryDelet;
        LinearLayout orderActionTv;
        String oid,customerID,totalPrice,shiping_cost,dOption,uid;

        public AllorderVH(@NonNull View v) {
            super(v);
            ocodeTv = v.findViewById(R.id.ocodeTv);
            custNameTv = v.findViewById(R.id.custNameTv);
            custNumberTv = v.findViewById(R.id.custNumberTv);
            priceTv = v.findViewById(R.id.priceTv);
            dateTv = v.findViewById(R.id.dateTv);
            deliveryTv = v.findViewById(R.id.deliveryTv);
            statusTv = v.findViewById(R.id.statusTv);
            orderActionTv = v.findViewById(R.id.orderAction);



        }
    }


}