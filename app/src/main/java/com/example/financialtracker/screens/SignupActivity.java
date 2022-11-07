package com.example.financialtracker.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.financialtracker.R;
import com.example.financialtracker.helpers.LoginDbHelper;

public class SignupActivity extends AppCompatActivity {

    EditText et_username, et_password, et_confirm_password;
    Button btn_signup, btn_to_login;
    ImageButton btn_change_theme_signup;
    LoginDbHelper loginDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        et_username = findViewById(R.id.et_signup_username);
        et_password = findViewById(R.id.et_signup_password);
        et_confirm_password = findViewById(R.id.et_signup_confirm_password);
        btn_signup = findViewById(R.id.btn_signup);
        btn_to_login = findViewById(R.id.btn_to_login);
        btn_change_theme_signup = findViewById(R.id.btn_change_theme_signup);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO){
            btn_change_theme_signup.setBackgroundResource(R.drawable.ic_round_dark_mode_24);
        }
        else{
            btn_change_theme_signup.setBackgroundResource(R.drawable.ic_round_wb_sunny_24);
        }

        loginDbHelper = new LoginDbHelper(this);

        btn_signup.setOnClickListener(view -> {
            String username = et_username.getText().toString();
            String password = et_password.getText().toString();
            String confirm_password = et_confirm_password.getText().toString();

            if (username.equals("") || password.equals("") || confirm_password.equals("")) {
                Toast.makeText(this, "Please enter all fields",
                        Toast.LENGTH_SHORT).show();
            } else {
                if (password.equals(confirm_password)) {
                    boolean user_exists = loginDbHelper.check_username(username);
                    if (user_exists) {
                        Toast.makeText(this, "User already exists",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        boolean user_inserted = loginDbHelper.insert_user(username, password);
                        if (user_inserted) {
                            Toast.makeText(this, "Successfully registered",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent_signup_nav = new Intent(getApplicationContext(),
                                    NavigationActivity.class);
                            intent_signup_nav.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                    Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                            intent_signup_nav.putExtra("username",
                                    et_username.getText().toString());
                            intent_signup_nav.putExtra("password",
                                    et_password.getText().toString());
                            startActivity(intent_signup_nav);
                            this.finish();
                        } else {
                            Toast.makeText(this, "Something went wrong!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(this, "Passwords do not match",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });

        btn_to_login.setOnClickListener(view -> {
            Intent intent_login = new Intent(getApplicationContext(), LoginActivity.class);
            intent_login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
            startActivity(intent_login);
            this.finish();
        });

        btn_change_theme_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    btn_change_theme_signup.setImageResource(R.drawable.ic_round_wb_sunny_24);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    btn_change_theme_signup.setImageResource(R.drawable.ic_round_dark_mode_24);
                }
            }
        });
    }
}