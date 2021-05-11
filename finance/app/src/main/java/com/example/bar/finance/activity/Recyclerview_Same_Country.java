package com.example.bar.finance.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.bar.finance.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Recyclerview_Same_Country extends RecyclerView.Adapter<Recyclerview_Same_Country.Holder> {

    Context context;
    ArrayList<Recyclerview_items> list;
    String constant_country_name;
    Double info;


    public Recyclerview_Same_Country(Context context , ArrayList<Recyclerview_items> list , String constant_country_name ) {

        this.context = context;
        this.list = list;
        this.constant_country_name = constant_country_name;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.recylerview_country2,viewGroup,false);

        Holder holder = new Holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Recyclerview_items item = list.get(position);
        holder.setData(item);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView constant_country_imageview,different_country_imageview;
        TextView constant_country_text,different_country_text;

        public Holder(View itemView) {
            super(itemView);

            constant_country_imageview = itemView.findViewById(R.id.constant_country_imageview);
            constant_country_text = itemView.findViewById(R.id.constant_country_text);
            different_country_imageview = itemView.findViewById(R.id.different_country_imageview);
            different_country_text = itemView.findViewById(R.id.different_country_text);
        }

        public void setData(Recyclerview_items item) {

            Bitmap bitmap = getImage();
            String total = String.valueOf(item.getCountrymoneyvalue()) +" "+ item.getCountrymoneyname();
            different_country_imageview.setImageResource(item.countryimage);
            different_country_text.setText(total);
            constant_country_imageview.setImageBitmap(bitmap);
            String total2 = "1"+constant_country_name;
            constant_country_text.setText(total2);
            info = item.getCountrymoneyvalue();

        }

        public Bitmap getImage() {

            Bitmap bitmap = null;
            Bitmap resizedbitmap = null;

            try {

                FileInputStream fileInputStream = context.openFileInput("imageview.png");
                bitmap = BitmapFactory.decodeStream(fileInputStream);
            }catch (Exception e) {
                e.printStackTrace();
            }

            return bitmap;
        }
    }

    public Double getInfo(int position) {

        return list.get(position).countrymoneyvalue;
    }

    public String getConstant_country_name(int position) {

        return list.get(position).countryname;
    }

}
