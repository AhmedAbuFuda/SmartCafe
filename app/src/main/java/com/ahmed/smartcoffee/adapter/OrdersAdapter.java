package com.ahmed.smartcoffee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.smartcoffee.R;
import com.ahmed.smartcoffee.pojo.Order;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersHolder> {
    ArrayList<Order> mList;
    Context context;

    public OrdersAdapter(ArrayList<Order> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public OrdersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new OrdersHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersHolder holder, int position) {
        Order model = mList.get(position);
        holder.name.setText(model.getName());
        holder.size.setText(model.getSize());
        holder.quantity.setText(model.getQuantity());
        holder.table.setText(model.getTable());
        holder.price.setText(model.getPrice());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class OrdersHolder extends RecyclerView.ViewHolder{
        TextView name, size, quantity, table, price;
        public OrdersHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.type_text);
            size = itemView.findViewById(R.id.size_text);
            quantity = itemView.findViewById(R.id.quantity_text);
            table = itemView.findViewById(R.id.table_text);
            price = itemView.findViewById(R.id.price_text);
        }
    }
}
