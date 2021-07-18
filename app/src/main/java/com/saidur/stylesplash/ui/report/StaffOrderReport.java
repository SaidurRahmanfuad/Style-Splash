package com.saidur.stylesplash.ui.report;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.report.adapter.SOReportAdapter;
import com.saidur.stylesplash.ui.report.model.SorData;
import com.saidur.stylesplash.ui.report.model.SorTotal;
import com.saidur.stylesplash.ui.report.model.Stuff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class StaffOrderReport extends AppCompatActivity {
    AutoCompleteTextView add_stuff_spinner;
    StuffVM stuffVM;
    ArrayAdapter<Stuff> stuffAdapter;
    String stuffId, stuffName, Tdate;

    TextView startdate, enddate;
    ImageView selectst, selectend;
    DatePickerDialog datePickerDialog1, datePickerDialog2;
    ProgressDialog pd,pds;
    HashMap<String, String> savemap;

    SOReportAdapter sorAdapter;
    RecyclerView rv_sor;


    TextView torder,porder,sorder,corder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_order_report);
        stuffVM = new ViewModelProvider(this).get(StuffVM.class);

        pds=new ProgressDialog(StaffOrderReport.this);
        pds.setMessage("Loading...");
        pds.setCancelable(false);
        pds.show();

        stuffVM.AllStuffApiCall();
        Tdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());


        initView();
        initDatePicker();
        stuffVM.allStuffObserver().observe(this, new Observer<List<Stuff>>() {
            @Override
            public void onChanged(List<Stuff> stuffs) {
                if (stuffs != null) {
                    pds.dismiss();

                    stuffAdapter = new ArrayAdapter<>(StaffOrderReport.this, R.layout.spinner_product, stuffs);
                    add_stuff_spinner.setAdapter(stuffAdapter);
                    add_stuff_spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            Stuff cmm = (Stuff) stuffAdapter.getItem(position);

                            stuffId = cmm.getUid();
                            stuffName = cmm.getName();
                        }
                    });
                } else {
                    Toast.makeText(StaffOrderReport.this, "No Stuff Exist", Toast.LENGTH_SHORT).show();
                }
            }
        });
        startdate.setText(Tdate);

        selectst.setOnClickListener(v -> {
            datePickerDialog1.show();
        });
        selectend.setOnClickListener(v -> {
            datePickerDialog2.show();
        });

        sorAdapter=new SOReportAdapter(StaffOrderReport.this);
        stuffVM.StuffAllOrderObserver().observe(this, new Observer<List<SorData>>() {
            @Override
            public void onChanged(List<SorData> sorData) {
                if(sorData!=null)
                {
                    pd.dismiss();
                    sorAdapter.setDataList(sorData);
                    rv_sor.setLayoutManager(new LinearLayoutManager(StaffOrderReport.this));
                    rv_sor.setAdapter(sorAdapter);
                }
                else {
                    Toast.makeText(StaffOrderReport.this, "Order Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        stuffVM.StuffCounterObserver().observe(this, new Observer<SorTotal>() {
            @Override
            public void onChanged(SorTotal sorTotal) {
                if(sorTotal!=null)
                {
                        torder.setText(String.valueOf(sorTotal.getTorder()));
                        porder.setText(String.valueOf(sorTotal.getPorder()));
                        sorder.setText(String.valueOf(sorTotal.getSorder()));
                        corder.setText(String.valueOf(sorTotal.getCorder()));

                }
                else {
                    try {
                        Toast.makeText(StaffOrderReport.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void initView() {
        add_stuff_spinner = findViewById(R.id.add_stuff_spinner);
        startdate = findViewById(R.id.startdate);
        enddate = findViewById(R.id.enddate);
        selectst = findViewById(R.id.selectst);
        selectend = findViewById(R.id.selectend);
        rv_sor = findViewById(R.id.rv_sor);

        torder = findViewById(R.id.torder);
        porder = findViewById(R.id.porder);
        sorder = findViewById(R.id.sorder);
        corder = findViewById(R.id.corder);
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                //parseDateandTime(date,"dd-MM-yyyy");
                startdate.setText(parseDateandTime(date, "dd-MM-yyyy"));
                // startdate.setText(date);

            }
        };
        DatePickerDialog.OnDateSetListener dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                enddate.setText(parseDateandTime(date, "dd-MM-yyyy"));
                // enddate.setText(date);
                // searchdateTv.setText("");
                callSearchApi();
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog1 = new DatePickerDialog(this, style, dateSetListener1, year, month, day);
        datePickerDialog2 = new DatePickerDialog(this, style, dateSetListener2, year, month, day);


    }

    private void callSearchApi() {
        pd = new ProgressDialog(StaffOrderReport.this);
        pd.setMessage("Searching...");
        pd.show();


        savemap = new HashMap<>();
        savemap.put("sdate", startdate.getText().toString());
        savemap.put("edate", enddate.getText().toString());
        savemap.put("stafid", stuffId);
        stuffVM.onlyStuffReportApiCall(savemap);

    /*    searchReport = new ArrayList<>();
        saleRAdapter2 = new SaleRAdapter(StaffOrderReport.this);

        ApiService apiService = RetrofitInstanse.getRetroClient().create(ApiService.class);
        Call<SaleReportRP> call = apiService.getAllSalesReport(Authorization, savemap);
        call.enqueue(new Callback<SaleReportRP>() {
            @Override
            public void onResponse(Call<SaleReportRP> call, Response<SaleReportRP> response) {
                if (response.isSuccessful()) {
                    pd.dismiss();
                    rv_salereport.setVisibility(View.GONE);
                    rv_srcsalereport.setVisibility(View.VISIBLE);


                    if (response.body().getData().getTotalSaleInfo().getTotalAmount() == null && response.body().getData().getTotalSaleInfo().getTotalPaid() == null &&
                            response.body().getData().getTotalSaleInfo().getDiscountAmount() == null) {
                        searchReport = response.body().getData().getSales();
                        totalsale.setText("0");
                        totalpaid.setText("0");
                        totaldue.setText("0");
                    } else {
                        searchReport = response.body().getData().getSales();

                        Log.d("search", "onResponse: " + searchReport);
                        totalsale.setText(response.body().getData().getTotalSaleInfo().getTotalAmount());
                        totalpaid.setText(response.body().getData().getTotalSaleInfo().getTotalPaid());

                        int ta = Integer.parseInt(response.body().getData().getTotalSaleInfo().getTotalAmount());
                        int tp = Integer.parseInt(response.body().getData().getTotalSaleInfo().getTotalPaid());
                        int tdu = ta - tp;
                        // totaldue.setText(totalSaleInfo.getDiscountAmount());
                        totaldue.setText(String.valueOf(tdu));


                        saleRAdapter2.setSaleReportList(searchReport);

                        //  saleRAdapter.setSaleReportList(saleReportModels);
                        // newsaleRlist = saleReportModels;


                        rv_srcsalereport.setLayoutManager(new LinearLayoutManager(ReportGenerateActivity.this));
                        rv_srcsalereport.setAdapter(saleRAdapter2);
                    }

                } else {
                    searchReport = null;
                    searchReportTotal = null;
                }
            }

            @Override
            public void onFailure(Call<SaleReportRP> call, Throwable t) {
                searchReport = null;
                searchReportTotal = null;
                Log.d("CategoryListFragment", "getCause: " + t.getCause());
                Log.d("CategoryListFragment", "getStackTrace: " + t.getStackTrace());

            }
        });*/

    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public String parseDateandTime(String dates, String outputPattern) {
        // String inputPattern = "yyyy-MM-dd";
        String inputPattern = "MMM dd yyyy";
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