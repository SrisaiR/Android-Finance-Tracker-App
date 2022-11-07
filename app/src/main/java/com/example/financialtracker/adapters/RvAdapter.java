package com.example.financialtracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financialtracker.R;
import com.example.financialtracker.models.TransactionModel;

import java.util.ArrayList;
import java.util.Date;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<TransactionModel> transactionModels;

    public RvAdapter(Context context, ArrayList<TransactionModel> transactionModels) {
        this.context = context;
        this.transactionModels = transactionModels;
    }

    @NonNull
    @Override
    public RvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.transaction_view, parent, false);

        return new RvAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.MyViewHolder holder, int position) {
        holder.tv_title.setText(transactionModels.get(position).getTitle());
        String date = Integer.toString(transactionModels.get(position).getDay()) + "/" +
                Integer.toString(transactionModels.get(position).getMonth()) + "/" +
                Integer.toString(transactionModels.get(position).getYear());
        holder.tv_date.setText(date);
        holder.tv_amount.setText(Float.toString(transactionModels.get(position).getAmount()));
        if (transactionModels.get(position).isExpense() == 0){
            holder.tv_amount.setTextColor(context.getResources().getColor(R.color.positive_cash));
        }
        else{
            holder.tv_amount.setTextColor(context.getResources().getColor(R.color.text_bg));
        }
        int resId = R.drawable.ic_baseline_attach_money_24;
        switch (transactionModels.get(position).getCategory()) {
            case "Food":
                resId = R.drawable.ic_baseline_restaurant_24;
                break;
            case "Home":
                resId = R.drawable.ic_baseline_home_24;
                break;
            case "Salary":
                resId = R.drawable.ic_baseline_work_24;
                break;
            case "Travel":
                resId = R.drawable.ic_baseline_directions_car_filled_24;
                break;
            case "Health":
                resId = R.drawable.ic_baseline_health_and_safety_24;
                break;
            case "Education":
                resId = R.drawable.ic_baseline_school_24;
                break;
            case "Groceries":
                resId = R.drawable.ic_baseline_local_grocery_store_24;
                break;
            case "Entertainment":
                resId = R.drawable.ic_baseline_local_movies_24;
                break;
            case "Miscellaneous":
                resId = R.drawable.ic_baseline_attach_money_24;
                break;
            default:
                resId = R.drawable.ic_baseline_error_outline_24;
        }
        holder.iv_icon.setImageResource(resId);
    }

    @Override
    public int getItemCount() {
        return transactionModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_icon;
        TextView tv_title;
        TextView tv_date;
        TextView tv_amount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_amount = itemView.findViewById(R.id.tv_amount);
        }
    }
}
