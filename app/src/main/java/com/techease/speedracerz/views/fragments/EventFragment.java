package com.techease.speedracerz.views.fragments;


import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.techease.speedracerz.R;
import com.techease.speedracerz.adapters.EventAdapter;
import com.techease.speedracerz.dataModels.eventsDataModels.EventDataModel;
import com.techease.speedracerz.dataModels.eventsDataModels.EventResponseModel;
import com.techease.speedracerz.networking.BaseNetworking;
import com.techease.speedracerz.utils.AlertUtils;
import com.techease.speedracerz.utils.SharedPrefUtils;
import com.techease.speedracerz.views.EventActivity;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

    AlertDialog alertDialog;
    @BindView(R.id.rv_event)
    RecyclerView rvEvents;
    View view;

    ArrayList<EventDataModel> eventDataModelArrayList;
    EventAdapter eventAdapter;
    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_event, container, false);
        initUI();
        return view;
    }
    private void initUI() {
        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvEvents.setLayoutManager(linearLayoutManager);
        eventDataModelArrayList = new ArrayList<>();
        alertDialog = AlertUtils.createProgressDialog(getActivity());
        alertDialog.show();
        eventAdapter = new EventAdapter(getActivity(), eventDataModelArrayList);
        rvEvents.setAdapter(eventAdapter);
        apiCallEvents();
    }
    private void apiCallEvents() {
        Call<EventResponseModel> userLogin = BaseNetworking.apiServices(SharedPrefUtils.getApiToken(getActivity())).events();
        userLogin.enqueue(new Callback<EventResponseModel>() {
            @Override
            public void onResponse(Call<EventResponseModel> call, Response<EventResponseModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else if (response.body().getStatus()) {
                    eventDataModelArrayList.addAll(response.body().getData());
                    eventAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<EventResponseModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
