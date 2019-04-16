package com.techease.speedracerz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.techease.speedracerz.R;
import com.techease.speedracerz.dataModels.signupModels.cities.CitiesDataModel;
import com.techease.speedracerz.dataModels.signupModels.country.CountryDataModel;
import com.techease.speedracerz.interfaces.OnCityItemClicked;

import java.util.List;

public class CityAdapter extends BaseAdapter {
    Context context;
    List<CitiesDataModel> citiesList;
    LayoutInflater inflater;
    OnCityItemClicked onCityItemClicked;
    public CityAdapter(Context context, List<CitiesDataModel> list, OnCityItemClicked onCityItemClicked){
        this.context = context;
        this.citiesList = list;
        this.onCityItemClicked = onCityItemClicked;
        inflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return citiesList.size();
    }

    @Override
    public Object getItem(int position) {
        return citiesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        onCityItemClicked.onCitySelected(citiesList.get(position).getName());
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.custom_country_items, null);
        ImageView icon = convertView.findViewById(R.id.imageView);
        TextView names = convertView.findViewById(R.id.textView);
//        icon.setImageResource(citiesList.get(position).getName());
        names.setText(citiesList.get(position).getName());
        return convertView;
    }
}
