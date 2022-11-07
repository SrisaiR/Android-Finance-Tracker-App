package com.example.financialtracker.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.financialtracker.R;
import com.example.financialtracker.fragments.AccountFragment;
import com.example.financialtracker.fragments.DashboardFragment;
import com.example.financialtracker.fragments.TransactionListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class NavigationActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    AccountFragment accountFragment = new AccountFragment();
    DashboardFragment dashboardFragment = new DashboardFragment();
    TransactionListFragment transactionListFragment = new TransactionListFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Bundle bundle = getIntent().getExtras();
        accountFragment.setArguments(bundle);
        dashboardFragment.setArguments(bundle);
        transactionListFragment.setArguments(bundle);

        bottomNavigationView = findViewById(R.id.bottom_nav_bar);

        getSupportFragmentManager().beginTransaction().
                replace(R.id.frame_container, dashboardFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.account:
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.frame_container, accountFragment).commit();
                        return true;
                    case R.id.dashboard:
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.frame_container, dashboardFragment).commit();
                        return true;
                    case R.id.transaction_list:
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.frame_container, transactionListFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}