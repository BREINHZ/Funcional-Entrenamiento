package com.example.functionaltraining.DataAccess.DatabaseSQLite.Helper;

import static com.example.functionaltraining.Utils.Constants.CREATE_TABLE_USERS;
import static com.example.functionaltraining.Utils.Constants.TABLE_USERS;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "functionaltraining.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);

        insertUserDefect(db);
    }

    private void insertUserDefect(SQLiteDatabase db){
        // Insertar un usuario por defecto
        ContentValues values = new ContentValues();
        values.put("email", "admin@admin.com"); // Email por defecto
        values.put("name", "Admin"); // Nombre por defecto
        values.put("phone", "3121234568"); // Teléfono por defecto
        values.put("pass", "1234Admin"); // Contraseña por defecto

        db.insert(TABLE_USERS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
        onCreate(db);
    }
}
