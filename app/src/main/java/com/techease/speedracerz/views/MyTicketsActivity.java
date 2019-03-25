package com.techease.speedracerz.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.techease.speedracerz.R;
import com.techease.speedracerz.adapters.MyTicketsAdapter;
import com.techease.speedracerz.dataModels.myTicketsModel.MyTicketsData;
import com.techease.speedracerz.dataModels.myTicketsModel.MyTicketsResponseModel;
import com.techease.speedracerz.networking.BaseNetworking;
import com.techease.speedracerz.utils.SharedPrefUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyTicketsActivity extends AppCompatActivity {

    @BindView(R.id.rv_my_tickets)
    RecyclerView rvMyTickets;
    List<MyTicketsData> myTicketsDataList;
    MyTicketsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets);
        initUI();
    }

    private void initUI() {
        ButterKnife.bind(this);
        myTicketsDataList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MyTicketsActivity.this);
        rvMyTickets.setLayoutManager(layoutManager);
        adapter = new MyTicketsAdapter(this, myTicketsDataList);
        rvMyTickets.setAdapter(adapter);
        getMyTickets();
    }


    private void getMyTickets() {
        Call<MyTicketsResponseModel> call = BaseNetworking.apiServices(SharedPrefUtils.getApiToken(this))
                .myTickets();
        call.enqueue(new Callback<MyTicketsResponseModel>() {
            @Override
            public void onResponse(Call<MyTicketsResponseModel> call, Response<MyTicketsResponseModel> response) {
                if (response.isSuccessful()) {
                    myTicketsDataList.addAll(response.body().getData());
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(MyTicketsActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MyTicketsResponseModel> call, Throwable t) {
                Toast.makeText(MyTicketsActivity.this,  t.getCause().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
