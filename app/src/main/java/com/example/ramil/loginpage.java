package com.example.ramil;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginpage extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;
    //Button lognbtn;
    FirebaseAuth mAuth;
    ProgressBar progsbar;
    TextView textView;
    ImageView lognbtn;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.Email);
        editTextPassword = findViewById(R.id.Password);
        lognbtn = findViewById(R.id.loginbtn);
        progsbar = findViewById(R.id.progbar);
        textView = findViewById(R.id.regNow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),regpage.class);
                startActivity(intent);
                finish();
            }
        });

       lognbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               progsbar.setVisibility(View.VISIBLE);
               String email, password;
               email = String.valueOf(editTextEmail.getText());
               password = String.valueOf(editTextPassword.getText());

               if (TextUtils.isEmpty(email)){
                   Toast.makeText(loginpage.this,"Please Enter Your Email", Toast.LENGTH_SHORT).show();
                   return;
               }

               if (TextUtils.isEmpty(password)){
                   Toast.makeText(loginpage.this,"Please Enter Your Password", Toast.LENGTH_SHORT).show();
                   return;
               }

               mAuth.signInWithEmailAndPassword(email, password)
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               progsbar.setVisibility(View.GONE);
                               if (task.isSuccessful()) {
                                   Toast.makeText(getApplicationContext(),"Successful login", Toast.LENGTH_SHORT).show();
                                   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                   startActivity(intent);
                                   finish();
                               } else {
                                   Toast.makeText(loginpage.this, "Authentication failed.",
                                           Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
           }
       });

    }
}