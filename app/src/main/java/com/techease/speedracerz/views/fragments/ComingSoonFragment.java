package com.techease.speedracerz.views.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techease.speedracerz.R;
import com.techease.speedracerz.views.MyTicketsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComingSoonFragment extends Fragment {


    View view;
    public ComingSoonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_coming_soon, container, false);
        startActivity(new Intent(getActivity(), MyTicketsActivity.class));
        return view;
    }

}
