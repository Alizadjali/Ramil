package com.example.ramiladmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;


public class Login extends AppCompatActivity {

    Button loginbutton;
    EditText email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


        loginbutton = findViewById(R.id.lgoinbtn);
        email = findViewById(R.id.usernametext);
        password = findViewById(R.id.passwordtext);


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                loginbutton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   String emailstring = email.getText().toString().trim();
                   String passwordstring = password.getText().toString().trim();


                   if(emailstring!= null && passwordstring!=null){

                       FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                       CollectionReference adminusersRef = firebaseFirestore.collection("adminusers");
                       Query userAdminQuery = adminusersRef.whereEqualTo("email",emailstring);

                       userAdminQuery.get().addOnCompleteListener(task -> {
                           if(task.isSuccessful()){
                               QuerySnapshot querySnapshot = task.getResult();
                               if(querySnapshot != null && !querySnapshot.isEmpty()){
                                    firebaseAuth.signInWithEmailAndPassword(emailstring, passwordstring)
                                       .addOnCompleteListener(ttask -> {
                                           if (ttask.isSuccessful()) {
                                               Toast.makeText(getApplicationContext(),"Logged in successfully",Toast.LENGTH_LONG).show();
                                               Intent intent = new Intent(Login.this,Home.class);
                                               startActivity(intent);

                                           } else {

                                               Exception exception = ttask.getException();
                                               Toast.makeText(getApplicationContext(),"Login Faild: "+exception.getMessage(),Toast.LENGTH_LONG).show();

                                           }
                                       });

                               }
                               else{
                                   Toast.makeText(getApplicationContext(),"Denied: You aren't Admin",Toast.LENGTH_LONG).show();

                               }

                           }
                       });

                   }

               }
           }

                );

            }
        });

    }








}