package com.ahmed.smartcoffee.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmed.smartcoffee.R;

public class Item extends AppCompatActivity {
    LinearLayout linearLayout;
    CardView cardView;
    ImageView imageView;
    TextView text, text2, text3;
    Button btn1,btn2;
    int quantity = 1;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Spinner sp;
    String tables[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        calling();

        savedInstanceState = getIntent().getExtras();
        int image = savedInstanceState.getInt("image");
        String name = savedInstanceState.getString("name");

        text.setText(name);
        imageView.setImageResource(image);

        increment();
        decrement();
        spinner();
        price(quantity);
    }

    void calling(){
        linearLayout = findViewById(R.id.linear);
        cardView = findViewById(R.id.card);
        imageView = findViewById(R.id.imageView5);
        text = findViewById(R.id.textView6);
        btn1 = findViewById(R.id.increment);
        btn2 = findViewById(R.id.decrement);
        text2 = findViewById(R.id.quantity);
        text3 = findViewById(R.id.price);
        radioGroup = findViewById(R.id.radiogroup);
        sp = findViewById(R.id.spinner);

        text3.setText("9 EGP");
    }
    void increment(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity == 10) {
                    Toast.makeText(Item.this, "You cannot have more than 10 coffees", Toast.LENGTH_SHORT).show();
                    return;
                }
                quantity++;
                text2.setText(""+quantity);
                price(quantity);
            }
        });
    }
    void decrement(){
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity == 1) {
                    Toast.makeText(Item.this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
                    return;
                }
                quantity--;
                text2.setText(""+quantity);
                price(quantity);
            }
        });
    }
    void price(int number){
        final RadioButton small = findViewById(R.id.radioButton2);
        final RadioButton medium = findViewById(R.id.radioButton4);
        final RadioButton large = findViewById(R.id.radioButton5);
            if (small.isChecked()){
            int x = number * 9;
            text3.setText(""+x+" EGP");
            }else if (medium.isChecked()){
            int x = number * 12;
            text3.setText(""+x+" EGP");
            }else if (large.isChecked()){
            int x = number * 15;
            text3.setText(""+x+" EGP");
            }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (small.isChecked()){
                    int x = number * 9;
                    text3.setText(""+x+" EGP");
                }else if (medium.isChecked()){
                    int x = number * 12;
                    text3.setText(""+x+" EGP");
                }else if (large.isChecked()){
                    int x = number * 15;
                    text3.setText(""+x+" EGP");
                }
            }
        });
    }
    void spinner(){
        tables = getResources().getStringArray(R.array.table);
        ArrayAdapter adapter = new ArrayAdapter(Item.this,R.layout.support_simple_spinner_dropdown_item,tables);
        sp.setAdapter(adapter);
    }


    public void order(View view) {
        Intent intent = new Intent(Item.this, SubmitOrder.class);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        if(radioButton.getText() != null || text2.getText() != null || sp.getSelectedItem() != null || text3.getText() != null){
            intent.putExtra("name",text.getText().toString());
            intent.putExtra("size",radioButton.getText().toString());
            intent.putExtra("quantity",text2.getText().toString());
            intent.putExtra("table",sp.getSelectedItem().toString());
            intent.putExtra("price",text3.getText().toString());
            intent.putExtra("image",R.drawable.logo);
            startActivity(intent);
        }else {
            Toast.makeText(Item.this,"Invalid Value",Toast.LENGTH_SHORT);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}