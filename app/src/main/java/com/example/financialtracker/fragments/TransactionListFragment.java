package com.example.financialtracker.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.financialtracker.R;
import com.example.financialtracker.adapters.RvAdapter;
import com.example.financialtracker.helpers.TransactionDBHelper;
import com.example.financialtracker.models.TransactionModel;
import com.example.financialtracker.screens.TransactionEditActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

public class TransactionListFragment extends Fragment {

    RecyclerView rv_transactions;
    FloatingActionButton fab_add;
    ArrayList<TransactionModel> transactionModels = new ArrayList<>();
    TransactionDBHelper transactionDBHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transaction_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        transactionDBHelper = new TransactionDBHelper(getContext(),
                getArguments().getString("username"));

        fab_add = getView().findViewById(R.id.fab_add);
        fab_add.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TransactionEditActivity.class);
            intent.putExtra("username", getArguments().getString("username"));
            startActivity(intent);
        });

        rv_transactions = view.findViewById(R.id.rv_transactions);
        setTransactionModels();
        RvAdapter rvAdapter = new RvAdapter(getContext(), transactionModels);
        rv_transactions.setHasFixedSize(true);
        rv_transactions.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv_transactions.setAdapter(rvAdapter);
    }

    public void setTransactionModels() {
        transactionModels = transactionDBHelper.get_transactions();
    }
}