package com.example.financialtracker.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.financialtracker.R;
import com.example.financialtracker.helpers.TransactionDBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TransactionEditActivity extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance();
    EditText et_edit_title, et_edit_date, et_edit_amount;
    Spinner spinner_category;
    Button btn_done;
    ToggleButton btn_toggle_expense;
    TransactionDBHelper transactionDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_edit);


        et_edit_title = findViewById(R.id.et_edit_title);
        et_edit_date = findViewById(R.id.et_edit_date);
        et_edit_amount = findViewById(R.id.et_edit_amount);
        spinner_category = findViewById(R.id.spinner_category);
        btn_done = findViewById(R.id.btn_done);
        btn_toggle_expense = findViewById(R.id.btn_toggle_expense);
        btn_toggle_expense.setTextColor(getResources().getColor(R.color.text_bg));
        myCalendar.setTime(Calendar.getInstance().getTime());
        transactionDBHelper = new TransactionDBHelper(this,
                getIntent().getExtras().getString("username"));

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(
                this, R.array.my_categories, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_category.setAdapter(arrayAdapter);
        spinner_category.setSelection(0);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);

                String myFormat = "MM/dd/yy";
                SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
                et_edit_date.setText(dateFormat.format(myCalendar.getTime()));
            }
        };

        et_edit_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(TransactionEditActivity.this, date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });

        btn_toggle_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_toggle_expense.isChecked()) {
                    btn_toggle_expense.setTextColor(getResources().getColor(R.color.positive_cash));
                } else {
                    btn_toggle_expense.setTextColor(getResources().getColor(R.color.text_bg));
                }
            }
        });

        btn_done.setOnClickListener(view -> {
            int day = myCalendar.get(Calendar.DATE);
            int month = myCalendar.get(Calendar.MONTH);
            int year = myCalendar.get(Calendar.YEAR);
            String title = et_edit_title.getText().toString();
            String category = spinner_category.getSelectedItem().toString();
            float amount = Float.parseFloat(et_edit_amount.getText().toString());
            int isExpense = btn_toggle_expense.isChecked() ? 0 : 1;

            if (title.equals("") || category.equals("") || amount <= 0) {
                Toast.makeText(TransactionEditActivity.this, "Fill all fields",
                        Toast.LENGTH_SHORT).show();
            } else {
                transactionDBHelper.insert_transaction(day, month, year,
                        title, category, amount, isExpense);
            }
            Toast.makeText(this, "Transaction Added", Toast.LENGTH_SHORT).show();




            this.finish();
        });

    }
}