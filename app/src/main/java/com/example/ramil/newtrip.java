package com.example.ramil;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class newtrip extends AppCompatActivity {
    Button datebtn,savbt;
    FirebaseAuth mAuth;
    FirebaseUser user;
    TextView txdate;
    EditText kmed,nped,sicked,pmobile,smobile,tripname,tripdest;
    AutoCompleteTextView act,childOnTrip,firstaid,roadaids;

    String[] items =  {"YES","NO"};
    ArrayAdapter<String> adapterItems;
    @SuppressWarnings("unchecked")




    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtrip);
        mAuth = FirebaseAuth.getInstance();
        txdate = findViewById(R.id.txrange);
        datebtn = findViewById(R.id.datebtn);
        savbt = findViewById(R.id.savbt);
        act = findViewById(R.id.act);
        kmed =findViewById(R.id.kmed);
        nped =findViewById(R.id.nped);
        sicked =findViewById(R.id.sicked);
        pmobile = findViewById(R.id.pmobile); //1
        smobile = findViewById(R.id.smobile);//2
        tripname = findViewById(R.id.tripname);//3
        childOnTrip = findViewById(R.id.childOnTrip);//4
        firstaid = findViewById(R.id.firstaid);//5
        roadaids = findViewById(R.id.roadaids);//6
        tripdest = findViewById(R.id.tripdest);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //date-picker code
        MaterialDatePicker datePicker=MaterialDatePicker.Builder.dateRangePicker()
                .setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(),
                        MaterialDatePicker.todayInUtcMilliseconds())).setTheme(R.style.ThemeOverlay_App_MaterialCalendar).build();

        //drop down list code
        adapterItems = new ArrayAdapter<String>(this,R.layout.cond_item,items);
        act.setAdapter(adapterItems);
        childOnTrip.setAdapter(adapterItems);
        firstaid.setAdapter(adapterItems);
        roadaids.setAdapter(adapterItems);
        //datebtn
        datebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.show(getSupportFragmentManager(),"Material_Range");
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        txdate.setText(datePicker.getHeaderText());
                    }
                });
            }
        });
        //the new method here start

        savbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send trip date to firebase 18F17860

                //I delete .child("UserTripData") on June 8
                DatabaseReference myRef = database.getReference("users")
                        .child(mAuth.getCurrentUser().getUid()).push();

                //step1
                TextView textView = (TextView) txdate;
                AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) act;
                EditText editText = (EditText) kmed;
                EditText editText1 = (EditText) nped;
                EditText editText2 = (EditText) sicked;
                EditText editText3 = (EditText) pmobile;
                EditText editText4 = (EditText) smobile;
                EditText editText5 = (EditText) tripname;
                EditText editText6 = (EditText) tripdest;
                AutoCompleteTextView autoCompleteTextView1 = (AutoCompleteTextView) childOnTrip;
                AutoCompleteTextView autoCompleteTextView2 = (AutoCompleteTextView) firstaid;
                AutoCompleteTextView autoCompleteTextView3 = (AutoCompleteTextView) roadaids;


                //step2
                String act = autoCompleteTextView.getText().toString();
                String txdate = textView.getText().toString();
                String tripdest = editText6.getText().toString();
                String kmed = editText.getText().toString();
                String nped = editText1.getText().toString();
                String sicked = editText2.getText().toString();
                String pmobile = editText3.getText().toString();
                String smobile = editText4.getText().toString();
                String tripname = editText5.getText().toString();
                String childOnTrip = autoCompleteTextView1.getText().toString();
                String firstaid = autoCompleteTextView2.getText().toString();
                String roadaids = autoCompleteTextView3.getText().toString();




                //step3
                myRef.child("TripDate").setValue(txdate);
                myRef.child("TripDestination").setValue(tripdest);
                myRef.child("TripName").setValue(tripname);
                myRef.child("SickPeople").setValue(act);
                myRef.child("TripKilometer").setValue(kmed);
                myRef.child("NumberOfPersons").setValue(nped);
                myRef.child("childOnTrip").setValue(childOnTrip);
                myRef.child("DiseaseType").setValue(sicked);
                myRef.child("PrimaryPhone").setValue(pmobile);
                myRef.child("SecondaryPhone").setValue(smobile);
                myRef.child("FirstAidKit").setValue(firstaid);
                myRef.child("RoadAids").setValue(roadaids);




                Toast.makeText(getApplicationContext(),"Trip data saved",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(newtrip.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

}