package com.example.financialtracker.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LoginDbHelper extends SQLiteOpenHelper {

    public static String dbName = "LoginDb";
    public static int dbVersion = 1;

    public LoginDbHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE USERS(username TEXT PRIMARY KEY, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE if exists USERS");
    }

    public boolean insert_user(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = sqLiteDatabase.insert("USERS", null, contentValues);
        return result != -1;
    }

    public boolean check_username(String username){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT * FROM USERS WHERE username = ?",
                new String[] {username});
        return cursor.getCount() > 0;
    }

    public boolean validate_username_and_password(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT * FROM USERS WHERE username = ? AND password = ?",
                new String[] {username, password});
        return cursor.getCount() > 0;
    }
}
