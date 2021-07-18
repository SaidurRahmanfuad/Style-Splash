package com.saidur.stylesplash.ui.home;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;
import com.saidur.stylesplash.MainActivity;
import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.login.LoginVM;
import com.saidur.stylesplash.ui.login.network.LoginData;
import com.saidur.stylesplash.utils.Const;
import com.saidur.stylesplash.utils.Session;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private TextView ol, ps, to, cl, ado, adc;
    private TextInputEditText i;
    private TextView dash_todateId, dash_time;
    String date, time;
    TextView tracklin, stufforderrep, dailyreport;
    Session session;
    HashMap<String, String> usermap;
    String userrole;
    LinearLayout ll5, llforadmin;
    LoginVM loginVM;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        loginVM = new ViewModelProvider(this).get(LoginVM.class);
        session = new Session(getActivity());
        usermap = session.getUserInfo();
        userrole = usermap.get(Session.UserRole);

        date = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        time = new SimpleDateFormat("HH:mm a", Locale.getDefault()).format(new Date());


        iniTView(v);
        switch (userrole)
        {
            case "2":
                llforadmin.setVisibility(View.VISIBLE);
                ll5.setVisibility(View.VISIBLE);
                break;
            case "5":
                llforadmin.setVisibility(View.GONE);
                ll5.setVisibility(View.GONE);
                break;
        }


        return v;
    }

    private void iniTView(View v) {
        ll5 = v.findViewById(R.id.ll5);
        llforadmin = v.findViewById(R.id.llforadmin);


        dash_time = v.findViewById(R.id.dash_time);
        dash_time.setText(time);
        dash_todateId = v.findViewById(R.id.dash_todateId);
        dash_todateId.setText(date);
        ol = v.findViewById(R.id.ol);
        ps = v.findViewById(R.id.ps);
        to = v.findViewById(R.id.to);
        tracklin = v.findViewById(R.id.tracklin);
        stufforderrep = v.findViewById(R.id.stufforderrep);
        dailyreport = v.findViewById(R.id.dailyreport);
        cl = v.findViewById(R.id.cl);
        ado = v.findViewById(R.id.ado);
        adc = v.findViewById(R.id.adc);

        ol.setOnClickListener(this);
        ps.setOnClickListener(this);
        to.setOnClickListener(this);
        tracklin.setOnClickListener(this);
        cl.setOnClickListener(this);
        ado.setOnClickListener(this);
        adc.setOnClickListener(this);
        stufforderrep.setOnClickListener(this);
        dailyreport.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ol:
                Navigation.findNavController(v).navigate(R.id.goto_home_to_orderlist);

                break;
            case R.id.ps:
                Navigation.findNavController(v).navigate(R.id.goto_home_to_productstock);
                break;
            case R.id.to:
                Navigation.findNavController(v).navigate(R.id.goto_home_to_trackorderAc);
                break;
            case R.id.tracklin:
                Navigation.findNavController(v).navigate(R.id.goto_home_to_trackorderAc);
                break;
            case R.id.stufforderrep:
                Navigation.findNavController(v).navigate(R.id.goto_home_to_stuffreportorderAc);
                break;
            case R.id.dailyreport:
                Const.from = "dailyrep";
                Navigation.findNavController(v).navigate(R.id.goto_home_to_dailyreport);
                break;


            case R.id.cl:
                Navigation.findNavController(v).navigate(R.id.goto_home_to_cuslist);
                break;
            case R.id.ado:
                Navigation.findNavController(v).navigate(R.id.goto_home_to_addorder);
                break;
            case R.id.adc:
                Navigation.findNavController(v).navigate(R.id.goto_home_to_addcust);
                break;
        }
    }
}