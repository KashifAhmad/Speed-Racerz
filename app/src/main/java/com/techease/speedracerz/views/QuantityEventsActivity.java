package com.techease.speedracerz.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techease.speedracerz.R;
import com.techease.speedracerz.utils.SharedPrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuantityEventsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_continue_payment_events)
    Button btnContinuePaymentEvents;
    @BindView(R.id.iv_race_image)
    ImageView ivRaceImage;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.iv_remove_qty)
    ImageView ivRemoveQuantity;
    @BindView(R.id.iv_add_qty)
    ImageView ivAddQuantity;
    @BindView(R.id.tv_booking_qty)
    TextView tvQuantityCounter;
    @BindView(R.id.tv_changed_price)
    TextView tvChangedPrice;
    @BindView(R.id.et_promo_code)
    EditText etPromoCode;
    @BindView(R.id.btn_apply_code)
    Button btnApplyCode;
    @BindView(R.id.tv_sub_total)
    TextView tvSubtotal;
    @BindView(R.id.tv_discount)
    TextView tvDiscount;
    @BindView(R.id.tv_grand_total)
    TextView tvGrandTotal;
    double quantity = 1, changedAmount, discount;
    private String promoCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity);
        initUI();
    }

    private void initUI() {
        ButterKnife.bind(this);
        btnContinuePaymentEvents.setOnClickListener(this);
        ivAddQuantity.setOnClickListener(this);
        ivRemoveQuantity.setOnClickListener(this);
        changedAmount = Double.parseDouble(SharedPrefUtils.getSharedPref(this).getString("price", ""));
        discount = Double.parseDouble(SharedPrefUtils.getSharedPref(this).getString("discount", ""));

        tvChangedPrice.setText("$ " + changedAmount);
        tvDiscount.setText("$ " + discount);

        String raceImageURL = SharedPrefUtils.getSharedPref(this).getString("imageURL", "");
        Picasso.get().load(raceImageURL).into(ivRaceImage);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add_qty:
                quantity = quantity + 1;

                tvQuantityCounter.setText("" + quantity);

                tvChangedPrice.setText("" +changedAmount*quantity);
                Log.d("zma amount", "change amount: "+changedAmount+" quantity: "+quantity);
                break;
            case R.id.iv_remove_qty:
                if (quantity == 1) {
                    tvQuantityCounter.setText("1");
                } else
                    quantity = quantity - 1;
                tvQuantityCounter.setText("" + quantity);
                tvChangedPrice.setText("" + changedAmount * quantity);
                break;
            case R.id.btn_continue_payment_events:
                SharedPrefUtils.getEditor(this).putString("quantity", String.valueOf(quantity)).commit();
                promoCode = etPromoCode.getText().toString();
                if (!promoCode.isEmpty()) {
                    SharedPrefUtils.getEditor(this).putString("discount", promoCode).commit();
                }
                SharedPrefUtils.getEditor(this).putString("changedPrice", tvChangedPrice.getText().toString()).commit();
                SharedPrefUtils.getEditor(this).putString("totalPrice", tvGrandTotal.getText().toString()).commit();
                String eventType = SharedPrefUtils.getSharedPref(this).getString("event_type", "");
                if (eventType.equals("free")) {
                    startActivity(new Intent(this, ReviewEventsActivity.class));
                } else {
                    startActivity(new Intent(this, CardPaymentActivity.class));
                }
                break;
        }
    }
}
