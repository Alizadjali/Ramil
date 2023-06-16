package com.example.ramil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailTrip extends AppCompatActivity {

    TextView DetTitle,DetDate,DetTripname,DetTripkm,DetTripNum,DetTripMed,DetTripPhoneP,DetTripPhoneSec,DetChild,DetDis,DetRaids,DetFaids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trip);

        DetTitle = findViewById(R.id.DetTitle);
        DetDate = findViewById(R.id.Detdate);

        DetTripname = findViewById(R.id.DetTripname);
        DetTripkm = findViewById(R.id.DetTripkm);
        DetTripNum = findViewById(R.id.DetTripNum);
        DetTripMed = findViewById(R.id.DetTripMed);
        DetTripPhoneP = findViewById(R.id.DetTripPhoneP);
        DetTripPhoneSec = findViewById(R.id.DetTripPhoneSec);

        DetChild = findViewById(R.id.DetChild);
        DetDis = findViewById(R.id.DetDis);
        DetRaids = findViewById(R.id.DetRaids);
        DetFaids = findViewById(R.id.DetFaids);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            DetTitle.setText(bundle.getString("destination"));
            DetDate.setText(bundle.getString("date"));
            DetTripname.setText(bundle.getString("tripName"));
            DetTripkm.setText(bundle.getString("tripKm"));
            DetTripNum.setText(bundle.getString("personNumber"));
            DetTripMed.setText(bundle.getString("medicalCondition"));
            DetTripPhoneP.setText(bundle.getString("phonePrim"));
            DetTripPhoneSec.setText(bundle.getString("phoneSec"));
            DetChild.setText(bundle.getString("ChildOnTrip"));
            DetDis.setText(bundle.getString("DiseaseType"));
            DetRaids.setText(bundle.getString("RoadAids"));
            DetFaids.setText(bundle.getString("FirsAid"));

        }
    }
}