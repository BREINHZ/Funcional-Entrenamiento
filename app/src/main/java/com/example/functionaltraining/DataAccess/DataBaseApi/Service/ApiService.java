package com.example.functionaltraining.DataAccess.DataBaseApi.Service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.http.Header;
import okhttp3.ResponseBody;

public interface ApiService {
    @POST("auth/register")
    Call<ResponseBody> registerUser(@Body RequestBody body);

    @POST("auth/login")
    Call<ResponseBody> loginUser(@Body RequestBody body);

    @POST("protected/endpoint")
    Call<ResponseBody> protectedEndpoint(@Header("Authorization") String token, @Body RequestBody body);
}
