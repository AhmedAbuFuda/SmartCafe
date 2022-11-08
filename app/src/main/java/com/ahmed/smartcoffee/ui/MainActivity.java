package com.ahmed.smartcoffee.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ahmed.smartcoffee.R;
import com.ahmed.smartcoffee.pojo.Capture;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void admin(View view) {
        Intent intent = new Intent(MainActivity.this, SignIn.class);
        startActivity(intent);
    }

    public void menu(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
        intentIntegrator.setPrompt("For flash use volume up key")
                .setBeepEnabled(true)
                .setOrientationLocked(true)
                .setCaptureActivity(Capture.class)
                .initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(intentResult.getContents().equals("SmartCoffee")){
            Intent intent = new Intent(MainActivity.this, menu.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"OOPS... You scan something weired",Toast.LENGTH_SHORT).show();
        }
    }

    public void waiter(View view) {
        Intent intent = new Intent(MainActivity.this, WaiterSignIn.class);
        startActivity(intent);
    }
}