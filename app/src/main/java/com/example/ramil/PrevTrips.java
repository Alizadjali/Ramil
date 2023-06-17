package com.example.ramil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class PrevTrips extends AppCompatActivity {

    RecyclerView recyclerView;
    List<retDataClass> dataClassList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    FirebaseUser user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prev_trips);
        // here I added user and uid to test
        recyclerView = findViewById(R.id.rcv860);
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(PrevTrips.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(PrevTrips.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataClassList = new ArrayList<>();
        MyAdp adp = new MyAdp(PrevTrips.this, dataClassList);
        recyclerView.setAdapter(adp);
        //link database
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataClassList.clear(); //link adp
                for (DataSnapshot itemSnapshot: snapshot.getChildren())
                {
                    retDataClass retdataClass = itemSnapshot.getValue(retDataClass.class);
                    dataClassList.add(retdataClass);
                }

               adp.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });
    }
}