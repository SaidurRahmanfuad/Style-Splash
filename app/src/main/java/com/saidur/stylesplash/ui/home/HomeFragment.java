package com.saidur.stylesplash.ui.home;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;
import com.saidur.stylesplash.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private TextView ol, ps, to, cl, ado, adc;
    private HomeViewModel homeViewModel;
    private TextInputEditText i;
    private TextView dash_todateId,dash_time;
    String date,time;
LinearLayout tracklin;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View v = inflater.inflate(R.layout.fragment_home, container, false);
         date=new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
         time=new SimpleDateFormat("HH:mm a", Locale.getDefault()).format(new Date());

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        iniTView(v);

        return v;
    }

    private void iniTView(View v) {
        dash_time = v.findViewById(R.id.dash_time);
        dash_time.setText(time);
        dash_todateId = v.findViewById(R.id.dash_todateId);
        dash_todateId.setText(date);
        ol = v.findViewById(R.id.ol);
        ps = v.findViewById(R.id.ps);
        to = v.findViewById(R.id.to);
        tracklin = v.findViewById(R.id.tracklin);
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