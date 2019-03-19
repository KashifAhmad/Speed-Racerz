package com.techease.speedracerz.networking;


import com.techease.speedracerz.dataModels.changePasswordModels.ChangePasswordModel;
import com.techease.speedracerz.dataModels.changePasswordModels.ResetPasswordResponeModel;
import com.techease.speedracerz.dataModels.changePasswordModels.verifycodemodel.VerifyCodeResponseModel;
import com.techease.speedracerz.dataModels.eventsDataModels.EventResponseModel;
import com.techease.speedracerz.dataModels.loginModels.LoginResponse;
import com.techease.speedracerz.dataModels.profileDataModel.ProfileResponseModel;
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
                                  @Field("latitute") String latitude,
                                  @Field("longitude") String longitude,
                                  @Field("deviceToken") String token);

    @FormUrlEncoded
    @POST("reset-password")
    Call<ResetPasswordResponeModel> resetPassword(@Field("email") String email);

    @FormUrlEncoded
    @POST("reset-password-verify")
    Call<VerifyCodeResponseModel> verifyPasswordCode(@Field("code") String code,
                                                     @Field("email") String email);

    @FormUrlEncoded
    @POST("change-password")
    Call<ChangePasswordModel> changePassword(@Field("newPassword") String code,
                                             @Field("confirmPassword") String email);

    @GET("user-profile")
    Call<ProfileResponseModel> profile();

    @GET("events")
    Call<EventResponseModel> events();


}
