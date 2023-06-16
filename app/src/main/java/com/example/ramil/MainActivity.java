package com.example.ramil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Arrays;
import java.util.List;
import android.Manifest;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button outbtn;
    TextView text;
    CardView ntrip,pretrip,sosbtn;
    FirebaseUser user;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        outbtn = findViewById(R.id.logoutbtn);
        ntrip = findViewById(R.id.newtrip);
        text = findViewById(R.id.userhome);
        pretrip = findViewById(R.id.pretrip);
        sosbtn = findViewById(R.id.sosbtn);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        user = auth.getCurrentUser();
        if (user == null){
            Intent intent = new Intent(getApplicationContext(), loginpage.class);
            startActivity(intent);
            finish();
        }
        else {
             text.setText(user.getEmail());
        }

        outbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), loginpage.class);
                startActivity(intent);
                finish();
            }
        });
        ntrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,newtrip.class);
                startActivity(intent);
            }
        });

        pretrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PrevTrips.class);
                startActivity(intent);
            }
        });

        sosbtn.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String authenticatedUserEmail = user.getEmail();

                databaseReference = FirebaseDatabase.getInstance().getReference();
                double latitude = getLocationCordinates().get(0);
                double longitude = getLocationCordinates().get(1);

                databaseReference.child("soscases").child(user.getUid()).child("latitude").setValue(latitude);
                databaseReference.child("soscases").child(user.getUid()).child("longitude").setValue(longitude);
                databaseReference.child("soscases").child(user.getUid()).child("email").setValue(authenticatedUserEmail);
                databaseReference.child("soscases").child(user.getUid()).child("isActive").setValue(true);
                Toast.makeText(getApplicationContext(),"The SOS Case is Created", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private List<Double> getLocationCordinates() {
        final int REQUEST_CODE_PERMISSION = 123;

        // Check for location permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request location permission if not granted
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_PERMISSION);
            return null;
        }


        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Get the last known location
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
            // Retrieve the latitude and longitude values
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            // Return the coordinates as a double array
            return Arrays.asList(latitude, longitude);
        } else {
            Toast.makeText(this, "Location not available", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}