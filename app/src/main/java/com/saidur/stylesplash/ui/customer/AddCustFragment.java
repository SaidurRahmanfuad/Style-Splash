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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.saidur.stylesplash.R;
import com.saidur.stylesplash.utils.Const;
import com.saidur.stylesplash.utils.Session;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class AddCustFragment extends Fragment {

    public AddCustFragment() {
        // Required empty public constructor
    }

    Session session;
    HashMap<String, String> logedinUInfo;
    public TextInputEditText custNameTv, custemailTv, custNumberTv, custAddTv, custBalanceTv;
    private Button savecust;
    String uid, postname, postmobile, postemail, postadd, postbal;
    CustListVM custListVM;
    ProgressDialog pd;
    String msg,tdayshow;
    TextView datecusTv;

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
        pd.setMessage("Customer Saving...");
        pd.setCancelable(false);
        initView(v);
        custListVM.savecustObserver().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                msg = s;
                if (msg.equals("Ok")) {
                    pd.dismiss();
                    custNameTv.setText("");
                    custNumberTv.setText("");
                   // custemailTv.setText("");
                    custAddTv.setText("");
                   // custBalanceTv.setText("");
                   // Navigation.findNavController(v).navigate(R.id.action_men_addcust_to_nav_custlist);
                }
            }
        });

        return v;
    }

    public void initView(View v) {

        tdayshow = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        custNameTv = v.findViewById(R.id.add_custName);
       // custemailTv = v.findViewById(R.id.add_custemail);
        custNumberTv = v.findViewById(R.id.add_custmobile);
        custAddTv = v.findViewById(R.id.add_custadd);
       // custBalanceTv = v.findViewById(R.id.add_custbal);
        savecust = v.findViewById(R.id.add_custSave);
        datecusTv = v.findViewById(R.id.datecust);
        datecusTv.setText(tdayshow);


        savecust.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pd.show();

                postname = custNameTv.getText().toString();
                postmobile = custNumberTv.getText().toString();
               // postemail = custemailTv.getText().toString();
                postadd = custAddTv.getText().toString();
               // postbal = custBalanceTv.getText().toString();

                HashMap<String, String> map = new HashMap<>();
                map.put("customerName", postname);
                map.put("mobile", postmobile);
                //map.put("email", postemail);
                map.put("address", postadd);
               // map.put("balance", postbal);
                map.put("uid", uid);
                custListVM.SaveCustApiCall(map);

            }
        });
    }
}