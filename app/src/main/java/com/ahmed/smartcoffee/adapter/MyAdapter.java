package com.ahmed.smartcoffee.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.smartcoffee.R;
import com.ahmed.smartcoffee.pojo.Order;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Order> mList;
    Context context;
    DatabaseReference reference;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    public  MyAdapter(Context context, ArrayList<Order> mList){
        this.context = context;
        this.mList = mList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Order model = mList.get(position);
        holder.name.setText(model.getName());
        holder.size.setText(model.getSize());
        holder.quantity.setText(model.getQuantity());
        holder.table.setText(model.getTable());
        holder.price.setText(model.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alter = new AlertDialog.Builder(view.getContext());
                alter.setIcon(R.drawable.confirm).setTitle("Confirm Order").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        reference = db.getReference();
                        reference.child("Machine").child("1").setValue(model);
                        reference.child("Confirm Orders").child(model.getId()).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                reference = db.getReference("Orders").child(model.getId());
                                reference.removeValue();
                                mList.remove(model);
                                Toast.makeText(view.getContext(),"Done",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(view.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      dialogInterface.dismiss();
                    }
                }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, size, quantity, table, price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.type_text);
            size = itemView.findViewById(R.id.size_text);
            quantity = itemView.findViewById(R.id.quantity_text);
            table = itemView.findViewById(R.id.table_text);
            price = itemView.findViewById(R.id.price_text);

        }
    }

}
