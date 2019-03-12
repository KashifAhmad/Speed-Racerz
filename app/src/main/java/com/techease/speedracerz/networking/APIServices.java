package com.techease.speedracerz.networking;


import com.techease.speedracerz.dataModels.loginModels.LoginResponse;
import com.techease.speedracerz.dataModels.signupModels.SignupResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by kashif on 4/2/18.
 */

public interface APIServices {

    @Multipart
    @POST("register")
    Call<SignupResponseModel> userSignUp(@Part("name") RequestBody name,
                                         @Part("email") RequestBody email,
                                         @Part("password") RequestBody password,
                                         @Part("confirmPassword") RequestBody passwordConfirmation,
                                         @Part("address") RequestBody address,
                                         @Part("phoneNumber") RequestBody phoneNumber,
                                         @Part("latitute") RequestBody lat,
                                         @Part("longitude") RequestBody lon,
                                         @Part("deviceType") RequestBody deviceType,
                                         @Part("deviceToken") RequestBody deviceToken,
                                         @Part MultipartBody.Part photo,
                                         @Part("profilePicture") RequestBody fileName);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> userLogin(@Field("email") String email,
                                  @Field("password") String password,
                                  @Field("deviceToken") String token);


}
