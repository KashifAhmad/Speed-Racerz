package com.techease.speedracerz.dataModels.singupDataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingupDataModel {
    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
