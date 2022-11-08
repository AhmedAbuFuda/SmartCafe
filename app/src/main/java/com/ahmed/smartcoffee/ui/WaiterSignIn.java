package com.ahmed.smartcoffee.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmed.smartcoffee.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WaiterSignIn extends AppCompatActivity {
    EditText editText, editText1;
    FirebaseAuth mAuth;
    String s,s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_sign_in);
        mAuth = FirebaseAuth.getInstance();
        calling();
    }
    void calling(){
        editText = findViewById(R.id.editTextTextEmailAddress);
        editText1 = findViewById(R.id.editTextTextPassword2);
    }
    private void signIn(){
        s =  editText.getText().toString();
        s1 =  editText1.getText().toString();
        mAuth.signInWithEmailAndPassword(s,s1).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser user = authResult.getUser();
                Intent i = new Intent(WaiterSignIn.this, ConfirmOrders.class);
                startActivity(i);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(WaiterSignIn.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void login(View view) {
        signIn();
    }
}