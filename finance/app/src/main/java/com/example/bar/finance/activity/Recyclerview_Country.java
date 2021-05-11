package com.example.bar.finance.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.bar.finance.R;

import java.util.List;

public class Recyclerview_Country extends RecyclerView.Adapter<Recyclerview_Country.Holder> {

    Context context;
    List<Recyclerview_items> countrydatalist;


    public Recyclerview_Country(Context context, List<Recyclerview_items> countrydata) {

        this.context = context;
        this.countrydatalist = countrydata;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int viewtype) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.recyclerview_country,viewGroup,false);

        Holder holder = new Holder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {

        Recyclerview_items datacountry = countrydatalist.get(position);
        viewHolder.setdata(datacountry);
    }

    @Override
    public int getItemCount() {

        return countrydatalist.size();

    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView countryname;
        ImageView countryimage;

        public Holder(View itemView) {
            super(itemView);

            countryname = itemView.findViewById(R.id.country_name);
            countryimage = itemView.findViewById(R.id.country_image);
        }

        public void setdata(Recyclerview_items countrydata) {

            this.countryname.setText(countrydata.getCountryname());
            this.countryimage.setImageResource(countrydata.getCountryimage());
        }
    }

}
