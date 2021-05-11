package com.example.bar.finance.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.bar.finance.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.FileOutputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    boolean InternetConnection = false;
    RecyclerView recyclerView;
    Recyclerview_Country adapter;
    ArrayList<Recyclerview_items> recyclerview_items_list;
    Context context;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        identifiers();
        useRecyclerView();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver,new IntentFilter("takecountryflag"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menu_info) {

            Intent intent = new Intent();
            intent.setClass(context,InfoActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    public void setToolbar() {

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    public void identifiers() {

        setToolbar();

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);

        recyclerView = findViewById(R.id.recyclerview_country);
        recyclerview_items_list = new ArrayList();
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.TRY),R.drawable.turkey,"TRY"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.USD),R.drawable.america,"USD"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.EUR),R.drawable.europeanunion,"EUR"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.EURG),R.drawable.germany,"EUR"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.HRK),R.drawable.croatia,"HRK"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.AED),R.drawable.unitedarabemirates,"AED"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.ARS),R.drawable.argentina,"ARS"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.AUD),R.drawable.australia,"AUD"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.BRL),R.drawable.brazil,"BRL"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.BGN),R.drawable.bulgaria,"BGN"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.CHF),R.drawable.switzerland,"CHF"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.CAD),R.drawable.canada,"CAD"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.CLP),R.drawable.chile,"CLP"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.CNY),R.drawable.china,"CNY"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.COP),R.drawable.colombia,"COP"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.CZK),R.drawable.czechrebuplic,"CZK"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.DKK),R.drawable.denmark,"DKK"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.EGP),R.drawable.egypt,"EGP"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.GBP),R.drawable.england,"GBP"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.FIJI),R.drawable.fiji,"FJD"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.HKD),R.drawable.hongkong,"HKD"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.HUF),R.drawable.hungary,"HUF"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.IDR),R.drawable.indonesia,"IDR"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.ILS),R.drawable.israel,"ILS"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.INR),R.drawable.india,"INR"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.ISK),R.drawable.iceland,"ISK"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.JPY),R.drawable.japan,"JPY"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.KZT),R.drawable.kazakhstan,"KZT"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.MXN),R.drawable.mexico,"MXN"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.MYR),R.drawable.malaysia,"MYR"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.NOK),R.drawable.norway,"NOK"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.NZD),R.drawable.newzealand,"NZD"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.PAB),R.drawable.panama,"PAB"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.PKR),R.drawable.pakistan,"PKR"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.PEN),R.drawable.peru,"PEN"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.PYG),R.drawable.paraguay,"PYG"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.PLN),R.drawable.poland,"PLN"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.PHP),R.drawable.philippines,"PHP"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.RON),R.drawable.romania,"RON"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.RUB),R.drawable.russia,"RUB"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.SAR),R.drawable.saudiarabia,"SAR"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.SEK),R.drawable.sweden,"SEK"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.SGD),R.drawable.singapore,"SGD"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.THB),R.drawable.thailand,"THB"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.TWD),R.drawable.taiwan,"TWD"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.UAH),R.drawable.ukraine,"UAH"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.UYU),R.drawable.uruguay,"UYU"));
        recyclerview_items_list.add(new Recyclerview_items(getResources().getString(R.string.ZAR),R.drawable.southafrica,"ZAR"));


        adapter = new Recyclerview_Country(getApplicationContext(), recyclerview_items_list);
        recyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

    }

    public void useRecyclerView() {


        recyclerView.addOnItemTouchListener(new RecyclerviewİtemClickListener(getApplicationContext(), recyclerView, new RecyclerviewİtemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {

                InternetConnection = checkInternetConnection();

                if (InternetConnection) {

                    ImageView country_imageview = new ImageView(context);
                    country_imageview.setImageResource(recyclerview_items_list.get(position).countryimage);
                    String countrymoneyname = recyclerview_items_list.get(position).countrymoneyname;

                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                        try {

                            FileOutputStream fileOutputStream = openFileOutput("imageview.png", Context.MODE_PRIVATE);

                            Bitmap bitmap = ((BitmapDrawable) country_imageview.getDrawable()).getBitmap();
                            bitmap.setHasAlpha(true);
                            Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
                            bitmap2.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                            fileOutputStream.flush();
                            fileOutputStream.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                    }

                    Intent intent = new Intent(context, CountryValueActivity.class);
                    intent.putExtra("countrymoneyname", countrymoneyname);
                    startActivity(intent);
                }
                else {

                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                    dialog.setTitle("BİLGİLENDİRME");
                    dialog.setMessage("İNTERNET BAĞLANTINIZI KONTROL EDİNİZ");
                    dialog.show();
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }
        ));
    }


    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {

        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                }
                    else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public boolean checkInternetConnection() {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        //NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if(Build.VERSION.SDK_INT  <= Build.VERSION_CODES.LOLLIPOP_MR1) {

                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if(networkInfo != null) {

                    if (networkInfo.isConnected()) {

                        InternetConnection = true;
                    }
                }
                else {

                    InternetConnection = false;
                }
            }

            if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                Network network = connectivityManager.getActiveNetwork();
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);

                if (capabilities != null) {

                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {

                        InternetConnection = true;

                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {

                        InternetConnection = true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {

                        InternetConnection = true;
                    }
                } else {

                    InternetConnection = false;
                }
            }

        return InternetConnection;
    }


    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String countrymoneyname = intent.getStringExtra("takemoneyname");

            ImageView country_imageview = findCountryFlag(countrymoneyname);

            try {
                FileOutputStream fileOutputStream = openFileOutput("imageview.png", Context.MODE_PRIVATE);

                Bitmap bitmap = ((BitmapDrawable) country_imageview.getDrawable()).getBitmap();
                bitmap.setHasAlpha(true);
                Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
                bitmap2.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();

            }catch (Exception e) {
                e.printStackTrace();
            }

            Intent intent2 = new Intent();
            intent2.setClass(context,CountryValueActivity.class);
            intent2.putExtra("countrymoneyname",countrymoneyname);
            startActivity(intent2);

        }
    };

    public ImageView findCountryFlag(String countrymoneyname) {

        ImageView imageview = new ImageView(context);

        for(int i = 0 ; i < recyclerview_items_list.size() ; i++) {

            if(recyclerview_items_list.get(i).getCountrymoneyname().equalsIgnoreCase(countrymoneyname)) {

                imageview.setImageResource(recyclerview_items_list.get(i).countryimage);
            }
        }

        return imageview;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
        recyclerview_items_list.clear();
    }
}
