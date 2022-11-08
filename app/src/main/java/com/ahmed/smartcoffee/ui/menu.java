package com.ahmed.smartcoffee.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ahmed.smartcoffee.R;

public class menu extends AppCompatActivity {
ImageView img1,img2,img3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        calling();
        handling();
    }
    void calling(){
        img1 = findViewById(R.id.img_pepsi);
        img2 = findViewById(R.id.img_tea);
        img3 = findViewById(R.id.img_mirinda);
    }
    void handling(){
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this, Item.class);
                intent.putExtra("image",R.drawable.pepsi);
                intent.putExtra("name","Cafe Latte");
                startActivity(intent);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this,Item.class);
                intent.putExtra("image",R.drawable.tea);
                intent.putExtra("name","Iced Cafe");
                startActivity(intent);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this,Item.class);
                intent.putExtra("image",R.drawable.mirinda);
                intent.putExtra("name","Captchino");
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}