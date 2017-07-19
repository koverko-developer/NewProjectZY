package com.example.anika.newprojectzy.valuta;

import com.google.gson.annotations.Expose;

/**
 * Created by AnikA on 26.05.2017.
 */

public class Banks {

    @Expose
    String bankid;
    @Expose
    String filialid;
    @Expose
    String date;
    @Expose
    String bankname;
    @Expose
    String bankaddress;
    @Expose
    String bankphone;
    @Expose
    String filialname;
    @Expose
    String usd_buy;
    @Expose
    String usd_sell;
    @Expose
    String eur_buy;
    @Expose
    String eur_sell;
    @Expose
    String rur_buy;
    @Expose
    String rur_sell;
    @Expose
    String pln_buy;
    @Expose
    String pln_sell;
    @Expose
    String uah_buy;
    @Expose
    String uah_sell;
    @Expose
    String eurusd_buy;
    @Expose
    String eurusd_sell;

    public String getBankid() {
        return bankid;
    }

    public String getFilialid() {
        return filialid;
    }

    public String getDate() {
        return date;
    }

    public String getBankname() {
        return bankname;
    }

    public String getBankaddress() {
        return bankaddress;
    }

    public String getBankphone() {
        return bankphone;
    }

    public String getFilialname() {
        return filialname;
    }

    public String getUsd_buy() {
        return usd_buy;
    }

    public String getUsd_sell() {
        return usd_sell;
    }

    public String getEur_buy() {
        return eur_buy;
    }

    public String getEur_sell() {
        return eur_sell;
    }

    public String getRur_buy() {
        return rur_buy;
    }

    public String getRur_sell() {
        return rur_sell;
    }

    public String getPln_buy() {
        return pln_buy;
    }

    public String getPln_sell() {
        return pln_sell;
    }

    public String getUah_buy() {
        return uah_buy;
    }

    public String getUah_sell() {
        return uah_sell;
    }

    public String getEurusd_buy() {
        return eurusd_buy;
    }

    public String getEurusd_sell() {
        return eurusd_sell;
    }
}