package com.techease.speedracerz.views.resetpasswordscreens;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techease.speed.R;
import com.techease.speedracerz.dataModels.changePasswordModels.ResetPasswordResponeModel;
import com.techease.speedracerz.networking.APIClient;
import com.techease.speedracerz.networking.APIServices;
import com.techease.speedracerz.utils.AlertUtils;
import com.techease.speedracerz.utils.GeneralUtils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends AppCompatActivity {
    AlertDialog alertDialog;
    @BindView(R.id.et_forgot_email)
    EditText etEmail;
    @BindView(R.id.btn_reset_password)
    Button btnResetPassword;

    private boolean valid = false;
    private String strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initUI();
    }

    private void initUI() {
        ButterKnife.bind(this);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    alertDialog = AlertUtils.createProgressDialog(ResetPasswordActivity.this);
                    alertDialog.show();
                    apiCallForgotPassword();
                }
            }
        });
    }

    private void apiCallForgotPassword() {
        APIServices services = APIClient.getClient().create(APIServices.class);
        Call<ResetPasswordResponeModel> userLogin = services.resetPassword(strEmail);
        userLogin.enqueue(new Callback<ResetPasswordResponeModel>() {
            @Override
            public void onResponse(Call<ResetPasswordResponeModel> call, Response<ResetPasswordResponeModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(ResetPasswordActivity.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(ResetPasswordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response.body().getStatus()) {
                    Toast.makeText(ResetPasswordActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ResetPasswordActivity.this, VerifyPasswordCodeActivity.class));

                }
            }

            @Override
            public void onFailure(Call<ResetPasswordResponeModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(ResetPasswordActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean validate() {
        valid = true;

        strEmail = etEmail.getText().toString();

        GeneralUtils.putStringValueInEditor(ResetPasswordActivity.this,"forgot_email",strEmail);

        if (strEmail.isEmpty()) {
            etEmail.setError("enter a valid email address");
            valid = false;
        } else {
            etEmail.setError(null);
        }
        return valid;
    }
}
