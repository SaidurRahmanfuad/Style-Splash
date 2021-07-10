package com.saidur.stylesplash.ui.order;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedDispatcher;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.saidur.stylesplash.R;
import com.saidur.stylesplash.ui.customer.CustListVM;
import com.saidur.stylesplash.ui.customer.network.CustomerListData;
import com.saidur.stylesplash.ui.order.adapter.ItemsAdapter;
import com.saidur.stylesplash.ui.product.adapter.StockAdapter;
import com.saidur.stylesplash.ui.product.network.StockData;
import com.saidur.stylesplash.utils.Const;
import com.saidur.stylesplash.utils.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class AddOrderFragment extends Fragment implements View.OnClickListener {
    TextView deliveryCrg, productPrice, totalprice, qty;
    Button add_productSave;
    private RadioGroup rg_delivery;
    CardView totalhead;
    String delicrg, insidecrg, outsidecrg;
    String insideClick, outsideClick, doption;
    private RadioButton insideY, outsideN;
    int totalin, totalout, postotalamount, postShip;

    AutoCompleteTextView add_productact_spinner, add_cust_spinner,add_shipping_spinner;
    OrderListVM orderListVM;
    CustListVM custListVM;
    List<StockData> spinnerProductlist;
    String custId, custMobile, totalPrice, shiping_cost, dOption, note, uid,oid;
    String shipname;

    ArrayAdapter<StockData> productsAdapter;
    ArrayAdapter<CustomerListData> custAdapter;
    ArrayAdapter<ShippingModel> shipAdapter;
    String sprice, selectedProductName;
    Dialog popAddQty;
    TextView gp,dp,popupItemName, popupItemPrice, totalsubtotalTv, finaltotalTv, finaldueTv, finalpaidTv, date;
    String selectedProductId, itemSubtotal, itemqty, today,tdayshow;
    TextInputEditText popupItemQty, add_productpayment;
    Button addqtybtn;
    ArrayList<StockData> itemList;
    ItemsAdapter itemsAdapter;
    RecyclerView rv_items;
    int item_totalSubtotalPrice, scrgint, pamountint,selectedpos;


    Session session;
    HashMap<String,String>getUserInfo;


    ArrayList<String> serialList;
    ArrayList<String> pidlist;
    ArrayList<String> sellpricelist;
    ArrayList<String> pqtylist;
    ArrayList<String> totalpricelist;

    ArrayList<ShippingModel> shippings;
//add new customer
    ImageView addnewcustBtn;

    public AddOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_order, container, false);

        Const.gotoinv="AddOrderFragment";
        orderListVM = new ViewModelProvider(this).get(OrderListVM.class);
        session = new Session(getActivity());
        getUserInfo=session.getUserInfo();
        uid=getUserInfo.get(Session.UID);

        custListVM = new ViewModelProvider(this).get(CustListVM.class);
        today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        tdayshow = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        initView(v);
        iniPopup();
        rgactins(v);

        orderListVM.getAllStockObserver().observe(getViewLifecycleOwner(), new Observer<List<StockData>>() {
            @Override
            public void onChanged(List<StockData> stockData) {
                if (stockData != null) {
                    //spinnerProductlist=stockData;
                  //  Log.d("TAG", "onChanged: " + stockData.toString());
                    //  productsAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_product, spinnerProductlist);
                    productsAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_product, stockData);
                    add_productact_spinner.setAdapter(productsAdapter);
                    add_productact_spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            StockData cmm = (StockData) productsAdapter.getItem(position);
                            sprice = cmm.getSprice();


                            selectedpos = position;
                            selectedProductId = cmm.getProduct();
                            selectedProductName = cmm.getProductName();
                            popupItemName.setText(selectedProductName);
                            popupItemPrice.setText(sprice);

                            if(cmm.getTotalPices()==null)
                            {
                                gp.setText("0");
                            }else {
                                gp.setText(cmm.getTotalPices());
                            }
                            if(cmm.getDtquantity()==null)
                            {
                                dp.setText("0");
                            }
                            else {dp.setText(cmm.getDtquantity());}

                            popAddQty.show();

                          /*  Toast.makeText(getActivity(), "selectedProductId : " + selectedProductId, Toast.LENGTH_SHORT).show();
                            Toast.makeText(getActivity(), "sprice : " + sprice, Toast.LENGTH_SHORT).show();*/
                        }
                    });
                }
            }
        });
        orderListVM.AllStockApiCall();
        custListVM.getAllcustObserver().observe(getViewLifecycleOwner(), new Observer<List<CustomerListData>>() {
            @Override
            public void onChanged(List<CustomerListData> customerListData) {
                custAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_product, customerListData);
                add_cust_spinner.setAdapter(custAdapter);
                add_cust_spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        CustomerListData cmm = (CustomerListData) custAdapter.getItem(position);

                        custId = cmm.getCustomerID();
                        custMobile = cmm.getMobile();
/*
                        Toast.makeText(getActivity(), "cust Id  : " + custId, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), "custMobile  : " + custMobile, Toast.LENGTH_SHORT).show();*/
                    }
                });
            }
        });
        custListVM.AllCustApiCall();
        orderListVM.adorderOidrObserver().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!s.isEmpty())
                {
                    Const.gotoinv="AddOrderFragment";
                    oid=s;
                    sendToInvoice(oid);
/*
                    Toast.makeText(getActivity(), "tt"+oid, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "tts"+s, Toast.LENGTH_SHORT).show();*/
                }

            }
        });
        shipping_data();
        return v;
    }

    private void shipping_data() {
        shippings=new ArrayList<>();
        shippings.add(new ShippingModel("0","Redx"));
        shippings.add(new ShippingModel("1","Patho"));
        shippings.add(new ShippingModel("2","Sundorban"));
        shippings.add(new ShippingModel("3","SA Poribahon"));
        shippings.add(new ShippingModel("4","Showroon"));


        shipAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_product, shippings);
        add_shipping_spinner.setAdapter(shipAdapter);
        add_shipping_spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ShippingModel ad = (ShippingModel) shipAdapter.getItem(position);
                shipname = ad.getName();
            }
        });
    }

    private void sendToInvoice(String oid) {
        pidlist.clear();
        pqtylist.clear();
        sellpricelist.clear();
        totalpricelist.clear();
        add_productpayment.setText("");
          if(oid!=null)
                {
                    Intent goinv=new Intent(getActivity(),InvoiceActivity.class);
                    goinv.putExtra("ioid",oid);
                    startActivity(goinv);
                }
                else {
                    Toast.makeText(getActivity(), "oid not found", Toast.LENGTH_SHORT).show();
                }
    }

    private void iniPopup() {
        popAddQty = new Dialog(getContext());
        popAddQty.setContentView(R.layout.popup_qty);
        //popAddQty.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popAddQty.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popAddQty.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        popAddQty.getWindow().getAttributes().gravity = Gravity.CENTER;

        // ini popup widgets

        popupItemName = popAddQty.findViewById(R.id.pName);
        popupItemQty = popAddQty.findViewById(R.id.inputQty);

        popupItemPrice = popAddQty.findViewById(R.id.price);
        gp = popAddQty.findViewById(R.id.gp);
        dp = popAddQty.findViewById(R.id.dp);

        addqtybtn = popAddQty.findViewById(R.id.addqtybtn);
        itemList = new ArrayList<>();
        addqtybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!popupItemQty.getText().toString().isEmpty()) {
                    itemqty = popupItemQty.getText().toString();
                    int qty, ssprice, isubtotal;
                    qty = Integer.valueOf(itemqty);
                    ssprice = Integer.valueOf(sprice);
                    isubtotal = ssprice * qty;
                    itemSubtotal = String.valueOf(isubtotal);

                    String dunitprice = String.valueOf(sprice);
                    // Integer ditemSubtotal=Integer.parseInt(itemSubtotal);

                    StockData isitm = new StockData(selectedProductId, selectedProductName, sprice, itemqty, itemSubtotal);
                    itemList.add(isitm);
                    //itemList.add(new Invoice_Single_item(selectedProductName, selectedProductPrice, fsubtotal, itemqty));
                  /*  Toast.makeText(getActivity(), "product id : " + selectedProductId, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "sprice  : " + sprice, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "itemqty  : " + itemqty, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "itemSubtotal  : " + itemSubtotal, Toast.LENGTH_SHORT).show();*/
                    itemsAdapter = new ItemsAdapter(getActivity(), itemList);
                    rv_items.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_items.setHasFixedSize(true);
                    rv_items.setAdapter(itemsAdapter);
                    itemsAdapter.notifyDataSetChanged();


                    int rv_item_totalPrice = 0;
                    for (int i = 0; i < itemList.size(); i++) {
                        rv_item_totalPrice += Integer.valueOf(itemList.get(i).getSubtotal());
                        item_totalSubtotalPrice = rv_item_totalPrice;
                    }
                    totalsubtotalTv.setText(String.valueOf(item_totalSubtotalPrice));
                    popupItemQty.setText("");

                    serialList.add(String.valueOf(selectedpos));
                    pidlist.add(selectedProductId);
                    pqtylist.add(itemqty);
                    sellpricelist.add(sprice);
                    totalpricelist.add(itemSubtotal);

                    popAddQty.dismiss();


                } else {
                    popupItemQty.setError("Enter Quantity");
                    addqtybtn.setVisibility(View.VISIBLE);

                }
            }
        });

    }

    private void initView(View v) {
        serialList = new ArrayList<>();
        pidlist = new ArrayList<>();
        pqtylist = new ArrayList<>();
        sellpricelist = new ArrayList<>();
        totalpricelist = new ArrayList<>();

        date = v.findViewById(R.id.date);
        date.setText(tdayshow);
        add_productpayment = v.findViewById(R.id.add_productpayment);
        totalsubtotalTv = v.findViewById(R.id.totalsubtotal);
        rv_items = v.findViewById(R.id.rv_items);
        add_productact_spinner = v.findViewById(R.id.add_productact_spinner);
        add_shipping_spinner = v.findViewById(R.id.add_shipping_spinner);

        add_cust_spinner = v.findViewById(R.id.add_cust_spinner);
        rg_delivery = v.findViewById(R.id.rg_delivery);
        totalhead = v.findViewById(R.id.totalhead);

        deliveryCrg = v.findViewById(R.id.deliveryCrg);
        productPrice = v.findViewById(R.id.productPrice);
        totalprice = v.findViewById(R.id.totalprice);
        add_productSave = v.findViewById(R.id.add_productSave);
        add_productSave.setOnClickListener(this);
        addnewcustBtn = v.findViewById(R.id.addnewcustBtn);
        addnewcustBtn.setOnClickListener(this);

    }

    private void rgactins(View v) {

        rg_delivery.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                insideY = v.findViewById(checkedId);
                insideClick = insideY.getText().toString();
                if (insideClick.equals("Inside Dhaka")) {
                    totalhead.setVisibility(View.VISIBLE);
                    deliveryCrg.setText("60");
                    totalin = item_totalSubtotalPrice + 60;
                    productPrice.setText(String.valueOf(item_totalSubtotalPrice));
                    totalprice.setText(String.valueOf(totalin));

                    postotalamount = totalin;
                    postShip = 60;
                    doption = "Inside Dhaka";
                }
                if (insideClick.equals("Outside Dhaka")) {
                    totalhead.setVisibility(View.VISIBLE);
                    deliveryCrg.setText("120");
                    totalout = item_totalSubtotalPrice + 120;
                    productPrice.setText(String.valueOf(item_totalSubtotalPrice));
                    totalprice.setText(String.valueOf(totalout));
                    postotalamount = totalout;
                    postShip = 120;
                    doption = "Outside Dhaka";
                }

              /*  else {
                    totalhead.setVisibility(View.GONE);
                }*/
                // Toast.makeText(getActivity(), "othertrt " + othertrt, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_productSave:
                HashMap<String, String> map = new HashMap<>();
                map.put("date", today);
                map.put("customerID", custId);
                map.put("totalAmount", String.valueOf(postotalamount));
                map.put("shiping_cost", String.valueOf(postShip));
                map.put("paidAmount", add_productpayment.getText().toString());
                map.put("dOption", doption);
                map.put("shmethod", shipname);
                map.put("note", "default");
                map.put("uid", uid);


                Log.d("agfh", "today: " + today);
                Log.d("agfh", "custId: " + custId);
                Log.d("agfh", "postotalamount: " + postotalamount);
                Log.d("agfh", "postShip: " + postShip);
                Log.d("agfh", "doption: " + doption);
                Log.d("agfh", "uid: " + uid);

                Log.d("agfh", "pidlist: " + pidlist);
                Log.d("agfh", "sellpricelist: " + sellpricelist);
                Log.d("agfh", "pqtylist: " + pqtylist);
                Log.d("agfh", "totalpricelist: " + totalpricelist);

                orderListVM.SaveOrderApiCall(map, pidlist, sellpricelist, pqtylist, totalpricelist);

                //Toast.makeText(getActivity(), "add order oid"+oid, Toast.LENGTH_SHORT).show();


                // Navigation.findNavController(v).navigate(R.id.goto_addOrder_to_invoice);
                break;

            case R.id.addnewcustBtn:
                Navigation.findNavController(v).navigate(R.id.goto_addOrder_to_addcust);
             //   moveToActivity(getActivity(), NewProductActivity.class);
                break;
        }

    }

   /* public void moveToActivity(FragmentActivity fragmentActivity, Class<? extends Activity> activity) {
        startActivity(new Intent(getActivity(), activity));
        getActivity().overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }*/
}