package com.example.functionaltraining.Presentation.AccessAcount.Login.Implementations;

import android.content.Context;

import com.example.functionaltraining.DataAccess.DataBaseApi.Builder.RetrofitClientBuilder;
import com.example.functionaltraining.DataAccess.DataBaseApi.Service.ApiService;
import com.example.functionaltraining.DataAccess.DatabaseSQLite.Daos.UserDao;
import com.example.functionaltraining.DataAccess.SharedPreferences.SessionManager;
import com.example.functionaltraining.Models.User;
import com.example.functionaltraining.Presentation.AccessAcount.Login.Interfaces.ILoginView;
import com.example.functionaltraining.R;
import com.example.functionaltraining.Utils.DialogueGenerico;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginPresenter {
    private final Context context;
    private final ILoginView loginView;
    private final UserDao dao;
    private final SessionManager sessionManager;
    private ApiService apiService;

    public LoginPresenter( Context context, ILoginView view, UserDao dao) {
        this.context = context;
        this.loginView = view;
        this.sessionManager = new SessionManager(context);
        this.dao = dao;
        dao.openDb();
        Retrofit retrofit = new RetrofitClientBuilder()
                .baseUrl("https://api.tu-servidor.com/")
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public void startSection(User user){
        if (!user.validateCredentialsUser()) {
            loginView.showDialogFragment(R.string.fields_empty, context.getString(R.string.details_fields_empty), DialogueGenerico.TypeDialogue.ADVERTENCIA);
            return;
        }
        String idUser = String.valueOf(user.initUserSection(dao));
        user.authUser(user, apiService, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println("Registro exitoso: " + response.body().string());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("Registro exitoso: " + t.getMessage());
            }
        });
        if (idUser.equals("-1")){
            loginView.credentialsIncorrect();
        }else {
            sessionManager.createLoginSession(user.getEmail(), idUser);
            loginView.credentialsCorrect();
        }
    }
}
