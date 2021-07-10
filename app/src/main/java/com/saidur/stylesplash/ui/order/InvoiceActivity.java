package com.saidur.stylesplash.ui.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.saidur.stylesplash.BuildConfig;
import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.order.adapter.ItemsAdapter;
import com.saidur.stylesplash.ui.order.adapter.ProductsAdapter;
import com.saidur.stylesplash.ui.order.invoice.ProductData;
import com.saidur.stylesplash.ui.order.network.OrderListData;
import com.saidur.stylesplash.utils.Const;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class InvoiceActivity extends AppCompatActivity {
LinearLayout invlayout;
Button sharereport;
String inDataInvno,postOid,postiOid,order;
OrderListVM orderListVM;
String oCode,oDate,tAmount,subtAmount,paidAmount,dueAmount,scost,dOption,customerName,mobile,Doption;
TextView invno,billersName,billersMob,reportdate,totalprice,subtotal,paid,due,/*doption,*/Scost,inv;

ProgressDialog pd;
ProductsAdapter productsAdapter;
RecyclerView rv_product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        pd=new ProgressDialog(InvoiceActivity.this);
        pd.setMessage("Preparing...");
        pd.setCancelable(false);

        orderListVM=new ViewModelProvider(InvoiceActivity.this).get(OrderListVM.class);
        initView();
        checkfromwhere();

        sharereport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laytoimg();
            }
        });
    }

    private void checkfromwhere() {
        switch (Const.gotoinv)
        {
            case "AddOrderAdapter":
                inv.setText("Order Details");
                Intent in=getIntent();
                postOid=in.getStringExtra("oid");
               // Toast.makeText(this, "orderid "+postOid, Toast.LENGTH_SHORT).show();
                order=in.getStringExtra("order");
                pd.show();
                sharereport.setVisibility(View.GONE);
                orderListVM.OrderViewApiCall(postOid);
                orderListVM.getOrderViewObserver().observe(InvoiceActivity.this, new Observer<OrderListData>() {
                    @Override
                    public void onChanged(OrderListData orderListData) {

                        if(orderListData!=null)
                        {
                            pd.dismiss();
                            //Doption=orderListData.getdOption();
                            //doption.setText(Doption);
                            scost=orderListData.getScost();
                            Scost.setText(scost);
                            oCode=orderListData.getoCode();
                            invno.setText(oCode);
                            oDate=orderListData.getoDate();
                            reportdate.setText(oDate);
                            tAmount=orderListData.gettAmount();
                            totalprice.setText(tAmount);
                            paidAmount=orderListData.getPaidAmount();
                            paid.setText(paidAmount);
                            dueAmount=orderListData.getDueAmount();
                            due.setText(dueAmount);
                            scost=orderListData.getScost();
                            dOption=orderListData.getdOption();
                            customerName=orderListData.getCustomerName();
                            billersName.setText(customerName);
                            mobile=orderListData.getMobile();
                            billersMob.setText(mobile);

                            int st=Integer.parseInt(tAmount)-Integer.parseInt(scost);
                            subtAmount=String.valueOf(st);
                            subtotal.setText(subtAmount);

                        }

                    }
                });
                orderListVM.getProductViewObserver().observe(InvoiceActivity.this, new Observer<List<ProductData>>() {
                    @Override
                    public void onChanged(List<ProductData> productData) {

                        if(productData!=null)
                        {
                            productsAdapter.setProductsList(productData);
                            rv_product.setAdapter(productsAdapter);
                        }

                    }
                });

                break;
            case "AddOrderFragment":
                inv.setText("Invoice");
                Intent iin=getIntent();
                postiOid=iin.getStringExtra("ioid");
              //  Toast.makeText(InvoiceActivity.this, "from fragment ", Toast.LENGTH_SHORT).show();
                pd.show();
                orderListVM.OrderViewApiCall(postiOid);
                orderListVM.getOrderViewObserver().observe(InvoiceActivity.this, new Observer<OrderListData>() {
                    @Override
                    public void onChanged(OrderListData orderListData) {
                      //  Toast.makeText(InvoiceActivity.this, "observe", Toast.LENGTH_SHORT).show();
                        if(orderListData!=null)
                        {
                            pd.dismiss();


                           // Doption=orderListData.getdOption();
                            //doption.setText(Doption);
                            scost=orderListData.getScost();
                            Scost.setText(scost);
                            oCode=orderListData.getoCode();
                            invno.setText(oCode);
                            oDate=orderListData.getoDate();
                            reportdate.setText(oDate);

                            tAmount=orderListData.gettAmount();
                            totalprice.setText(tAmount);

                            paidAmount=orderListData.getPaidAmount();
                            paid.setText(paidAmount);
                            dueAmount=orderListData.getDueAmount();
                            due.setText(dueAmount);
                            scost=orderListData.getScost();
                            dOption=orderListData.getdOption();
                            customerName=orderListData.getCustomerName();
                            billersName.setText(customerName);
                            mobile=orderListData.getMobile();
                            billersMob.setText(mobile);

                            int st=Integer.parseInt(tAmount)-Integer.parseInt(scost);
                            subtAmount=String.valueOf(st);
                            subtotal.setText(subtAmount);
                        }


                    }
                });
                orderListVM.getProductViewObserver().observe(InvoiceActivity.this, new Observer<List<ProductData>>() {
                    @Override
                    public void onChanged(List<ProductData> productData) {

                        if(productData!=null)
                        {
                            productsAdapter.setProductsList(productData);
                            rv_product.setAdapter(productsAdapter);
                        }

                    }
                });
        }
    }

    public void initView() {
        invlayout=findViewById(R.id.invlayout);
        sharereport=findViewById(R.id.sharereport);

        invno=findViewById(R.id.invno);
        billersName=findViewById(R.id.billersName);
        billersMob=findViewById(R.id.billersMob);
        reportdate=findViewById(R.id.reportdate);
        totalprice=findViewById(R.id.totalprice);
        subtotal=findViewById(R.id.subtotalprice);
        paid=findViewById(R.id.paid);
        due=findViewById(R.id.due);
      //  doption=findViewById(R.id.doption);
        Scost=findViewById(R.id.scost);
        inv=findViewById(R.id.inv);

        rv_product=findViewById(R.id.invoicerecler);
        rv_product.setLayoutManager(new LinearLayoutManager(InvoiceActivity.this));
        productsAdapter=new ProductsAdapter(InvoiceActivity.this);
    }

    private void laytoimg() {
        invlayout.setDrawingCacheEnabled(true);
        invlayout.buildDrawingCache();
        Bitmap bm = invlayout.getDrawingCache();
        try {
            File file = new File(this.getExternalCacheDir(), File.separator +inDataInvno+".jpg");
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);

            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID +".provider", file);

            intent.putExtra(Intent.EXTRA_STREAM, photoURI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setType("image/jpg");

            startActivity(Intent.createChooser(intent, "Share image via"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        InvoiceActivity.this.finish();
    }
}