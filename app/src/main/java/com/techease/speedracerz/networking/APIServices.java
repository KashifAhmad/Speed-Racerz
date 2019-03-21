package com.techease.speedracerz.networking;


import com.techease.speedracerz.dataModels.changePasswordModels.ChangePasswordModel;
import com.techease.speedracerz.dataModels.changePasswordModels.ResetPasswordResponeModel;
import com.techease.speedracerz.dataModels.changePasswordModels.verifycodemodel.VerifyCodeResponseModel;
import com.techease.speedracerz.dataModels.eventDetailModels.EventDetailsResponseModel;
import com.techease.speedracerz.dataModels.eventsDataModels.EventResponseModel;
import com.techease.speedracerz.dataModels.loginModels.LoginResponse;
import com.techease.speedracerz.dataModels.profileDataModel.ProfileResponseModel;
import com.techease.speedracerz.dataModels.signupModels.SignupResponseModel;
import com.techease.speedracerz.dataModels.signupModels.cities.CitiesResponseModel;
import com.techease.speedracerz.dataModels.signupModels.country.CountryResponseModel;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Field;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIServices {

    @GET("countries")
    Call<CountryResponseModel> getCountries();

    @GET("cities")
    Call<CitiesResponseModel> getCities(@Query("countryId") int id);

    @FormUrlEncoded
    @POST("register")
    Call<SignupResponseModel> userSignUp(@Field("name") String name,
                                         @Field("email") String email,
                                         @Field("password") String password,
                                         @Field("type") String type,
                                         @Field("category") String category,
                                         @Field("country") String country,
                                         @Field("city") String city,
                                         @Field("address") String address,
                                         @Field("company") String company,
                                         @Field("latitute") String lat,
                                         @Field("longitude") String lon,
                                         @Field("deviceType") String deviceType,
                                         @Field("deviceToken") String deviceToken);

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

    @GET("get-event-detail")
    Call<EventDetailsResponseModel> getEventDetails(@Query("eventId") int id);


}
