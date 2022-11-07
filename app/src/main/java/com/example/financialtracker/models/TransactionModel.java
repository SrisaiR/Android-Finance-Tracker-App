package com.example.financialtracker.models;

import java.util.Date;

public class TransactionModel {
    private int id;
    private int priority;
    private int day;
    private int month;
    private int year;
    private String title;
    private String category;
    private float amount;
    private int isExpense;

    public TransactionModel(){}

    public TransactionModel(int id,
                            int day,
                            int month,
                            int year,
                            String title,
                            String category,
                            float amount,
                            int isExpense) {
        this.id = id;
        this.day = day;
        this.month = month;
        this.year = year;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int isExpense() {
        return isExpense;
    }

    public void setExpense(int expense) {
        isExpense = expense;
    }
}
