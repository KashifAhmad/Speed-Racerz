package com.techease.speedracerz.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.techease.speedracerz.R;
import com.techease.speedracerz.dataModels.GenericResponseModel;
import com.techease.speedracerz.networking.BaseNetworking;
import com.techease.speedracerz.utils.Connectivity;
import com.techease.speedracerz.utils.SharedPrefUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewEventsActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.btn_buy_now)
    Button btnBuyNow;

    @BindView(R.id.tv_booking_qty)
    TextView tvBookingQuantity;
    @BindView(R.id.tv_booking_amount)
    TextView tvBookingAmount;
    @BindView(R.id.tv_email)
    TextView tvEmail;

    @BindView(R.id.tv_edit_email)
    TextView tvEditEmail;

    @BindView(R.id.tv_booked_by)
    TextView tvBookedBy;

    @BindView(R.id.tv_edit_booked_by)
    TextView tvEditBookedBy;

    @BindView(R.id.tv_address)
    TextView tvAddress;

    @BindView(R.id.tv_card_expiry)
    TextView tvCardExpiry;

    @BindView(R.id.tv_edit_card)
    TextView tvEditCard;

    @BindView(R.id.tv_sub_total)
    TextView tvSubtotal;

    @BindView(R.id.tv_tax)
    TextView tvTax;
    @BindView(R.id.tv_discount)
    TextView tvDiscount;
    @BindView(R.id.tv_grand_total)
    TextView tvGrandTotal;
    int eventID;
    private String name, email, phone, address, cardNo, expiry, cvv, quantity, price, discount, tax, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        initUI();
    }

    private void initUI() {
        ButterKnife.bind(this);
        initData();
        btnBuyNow.setOnClickListener(this);

    }

    private void initData() {
        eventID = SharedPrefUtils.getSharedPref(this).getInt("eventID", 0);
        quantity = SharedPrefUtils.getSharedPref(this).getString("quantity", "");
        price = SharedPrefUtils.getSharedPref(this).getString("changedPrice", "");
        email = SharedPrefUtils.getSharedPref(this).getString("email", "");
        address = SharedPrefUtils.getSharedPref(this).getString("address", "");
        name = SharedPrefUtils.getSharedPref(this).getString("bookedBy", "");
        expiry = SharedPrefUtils.getSharedPref(this).getString("cardExpiry", "");
        total = SharedPrefUtils.getSharedPref(this).getString("totalPrice", "");
        discount = SharedPrefUtils.getSharedPref(this).getString("discount", "");
        phone = SharedPrefUtils.getSharedPref(this).getString("bookedPhoneNumber", "");
        tax = "1234";
        cardNo = SharedPrefUtils.getSharedPref(this).getString("cardNumber", "");
        cvv = SharedPrefUtils.getSharedPref(this).getString("cvv", "");
        tvBookingQuantity.setText(quantity);
        tvBookingAmount.setText(price);
        tvEmail.setText(email);
        tvAddress.setText(address);
        tvBookedBy.setText(name);
        tvCardExpiry.setText(expiry);
        tvSubtotal.setText(price);
//        tvTax.setText(SharedPrefUtils.getSharedPref(this).getString("changedPrice", ""));
        tvGrandTotal.setText(total);
        tvDiscount.setText(discount);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_buy_now:
                if (Connectivity.isConnected(this)) {
                    bookTicket();
                }
        }

    }

    private void bookTicket() {
        Call<GenericResponseModel> call = BaseNetworking.apiServices(SharedPrefUtils.getApiToken(this))
                .bookTicket(eventID, name, email, phone, address, "4758411877817150", "05/2019", "1232342342", quantity);
        call.enqueue(new Callback<GenericResponseModel>() {
            @Override
            public void onResponse(Call<GenericResponseModel> call, Response<GenericResponseModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ReviewEventsActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ReviewEventsActivity.this, MyTicketsActivity.class));
                }else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String error = jObjError.getString("message");
                        Toast.makeText(ReviewEventsActivity.this, error, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GenericResponseModel> call, Throwable t) {

            }
        });
    }
}
