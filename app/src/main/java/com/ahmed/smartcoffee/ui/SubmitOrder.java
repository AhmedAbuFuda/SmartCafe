package com.ahmed.smartcoffee.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmed.smartcoffee.R;
import com.ahmed.smartcoffee.pojo.Order;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SubmitOrder extends AppCompatActivity {
    FirebaseAuth mAuth;
    private DatabaseReference reference;
    FirebaseUser user;
    TextView textView1, textView2, textView3, textView4, textView5;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_order);
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        reference = db.getReference();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        calling();

        savedInstanceState = getIntent().getExtras();
        String name = savedInstanceState.getString("name");
        String size = savedInstanceState.getString("size");
        String quantity = savedInstanceState.getString("quantity");
        String table = savedInstanceState.getString("table");
        String price = savedInstanceState.getString("price");
        int image = savedInstanceState.getInt("image");

        textView1.setText(size);
        textView2.setText(quantity);
        textView3.setText(table);
        textView4.setText(price);
        textView5.setText(name);
        imageView.setImageResource(image);
    }
    void calling(){
        textView1 = findViewById(R.id.size);
        textView2 = findViewById(R.id.quantit);
        textView3 = findViewById(R.id.table);
        textView4 = findViewById(R.id.totalprice);
        textView5 = findViewById(R.id.name);
        imageView = findViewById(R.id.imageView6);
    }

    private void createOrders(){
        user = mAuth.getCurrentUser();
        String key = reference.push().getKey();
        Order orderModel = new Order(key,textView5.getText().toString(),textView1.getText().toString(),textView2.getText().toString(),
                textView3.getText().toString(),textView4.getText().toString());
        reference.child("Orders").child(key).setValue(orderModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(SubmitOrder.this,"Done",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SubmitOrder.this, menu.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SubmitOrder.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void confirm(View view) {
        createOrders();
    }

    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }
}