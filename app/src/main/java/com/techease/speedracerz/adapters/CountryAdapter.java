package com.techease.speedracerz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techease.speedracerz.R;

import com.techease.speedracerz.dataModels.signupModels.country.CountryDataModel;
import com.techease.speedracerz.interfaces.OnCountryItemClicked;

import java.util.List;

public class CountryAdapter extends BaseAdapter {
    Context context;
    List<CountryDataModel> countryList;
    LayoutInflater inflater;
    OnCountryItemClicked onItemCallBack;

    public CountryAdapter(Context context, List<CountryDataModel> list, OnCountryItemClicked onCountryItemClicked) {
        this.context = context;
        this.countryList = list;
        inflater = LayoutInflater.from(context);
        this.onItemCallBack = onCountryItemClicked;

    }



    @Override
    public int getCount() {
        return countryList.size();
    }

    @Override
    public Object getItem(int position) {
//        onItemCallBack.onCountryClick(position);
        return countryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        onItemCallBack.onCountryClick(position);
        onItemCallBack.onCountrySelected(countryList.get(position).getName());
        return countryList.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.custom_country_items, null);
        ImageView icon = convertView.findViewById(R.id.imageView);
        TextView names = convertView.findViewById(R.id.textView);
        LinearLayout linearLayout = convertView.findViewById(R.id.ll_parent);
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemCallBack.onCountryClick(position);
//            }
//        });
//        icon.setImageResource(countryList.get(position).getName());
        names.setText(countryList.get(position).getName());
        return convertView;
    }
}
