package com.example.financialtracker.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.financialtracker.R;
import com.example.financialtracker.helpers.TransactionDBHelper;

public class DashboardFragment extends Fragment {

    TextView tv_dash_res_total, tv_dash_res_food, tv_dash_res_home, tv_dash_res_travel,
            tv_dash_res_salary, tv_dash_res_health, tv_dash_res_education, tv_dash_res_groceries,
            tv_dash_res_entertainment, tv_dash_res_miscellaneous;
    TransactionDBHelper transactionDBHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        transactionDBHelper = new TransactionDBHelper(getContext(),
                getArguments().getString("username"));

        tv_dash_res_total = view.findViewById(R.id.tv_dash_res_total);
        tv_dash_res_food = view.findViewById(R.id.tv_dash_res_food);
        tv_dash_res_home = view.findViewById(R.id.tv_dash_res_home);
        tv_dash_res_travel = view.findViewById(R.id.tv_dash_res_travel);
        tv_dash_res_salary = view.findViewById(R.id.tv_dash_res_salary);
        tv_dash_res_health = view.findViewById(R.id.tv_dash_res_health);
        tv_dash_res_education = view.findViewById(R.id.tv_dash_res_education);
        tv_dash_res_groceries = view.findViewById(R.id.tv_dash_res_groceries);
        tv_dash_res_entertainment = view.findViewById(R.id.tv_dash_res_entertainment);
        tv_dash_res_miscellaneous = view.findViewById(R.id.tv_dash_res_miscellaneous);

        tv_dash_res_total.setText(transactionDBHelper.get_total_amount());
        tv_dash_res_food.setText(transactionDBHelper.get_sum_of_category("Food"));
        tv_dash_res_home.setText(transactionDBHelper.get_sum_of_category("Home"));
        tv_dash_res_travel.setText(transactionDBHelper.get_sum_of_category("Travel"));
        tv_dash_res_salary.setText(transactionDBHelper.get_sum_of_category("Salary"));
        tv_dash_res_health.setText(transactionDBHelper.get_sum_of_category("Health"));
        tv_dash_res_education.setText(transactionDBHelper.get_sum_of_category("Education"));
        tv_dash_res_groceries.setText(transactionDBHelper.get_sum_of_category("Groceries"));
        tv_dash_res_entertainment.setText(transactionDBHelper.get_sum_of_category("Entertainment"));
        tv_dash_res_miscellaneous.setText(transactionDBHelper.get_sum_of_category("Miscellaneous"));
    }
}