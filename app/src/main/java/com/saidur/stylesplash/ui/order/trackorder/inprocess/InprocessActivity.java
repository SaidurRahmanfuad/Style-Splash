package com.saidur.stylesplash.ui.order.trackorder.inprocess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.saidur.stylesplash.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InprocessActivity extends AppCompatActivity {
    private CardView orderprocess, order_onprocess2;
   private String amount,date,time;
   private TextView amounttv,inproDate,inprotime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inprocess);
        Intent in=getIntent();
        amount=in.getStringExtra("payment");
        date=in.getStringExtra("date");
        initView();
        amounttv.setText(amount);
        inproDate.setText(parseDateandTime(date,"dd-MM-yyy"));
        inprotime.setText(parseDateandTime(date,"HH:mm a"));

        ApiCall();
    }

    private void ApiCall() {
     /*   if (Status.equals("1")) {
            orderprocess.setVisibility(View.VISIBLE);
            order_onprocess2.setVisibility(View.GONE);
        }
        if (Status.equals("1")) {
            order_onprocess2.setVisibility(View.VISIBLE);

        }*/
    }

    private void initView() {
        inproDate = findViewById(R.id.inproDate);
        inprotime = findViewById(R.id.inprotime);
        amounttv = findViewById(R.id.amount);
        orderprocess = findViewById(R.id.vorder_onprocess);
       // order_onprocess2 = findViewById(R.id.vorder_onprocess2);
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