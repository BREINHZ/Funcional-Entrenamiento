package com.example.functionaltraining.DataAccess.DataBaseApi.Builder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Interceptor;
import okhttp3.Response;
import java.io.IOException;

public class RetrofitClientBuilder {
    private String baseUrl;
    private String token;

    // Método para establecer la URL base
    public RetrofitClientBuilder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    // Método para establecer el token
    public RetrofitClientBuilder token(String token) {
        this.token = token;
        return this;
    }

    // Método para construir la instancia de Retrofit
    public Retrofit build() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        // Agregar interceptor si el token no es nulo
        if (token != null && !token.isEmpty()) {
            clientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + token)
                            .build();
                    return chain.proceed(request);
                }
            });
        }

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
    }
}
