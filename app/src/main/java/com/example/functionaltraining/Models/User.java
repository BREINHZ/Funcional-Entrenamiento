package com.example.functionaltraining.Models;

import com.example.functionaltraining.DataAccess.DataBaseApi.Service.ApiService;
import com.example.functionaltraining.DataAccess.DatabaseSQLite.Daos.UserDao;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import okhttp3.ResponseBody;

public class User {
    private String id;
    private String email;
    private String name;
    private String phone;
    private String password;
    private String confPassword;

    public boolean validateFieldsUser() {
        return email != null && !email.isEmpty() &&
                password != null && !password.isEmpty() &&
                name != null && !name.isEmpty() &&
                phone != null && !phone.isEmpty();
    }

    public boolean validateCredentialsUser() {
        return email != null && !email.isEmpty() &&
                password != null && !password.isEmpty();
    }

    public boolean validatePassEqualConfirPass() {
        return password.equals(confPassword);
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //Metodos de consumos SQlite
    public long initUserSection(UserDao dao){return dao.verifyUserCredentials(email, password);}
    public int insertUser(UserDao dao){return (int) dao.insertUser(this);}

    //Metodos para consumo Api
    public void authUser(User user, ApiService api, Callback<ResponseBody> callback){
        Gson gson = new Gson();
        String usuarioJson = gson.toJson(user);

        // Convertir el String JSON a RequestBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), usuarioJson);
        Call<ResponseBody> response = api.loginUser(requestBody);
        response.enqueue(callback);
    }
}
