package com.example.functionaltraining.Models;

import com.example.functionaltraining.DataAccess.DatabaseSQLite.Daos.UserDao;

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
}
