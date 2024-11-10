package com.example.functionaltraining.DataAccess.DataBaseApi;

import com.example.functionaltraining.DataAccess.DataBaseApi.Builder.RetrofitClientBuilder;
import com.example.functionaltraining.DataAccess.DataBaseApi.Service.ApiService;

import retrofit2.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class ApiClient {
    private ApiService apiService;

    // Constructor con Builder para token opcional
    public ApiClient(String token) {
        Retrofit retrofit = new RetrofitClientBuilder()
                .baseUrl("http://localhost:10573/api/")
                .token(token)  // Token opcional
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    // Método para registrar usuario (sin token)
    public Call<ResponseBody> registerUser(String jsonBody) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);
        return apiService.registerUser(body);
    }

    // Método para loguear usuario (sin token)
    public Call<ResponseBody> loginUser(String jsonBody) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);
        return apiService.loginUser(body);
    }

    // Método para un endpoint protegido (con token)
    public Call<ResponseBody> protectedEndpoint(String token, String jsonBody) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);
        return apiService.protectedEndpoint("Bearer " + token, body);
    }
}
