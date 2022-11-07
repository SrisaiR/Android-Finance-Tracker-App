package com.example.financialtracker.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.financialtracker.models.TransactionModel;

import java.util.ArrayList;

public class TransactionDBHelper extends SQLiteOpenHelper {
    public static String dbName = "TransactionDb";
    public static int dbVersion = 1;

    public TransactionDBHelper(@Nullable Context context, String username) {
        super(context, username + dbName, null, dbVersion);
    }

    public boolean insert_transaction(int day, int month, int year,
                                      String title, String category,
                                      float amount, int isExpense) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("day", day);
        contentValues.put("month", month);
        contentValues.put("year", year);
        contentValues.put("title", title);
        contentValues.put("category", category);
        contentValues.put("amount", amount);
        contentValues.put("isExpense", isExpense);
        long result = sqLiteDatabase.insert("TRANSACTIONS", null, contentValues);
        return result != -1;
    }

    public ArrayList<TransactionModel> get_transactions() {
        ArrayList<TransactionModel> transactionModels = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("TRANSACTIONS", null, null,
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            TransactionModel transactionModel = new TransactionModel(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("day")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("month")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("year")),
                    cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    cursor.getString(cursor.getColumnIndexOrThrow("category")),
                    cursor.getFloat(cursor.getColumnIndexOrThrow("amount")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("isExpense"))
            );
            transactionModels.add(transactionModel);
        }
        cursor.close();
        return transactionModels;
    }

    public String get_total_amount() {
        ArrayList<TransactionModel> transactionModels = get_transactions();
        float total = 0.0f;
        for (TransactionModel transaction : transactionModels) {
            if (transaction.isExpense() == 0) {
                total -= transaction.getAmount();
            } else {
                total += transaction.getAmount();
            }
        }
        return Float.toString(total);
    }

    public ArrayList<TransactionModel> get_transactions_by_category(String category) {
        ArrayList<TransactionModel> transactionModels = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT * FROM TRANSACTIONS WHERE category = ?",
                new String[]{category});

        while (cursor.moveToNext()) {
            TransactionModel transactionModel = new TransactionModel(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("day")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("month")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("year")),
                    cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    cursor.getString(cursor.getColumnIndexOrThrow("category")),
                    cursor.getFloat(cursor.getColumnIndexOrThrow("amount")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("isExpense"))
            );
            transactionModels.add(transactionModel);
        }
        cursor.close();
        return transactionModels;
    }

    public String get_sum_of_category(String category) {
        ArrayList<TransactionModel> transactionModels = get_transactions_by_category(category);
        float total = 0.0f;
        for (TransactionModel transaction : transactionModels) {
            if (transaction.isExpense() == 0) {
                total -= transaction.getAmount();
            } else {
                total += transaction.getAmount();
            }
        }
        return Float.toString(total);
    }

    public ArrayList<TransactionModel> get_transactions_by_date(int day, int month, int year) {
        ArrayList<TransactionModel> transactionModels = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT * FROM TRANSACTIONS WHERE day = ? AND month = ? AND year = ?",
                new String[]{
                        Integer.toString(day),
                        Integer.toString(month),
                        Integer.toString(year)
                }
        );

        while (cursor.moveToNext()) {
            TransactionModel transactionModel = new TransactionModel(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("day")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("month")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("year")),
                    cursor.getString(cursor.getColumnIndexOrThrow("title")),
                    cursor.getString(cursor.getColumnIndexOrThrow("category")),
                    cursor.getFloat(cursor.getColumnIndexOrThrow("amount")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("isExpense"))
            );
            transactionModels.add(transactionModel);
        }
        cursor.close();
        return transactionModels;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE TRANSACTIONS(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "day INTEGER," +
                "month INTEGER," +
                "year INTEGER," +
                "title TEXT," +
                "category TEXT," +
                "amount REAL," +
                "isExpense INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE if exists TRANSACTIONS");
    }
}
