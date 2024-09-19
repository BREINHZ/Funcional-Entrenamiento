package com.example.functionaltraining.Utils;

public class Constants {

    //  Nombres Tablas
    public static final String TABLE_TRAINING= "projects";
    public static final String TABLE_USERS = "users";

    // Query Tablas
    public static final String CREATE_TABLE_PROJECTS = "CREATE TABLE " + TABLE_TRAINING + "("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "title TEXT NOT NULL,"
            + "description TEXT,"
            + "user_id TEXT NOT NULL,"
            + "date_init TEXT NOT NULL,"
            + "date_end TEXT NOT NULL,"
            + "imagen TEXT,"
            + "FOREIGN KEY (user_id) REFERENCES users(id)"
            + ")";
    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "email TEXT NOT NULL UNIQUE,"
            + "name TEXT NOT NULL,"
            + "phone TEXT NOT NULL,"
            + "pass TEXT NOT NULL"
            + ")";
}
