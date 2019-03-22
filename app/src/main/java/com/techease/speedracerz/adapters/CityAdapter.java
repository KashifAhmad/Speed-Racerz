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

import java.util.List;

public class CityAdapter extends BaseAdapter {
    Context context;
    List<CitiesDataModel> citiesList;
    LayoutInflater inflater;
    public CityAdapter(Context context, List<CitiesDataModel> list){
        this.context = context;
        this.citiesList = list;
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
