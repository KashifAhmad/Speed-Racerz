package com.techease.speedracerz.views.resetpasswordscreens;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.techease.speedracerz.R;
import com.techease.speedracerz.dataModels.changePasswordModels.ChangePasswordModel;
import com.techease.speedracerz.dataModels.changePasswordModels.verifycodemodel.VerifyCodeResponseModel;
import com.techease.speedracerz.networking.APIClient;
import com.techease.speedracerz.networking.APIServices;
import com.techease.speedracerz.utils.AlertUtils;
import com.techease.speedracerz.utils.GeneralUtils;
import com.techease.speedracerz.views.LoginActivity;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyPasswordCodeActivity extends AppCompatActivity {
    AlertDialog alertDialog;

    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    @BindView(R.id.btn_verify_code)
    Button btnVerifyCode;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_conifrm_new_password)
    EditText etConfirmNewPassword;
    @BindView(R.id.btn_new_password)
    Button btnSetNewPassword;
    @BindView(R.id.code_layout)
    RelativeLayout codeLayout;
    @BindView(R.id.new_password_layout)
    RelativeLayout newPassLayout;

    private String strCode, strNewPassword, strConfirmNewPassword;
    private boolean valid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_password_code);

        initUI();
    }

    private void initUI() {
        ButterKnife.bind(this);

        btnVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    alertDialog = AlertUtils.createProgressDialog(VerifyPasswordCodeActivity.this);
                    alertDialog.show();
                    apiCallForgotPassword();
                }
            }
        });

        btnSetNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    alertDialog = AlertUtils.createProgressDialog(VerifyPasswordCodeActivity.this);
                    alertDialog.show();
                    apiCallChangePassword();
                }
            }
        });
    }

    private void apiCallForgotPassword() {
        APIServices services = APIClient.getClient().create(APIServices.class);
        Call<VerifyCodeResponseModel> userLogin = services.verifyPasswordCode(strCode, GeneralUtils.getForgotEmail(VerifyPasswordCodeActivity.this));
        userLogin.enqueue(new Callback<VerifyCodeResponseModel>() {
            @Override
            public void onResponse(Call<VerifyCodeResponseModel> call, Response<VerifyCodeResponseModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {

                } else if (response.body().getStatus()) {
                    Toast.makeText(VerifyPasswordCodeActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    GeneralUtils.putStringValueInEditor(VerifyPasswordCodeActivity.this, "api_token", response.body().getData().getUser().getToken());
                    codeLayout.setVisibility(View.GONE);
                    newPassLayout.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(VerifyPasswordCodeActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VerifyCodeResponseModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(VerifyPasswordCodeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void apiCallChangePassword() {
        APIServices services = APIClient.getClient(GeneralUtils.getApiToken(VerifyPasswordCodeActivity.this)).create(APIServices.class);
        Call<ChangePasswordModel> userLogin = services.changePassword(strNewPassword, strConfirmNewPassword);
        userLogin.enqueue(new Callback<ChangePasswordModel>() {
            @Override
            public void onResponse(Call<ChangePasswordModel> call, Response<ChangePasswordModel> response) {
                alertDialog.dismiss();
                if (response.body() == null) {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(VerifyPasswordCodeActivity.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(VerifyPasswordCodeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response.body().getStatus()) {
                    Toast.makeText(VerifyPasswordCodeActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(VerifyPasswordCodeActivity.this, LoginActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordModel> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(VerifyPasswordCodeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean validate() {
        valid = true;

        strCode = etVerifyCode.getText().toString();

        if (strCode.isEmpty()) {
            etVerifyCode.setError("please enter your code to verify");
            valid = false;
        } else {
            etVerifyCode.setError(null);
        }
        return valid;
    }

    private boolean isValid() {
        valid = true;

        strNewPassword = etNewPassword.getText().toString();
        strConfirmNewPassword = etConfirmNewPassword.getText().toString();

        if (strNewPassword.isEmpty()) {
            etNewPassword.setError("enter your new password");
            valid = false;
        } else {
            etNewPassword.setError(null);
        }

        if (strConfirmNewPassword.isEmpty()) {
            etConfirmNewPassword.setError("confirm your new password");
            valid = false;
        } else {
            etConfirmNewPassword.setError(null);
        }
        return valid;
    }
}
