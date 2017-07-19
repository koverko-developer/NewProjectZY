package com.example.anika.newprojectzy.valuta;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.anika.newprojectzy.R;

import java.util.ArrayList;

/**
 * Created by AnikA on 26.05.2017.
 */

public class BanksListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Banks> products;
    LayoutInflater lInflater;
    float minUsd, maxUsd, minEur, maxEur, minRub, maxRub ;

    public BanksListAdapter(Activity context, ArrayList<Banks> products, float minUsd, float maxUsd,
                            float minEur, float maxEur, float minRub, float maxRub){
        this.context = context;
        this.products = products;
        this.minUsd = minUsd;
        this.maxUsd = maxUsd;
        this.minEur = minEur;
        this.maxEur = maxEur;
        this.minRub = minRub;
        this.maxRub = maxRub;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Banks getItem(int position) {
        return products.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    class Card{
        TextView tvName;
        TextView tvPhone;
        TextView tvUSDB;
        TextView tvUSDS;
        TextView tvEUB;
        TextView tvEUS;
        TextView tvRRB;
        TextView tvRRS;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = lInflater.inflate(R.layout.item_car_course, null, true);
        Card card = new Card();

        Banks bank = products.get(position);




        card.tvName = (TextView) v.findViewById(R.id.textViewBankName);
        card.tvPhone = (TextView) v.findViewById(R.id.textViewPhoneBank);
        card.tvUSDB = (TextView) v.findViewById(R.id.textViewBankUSDBuy);
        card.tvUSDS = (TextView) v.findViewById(R.id.textViewBankUSDSale);
        card.tvEUB = (TextView) v.findViewById(R.id.textViewBankEUBuy);
        card.tvEUS = (TextView) v.findViewById(R.id.textViewBankEUSale);
        card.tvRRB = (TextView) v.findViewById(R.id.textViewBankRRBuy);
        card.tvRRS = (TextView) v.findViewById(R.id.textViewBankRRSale);




        try {
            card.tvName.setText(bank.getBankname());
            String[] phones = bank.getBankphone().split(",");
            card.tvPhone.setText(phones[0]);
            card.tvUSDB.setText(bank.getUsd_buy());
            card.tvUSDS.setText(bank.getUsd_sell());
            card.tvEUB.setText(bank.getEur_buy());
            card.tvEUS.setText(bank.getEur_sell());
            card.tvRRB.setText(bank.getRur_buy());
            card.tvRRS.setText(bank.getRur_sell());

            if(Float.parseFloat(bank.getUsd_buy()) == minUsd) card.tvUSDB.setTextColor(Color.parseColor("#F7FE2E"));
            if(Float.parseFloat(bank.getUsd_sell()) == maxUsd) card.tvUSDS.setTextColor(Color.parseColor("#80FF00"));

            if(Float.parseFloat(bank.getEur_buy()) == minEur) card.tvEUB.setTextColor(Color.parseColor("#F7FE2E"));
            if(Float.parseFloat(bank.getEur_sell()) == maxEur) card.tvEUS.setTextColor(Color.parseColor("#80FF00"));

            if(Float.parseFloat(bank.getRur_buy()) == minRub) card.tvRRB.setTextColor(Color.parseColor("#F7FE2E"));
            if(Float.parseFloat(bank.getRur_sell()) == maxRub) card.tvRRS.setTextColor(Color.parseColor("#80FF00"));

            String s = "";

        }catch (Exception e){
            e.printStackTrace();
        }


        return v;
    }



}