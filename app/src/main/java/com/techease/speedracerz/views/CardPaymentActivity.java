package com.techease.speedracerz.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.techease.speedracerz.R;
import com.techease.speedracerz.utils.SharedPrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CardPaymentActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.et_card_number)
    EditText etCardNumber;

    @BindView(R.id.et_card_expiry)
    EditText etCardExpiry;

    @BindView(R.id.et_card_cvv)
    EditText etCardCVV;
    @BindView(R.id.btn_add_card)
    Button btnAddCard;

    private String cardNumber, cardExpiry, cardCVV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);
        initUI();
    }

    private void initUI() {
        ButterKnife.bind(this);
        btnAddCard.setOnClickListener(this);


    }

    private boolean isValid() {
        boolean valid = true;
        cardNumber = etCardNumber.getText().toString();
        cardExpiry = etCardExpiry.getText().toString();
        cardCVV = etCardCVV.getText().toString();
        if (cardNumber.isEmpty() || cardExpiry.isEmpty() || cardCVV.isEmpty()) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Please add card details");
            alert.show();
            valid = false;
        }
        return valid;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_card:
                if (isValid()) {
                    startActivity(new Intent(this, ReviewEventsActivity.class));
                    SharedPrefUtils.getEditor(this).putString("cardNumber", cardNumber).commit();
                    SharedPrefUtils.getEditor(this).putString("cardExpiry", cardExpiry).commit();
                    SharedPrefUtils.getEditor(this).putString("cardCVV", cardCVV).commit();
                }
        }
    }
}
