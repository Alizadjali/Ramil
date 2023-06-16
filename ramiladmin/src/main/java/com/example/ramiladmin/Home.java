package com.example.ramiladmin;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ramiladmin.models.sosRecords;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class Home extends AppCompatActivity {

    private RecyclerView recyclerView;
     static sosCasesRecordsAdabter sosrecordAdapterobj;
    private List<sosRecords> sosrecordList;

    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        recyclerView = findViewById(R.id.soscasesViewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sosrecordList = new ArrayList<>();
        sosrecordAdapterobj = new sosCasesRecordsAdabter(sosrecordList, getApplicationContext());

        // Set the adapter to the RecyclerView
        recyclerView.setAdapter(sosrecordAdapterobj);

        databaseReference = FirebaseDatabase.getInstance().getReference("soscases");


        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                sosrecordList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    sosRecords record = snapshot.getValue(sosRecords.class);

                    record.setUserId(snapshot.getKey());

                    System.out.println(snapshot.getValue());
                    if(record.getisActive()){
                        sosrecordList.add(record);
                    }

                }

                sosrecordAdapterobj.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors
                Toast.makeText(Home.this, "Error: Can't retrive data", Toast.LENGTH_SHORT).show();
            }
        };
        databaseReference.addValueEventListener(valueEventListener);

    }
}
