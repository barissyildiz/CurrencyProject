package com.example.bar.finance.activity;

import com.app.bar.finance.R;

import java.util.ArrayList;
import java.util.List;


public class Recyclerview_items {

    String countryname;
    String countrymoneyname;
    Double countrymoneyvalue;
    int countryimage;
    List<Recyclerview_items> list;

    public Recyclerview_items() {

    }

    public Recyclerview_items(String countryname) {

        this.countryname = countryname;
    }

    public Recyclerview_items(String countryname,int countryimage,String countrymoneyname) {

        this.countryname = countryname;
        this.countryimage = countryimage;
        this.countrymoneyname = countrymoneyname;
    }

    public Recyclerview_items(String countryname,int countryimage,String countrymoneyname,Double countrymoneyvalue) {

        this.countryname = countryname;
        this.countryimage = countryimage;
        this.countrymoneyname = countrymoneyname;
        this.countrymoneyvalue = countrymoneyvalue;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public int getCountryimage() {
        return countryimage;
    }

    public void setCountryimage(int countryimage) {
        this.countryimage = countryimage;
    }

    public String getCountrymoneyname() {
        return countrymoneyname;
    }

    public void setCountrymoneyname(String countrymoneyname) {
        this.countrymoneyname = countrymoneyname;
    }


    public Double getCountrymoneyvalue() {
        return countrymoneyvalue;
    }

    public void setCountrymoneyvalue(Double countrymoneyvalue) {
        this.countrymoneyvalue = countrymoneyvalue;
    }

    public String countrymoneyname() {

        String countrymoneyname = countrymoneyname();

        return countrymoneyname;
    }

    public int countryimageview(int position) {

        int countryimageview = countryimage;

        return countryimageview;
    }

}
