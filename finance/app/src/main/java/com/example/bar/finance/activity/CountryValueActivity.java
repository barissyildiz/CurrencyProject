package com.example.bar.finance.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.bar.finance.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONObject;

import java.util.ArrayList;

public class CountryValueActivity extends AppCompatActivity {

    Context context;
    TextInputLayout textInputLayout;
    RecyclerView recyclerView;
    Recyclerview_items recyclerview_items;
    Intent intent;
    ArrayList<Recyclerview_items> recyclerview_items_list;
    Recyclerview_Same_Country  recyclerviewSameCountry;
    int position1;
    String countryname;
    String countryname2;
    TextInputEditText textInputEditText;
    TextView resulttext;
    CharSequence charSequence;
    int countglobal;
    String stringintent;
    String temp = null;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    String result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countryvalue);
        context = this;
        recyclerview_items_list = new ArrayList<>();

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4977622113046142/9831074949");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener(){

            @Override
            public void onAdLoaded() {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

                super.onAdLoaded();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        identifiers();
        intent = getIntent();
        String constant_country_name = intent.getStringExtra("countrymoneyname");
        //Recyclerview_items recyclerview_items1 = new Recyclerview_items("TURKEY", R.drawable.turkey,"TRY",50.1);
        //Toast.makeText(context,String.valueOf(recyclerview_items1.getAllData().get(0).getCountrymoneyvalue()),Toast.LENGTH_LONG).show();
        //CALL THE SERVİCE FOR NETWORK JOBS
        networkjob(constant_country_name);

    }

    public void setToolBar() {

        Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home) {

            Intent intent = new Intent();
            intent.setClass(context,MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void identifiers() {

        recyclerView = findViewById(R.id.recyclerview_country2);
        recyclerView.setHasFixedSize(true);
        recyclerview_items = new Recyclerview_items();
        setToolBar();

        recyclerView.addOnItemTouchListener(new RecyclerviewİtemClickListener(context, recyclerView, new RecyclerviewİtemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(final View view, final int position) {

                position1 = position;
                countryname2 = recyclerview_items_list.get(position).getCountrymoneyname();
                //USER ENTER THE MONEY VALUE CALCULATİNG

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View view1 = layoutInflater.inflate(R.layout.alertdialog_calculate, null, false);
                builder.setView(view1);
                builder.setPositiveButton(getResources().getString(R.string.alert_dialog_return_main_page), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent();
                        intent.setClass(context,MainActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNeutralButton(getResources().getString(R.string.alert_dialog_return_last_page), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        builder.create().cancel();
                        //Intent intent = new Intent();
                        //intent.setClass(context,CountryValueActivity.class);
                        //intent.putExtra("countrymoneyname",countryname);
                        //startActivity(intent);
                    }
                });
                builder.create().show();

                SharedPreferences sharedPreferences = context.getSharedPreferences("access", Context.MODE_PRIVATE);
                countryname = sharedPreferences.getString("constant_country_name", "A");

                textInputEditText = view1.findViewById(R.id.alert_dialog_money);
                resulttext = view1.findViewById(R.id.alert_dialog_result_value);
                textInputLayout = view1.findViewById(R.id.alert_dialog_text_input_layout);
                //android.support.design.widget.TextInputLayout parent = (TextInputLayout) textInputEditText.getParent();
                textInputLayout.setHelperText(getResources().getString(R.string.alert_dialog_enter)+" "+countryname+ " " +getResources().getString(R.string.alert_dialog_value));
                final Double money = recyclerviewSameCountry.getInfo(position);


                textInputEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        if (temp != null) {
                            textInputEditText.setText(temp);
                        }
                    }

                    @SuppressLint("StaticFieldLeak")
                    @Override
                    public void onTextChanged(CharSequence s, int start, final int before, int count) {

                        countglobal = count;
                        charSequence = s;

                        new AsyncTask<Void, Void, String>() {
                            @Override
                            protected String doInBackground(Void... voids) {


                                if (countglobal == 0) {

                                    if(charSequence.toString().equalsIgnoreCase("")) {

                                        charSequence = "1";
                                    }
                                }

                                    Double value = Double.parseDouble(charSequence.toString());
                                    Double value2 = value * money;
                                    //DecimalFormat f = new DecimalFormat("0.00");
                                    //value = Double.parseDouble(f.format(value));
                                    int r = (int) Math.round(value2 * 1000);
                                    value2 = r / 1000.0;
                                    String result = String.valueOf(value2);

                                    return result;
                            }

                            @Override
                            protected void onPostExecute(String aVoid) {
                                super.onPostExecute(aVoid);
                                //String result = aVoid + countryname;
                                String result = aVoid + " "+ countryname2;
                                //textInputEditText.setText(charSequence.toString() + countryname);
                                resulttext.setText(result);
                            }
                        }.execute();
                    }
                    @Override
                    public void afterTextChanged(Editable s) {


                    }
                });

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    public void networkjob(final String countrymoneynameforurl) {

        String url = "https://api.exchangerate-api.com/v4/latest/" + countrymoneynameforurl;

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject infomoneyvalues = jsonObject.getJSONObject("rates");
                    //We don't need to compare same country money value
                    final String notneccessaryvalue = jsonObject.getString("base");


                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.TRY), R.drawable.turkey, "TRY", infomoneyvalues.getDouble("TRY")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.USD), R.drawable.america, "USD", infomoneyvalues.getDouble("USD")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.EUR),R.drawable.europeanunion,"EUR",infomoneyvalues.getDouble("EUR")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.EURG), R.drawable.germany, "EUR", infomoneyvalues.getDouble("EUR")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.HRK), R.drawable.croatia, "HRK", infomoneyvalues.getDouble("HRK")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.AED), R.drawable.unitedarabemirates, "AED", infomoneyvalues.getDouble("AED")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.ARS), R.drawable.argentina, "ARS", infomoneyvalues.getDouble("ARS")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.AUD), R.drawable.australia, "AUD", infomoneyvalues.getDouble("AUD")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.BRL), R.drawable.brazil, "BRL", infomoneyvalues.getDouble("BRL")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.BGN), R.drawable.bulgaria, "BGN", infomoneyvalues.getDouble("BGN")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.CHF), R.drawable.switzerland, "CHF", infomoneyvalues.getDouble("CHF")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.CAD), R.drawable.canada, "CAD", infomoneyvalues.getDouble("CAD")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.CLP), R.drawable.chile, "CLP", infomoneyvalues.getDouble("CLP")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.CNY), R.drawable.china, "CNY", infomoneyvalues.getDouble("CNY")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.COP), R.drawable.colombia, "COP", infomoneyvalues.getDouble("COP")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.CZK), R.drawable.czechrebuplic, "CZK", infomoneyvalues.getDouble("CZK")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.DKK), R.drawable.denmark, "DKK", infomoneyvalues.getDouble("DKK")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.EGP), R.drawable.egypt, "EGP", infomoneyvalues.getDouble("EGP")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.GBP), R.drawable.england, "GBP", infomoneyvalues.getDouble("GBP")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.FIJI), R.drawable.fiji, "FJD", infomoneyvalues.getDouble("FJD")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.HKD), R.drawable.hongkong, "HKD", infomoneyvalues.getDouble("HKD")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.HUF), R.drawable.hungary, "HUF", infomoneyvalues.getDouble("HUF")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.IDR), R.drawable.indonesia, "IDR", infomoneyvalues.getDouble("IDR")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.ILS), R.drawable.israel, "ILS", infomoneyvalues.getDouble("ILS")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.INR), R.drawable.india, "INR", infomoneyvalues.getDouble("INR")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.ISK), R.drawable.iceland, "ISK", infomoneyvalues.getDouble("ISK")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.JPY), R.drawable.japan, "JPY", infomoneyvalues.getDouble("JPY")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.KZT), R.drawable.kazakhstan, "KZT", infomoneyvalues.getDouble("KZT")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.MXN), R.drawable.mexico, "MXN", infomoneyvalues.getDouble("MXN")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.MYR), R.drawable.malaysia, "MYR", infomoneyvalues.getDouble("MYR")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.NOK), R.drawable.norway, "NOK", infomoneyvalues.getDouble("NOK")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.NZD), R.drawable.newzealand, "NZD", infomoneyvalues.getDouble("NZD")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.PAB), R.drawable.panama, "PAB", infomoneyvalues.getDouble("PAB")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.PKR), R.drawable.pakistan, "PKR", infomoneyvalues.getDouble("PKR")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.PEN), R.drawable.peru, "PEN", infomoneyvalues.getDouble("PEN")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.PYG), R.drawable.paraguay, "PYG", infomoneyvalues.getDouble("PYG")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.PLN), R.drawable.poland, "PLN", infomoneyvalues.getDouble("PLN")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.PHP), R.drawable.philippines, "PHP", infomoneyvalues.getDouble("PHP")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.RON), R.drawable.romania, "RON", infomoneyvalues.getDouble("RON")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.RUB), R.drawable.russia, "RUB", infomoneyvalues.getDouble("RUB")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.SAR), R.drawable.saudiarabia, "SAR", infomoneyvalues.getDouble("SAR")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.SEK), R.drawable.sweden, "SEK", infomoneyvalues.getDouble("SEK")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.SGD), R.drawable.singapore, "SGD", infomoneyvalues.getDouble("SGD")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.THB), R.drawable.thailand, "THB", infomoneyvalues.getDouble("THB")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.TWD), R.drawable.taiwan, "TWD", infomoneyvalues.getDouble("TWD")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.UAH), R.drawable.ukraine, "UAH", infomoneyvalues.getDouble("UAH")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.UYU), R.drawable.uruguay, "UYU", infomoneyvalues.getDouble("UYU")));
                            recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.ZAR), R.drawable.southafrica, "ZAR", infomoneyvalues.getDouble("ZAR")));

                            stringintent = intent.getStringExtra("countrymoneyname");

                            new AsyncTask<Void,Void,String>() {

                                @Override
                                protected String doInBackground(Void... voids) {

                                    for (int i = 0; i < recyclerview_items_list.size(); i++) {

                                        if (recyclerview_items_list.get(i).countrymoneyname.equalsIgnoreCase(countrymoneynameforurl)) {

                                            recyclerview_items_list.remove(i);

                                            //SAVE THE COUNTRY MONEY NAME
                                            SharedPreferences sharedPreferences = context.getSharedPreferences("access", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("constant_country_name", notneccessaryvalue);
                                            editor.commit();

                                        }
                                    }

                                    recyclerviewSameCountry = new Recyclerview_Same_Country(context, recyclerview_items_list, stringintent);

                                    return "11";

                                }


                                @Override
                                protected void onPostExecute(String s) {
                                    super.onPostExecute(s);
                                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                    recyclerView.setLayoutManager(linearLayoutManager);
                                    recyclerView.setAdapter(recyclerviewSameCountry);
                                }
                            }.execute();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
        //MySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }
}
