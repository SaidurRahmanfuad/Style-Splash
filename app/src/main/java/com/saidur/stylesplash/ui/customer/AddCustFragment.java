package com.saidur.stylesplash.ui.customer;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.customer.network.CustomerListData;
import com.saidur.stylesplash.utils.Const;
import com.saidur.stylesplash.utils.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class AddCustFragment extends Fragment {

    public AddCustFragment() {
        // Required empty public constructor
    }

    Session session;
    HashMap<String, String> logedinUInfo;
    public TextInputEditText custNameTv, custemailTv,/* custNumberTv,*/ custAddTv, custBalanceTv;
    private AutoCompleteTextView custNumberTv;
    private Button savecust;
    String uid, postname, postmobile, postemail, postadd, postbal;
    CustListVM custListVM;
    ProgressDialog pd;
    String msg,tdayshow;
    TextView datecusTv;
    ArrayAdapter<CustomerListData> custAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_cust, container, false);
        custListVM = new ViewModelProvider(this).get(CustListVM.class);
        logedinUInfo = new HashMap<>();
        session = new Session(getActivity());
        logedinUInfo = session.getUserInfo();
        uid = logedinUInfo.get(Session.UID);

        pd = new ProgressDialog(getContext());
        pd.setCancelable(false);
        pd.setMessage("Please wait...");
        pd.show();

        initView(v);
        custListVM.savecustObserver().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                msg = s;
                if (msg.equals("Ok")) {
                    pd.dismiss();
                    Toast.makeText(getActivity(), "Customer Saved", Toast.LENGTH_SHORT).show();
                    custNameTv.setText("");
                    custNumberTv.setText("");
                    custAddTv.setText("");
                   // Navigation.findNavController(v).navigate(R.id.action_men_addcust_to_nav_custlist);
                }
            }
        });
        custListVM.AllCustApiCall("fromaddcustomer");
        custListVM.CustloadDoneObserver().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s.equals("lcomplete"))
                {
                    pd.dismiss();
                }if(s.equals("lincomplete")) {
                    pd.dismiss();
                    Toast.makeText(getActivity(), "Data Not Loded correctly", Toast.LENGTH_SHORT).show();
                }

            }
        });
        custListVM.getAllcustObserver().observe(getViewLifecycleOwner(), new Observer<List<CustomerListData>>() {
            @Override
            public void onChanged(List<CustomerListData> customerListData) {
                if(customerListData!=null)
                {
                    custAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_product, customerListData );

                    custNumberTv.setAdapter(custAdapter);
                    custNumberTv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            CustomerListData cmm = (CustomerListData) custAdapter.getItem(position);
                            custNameTv.setText(cmm.getCustomerName());
                            custAddTv.setText(cmm.getAddress());
                        }
                    });
                }
            }
        });
        return v;
    }

    public void initView(View v) {

        tdayshow = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        custNameTv = v.findViewById(R.id.add_custName);
        custNumberTv = v.findViewById(R.id.add_custmobile);
        custNumberTv.dismissDropDown();
        custAddTv = v.findViewById(R.id.add_custadd);
        savecust = v.findViewById(R.id.add_custSave);
        datecusTv = v.findViewById(R.id.datecust);
        datecusTv.setText(tdayshow);


        savecust.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(getContext());
                pd.setCancelable(false);
                pd.setMessage("Customer Saving...");
                pd.show();

                postname = custNameTv.getText().toString();
                postmobile = custNumberTv.getText().toString();
                postadd = custAddTv.getText().toString();

                HashMap<String, String> map = new HashMap<>();
                map.put("customerName", postname);
                map.put("mobile", postmobile);
                map.put("address", postadd);
                map.put("uid", uid);
                custListVM.SaveCustApiCall(map);

            }
        });
    }
}