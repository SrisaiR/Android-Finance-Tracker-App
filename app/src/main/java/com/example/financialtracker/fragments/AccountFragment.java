package com.example.financialtracker.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.financialtracker.R;
import com.example.financialtracker.screens.LoginActivity;

public class AccountFragment extends Fragment {

    ImageButton btn_change_theme;
    TextView tv_username_result, tv_password_result;
    Button btn_signout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_change_theme = view.findViewById(R.id.btn_change_theme);
        tv_username_result = view.findViewById(R.id.tv_username_result);
        tv_password_result = view.findViewById(R.id.tv_password_result);
        btn_signout = view.findViewById(R.id.btn_signout);

        tv_username_result.setText(getArguments().getString("username"));
        tv_password_result.setText(getArguments().getString("password"));

        btn_signout.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO){
            btn_change_theme.setBackgroundResource(R.drawable.ic_round_dark_mode_24);
        }
        else{
            btn_change_theme.setBackgroundResource(R.drawable.ic_round_wb_sunny_24);
        }
        btn_change_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    btn_change_theme.setImageResource(R.drawable.ic_round_wb_sunny_24);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    btn_change_theme.setImageResource(R.drawable.ic_round_dark_mode_24);
                }
            }
        });
    }
}