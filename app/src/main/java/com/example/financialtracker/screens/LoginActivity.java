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

public class LoginActivity extends AppCompatActivity {

    EditText et_username, et_password;
    Button btn_login, btn_to_signup;
    ImageButton btn_change_theme_login;
    LoginDbHelper loginDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.et_login_username);
        et_password = findViewById(R.id.et_login_password);
        btn_login = findViewById(R.id.btn_login);
        btn_to_signup = findViewById(R.id.btn_to_signup);
        btn_change_theme_login = findViewById(R.id.btn_change_theme_login);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO){
            btn_change_theme_login.setBackgroundResource(R.drawable.ic_round_dark_mode_24);
        }
        else{
            btn_change_theme_login.setBackgroundResource(R.drawable.ic_round_wb_sunny_24);
        }

        loginDbHelper = new LoginDbHelper(this);

        btn_login.setOnClickListener(view -> {
            String username = et_username.getText().toString();
            String password = et_password.getText().toString();

            if (username.equals("") || password.equals("")) {
                Toast.makeText(this, "Please enter all fields",
                        Toast.LENGTH_SHORT).show();
            } else {
                boolean check_user_pass = loginDbHelper.
                        validate_username_and_password(username, password);
                if (check_user_pass) {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent_login_nav = new Intent(getApplicationContext(),
                            NavigationActivity.class);
                    intent_login_nav.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    intent_login_nav.putExtra("username", et_username.getText().toString());
                    intent_login_nav.putExtra("password", et_password.getText().toString());
                    startActivity(intent_login_nav);
                    this.finish();
                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_to_signup.setOnClickListener(view -> {
            Intent intent_signup = new Intent(this, SignupActivity.class);
            intent_signup.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_TASK_ON_HOME);
            startActivity(intent_signup);
            this.finish();
        });

        btn_change_theme_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    btn_change_theme_login.setImageResource(R.drawable.ic_round_wb_sunny_24);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    btn_change_theme_login.setImageResource(R.drawable.ic_round_dark_mode_24);
                }
            }
        });
    }
}