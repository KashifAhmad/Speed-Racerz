package com.techease.speedracerz.dataModels.loginModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDataModel {

    @SerializedName("user")
    @Expose
    private LoginUserModel user;

    public LoginUserModel getUser() {
        return user;
    }

    public void setUser(LoginUserModel user) {
        this.user = user;
    }

}
