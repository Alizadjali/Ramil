package com.example.ramiladmin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ramiladmin.models.sosRecords;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class sosCasesRecordsAdabter extends RecyclerView.Adapter<sosCasesRecordsAdabter.RecordViewHolder> {
    private List<sosRecords> recordList;
    private Context context;

    public sosCasesRecordsAdabter(List<sosRecords> recordList, Context context) {
        this.recordList = recordList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sos_item_record, parent, false);
        return new RecordViewHolder(itemView, context);


    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        sosRecords record = recordList.get(position);

//        holder.longitudeTextView.setText(String.valueOf(record.getLongitude()));
//        holder.latitudeTextView.setText(String.valueOf(record.getLatitude()));
//        holder.tripIdTextView.setText(record.getTripId());
        holder.usernameTextView.setText(record.getEmail());
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public class RecordViewHolder extends RecyclerView.ViewHolder {

        TextView usernameTextView;
        Button locateButton, closeButton;
        private Context context;


        public RecordViewHolder(View itemView, Context context) {

            super(itemView);

            this.context = context;

            usernameTextView = itemView.findViewById(R.id.usernameTextView);


            locateButton = itemView.findViewById(R.id.locatebutton);
            closeButton = itemView.findViewById(R.id.closebutton);

            // open location (when user press locate button)
            locateButton.setOnClickListener(v ->{
                int position = getAdapterPosition();
                sosRecords selectedRecord = recordList.get(position);
                openMap(selectedRecord.getLatitude(), selectedRecord.getLongitude());
            });

            // To open previous trips details
            usernameTextView.setOnClickListener(v ->{
                int position = getAdapterPosition();
                sosRecords selectedRecord = recordList.get(position);
                        // to send userid
                        String userID = selectedRecord.getUserId();

                    }
            );


            // Closing Cases
            closeButton.setOnClickListener(v ->{
                DatabaseReference databaseReference;
                int position = getAdapterPosition();
                sosRecords selectedRecord = recordList.get(position);
//                UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(selectedRecord.getEmail());

                databaseReference = FirebaseDatabase.getInstance().getReference("soscases").child(selectedRecord.getUserId()).child("isActive");
                databaseReference.setValue(false)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                recordList.remove(position);
                                Home.sosrecordAdapterobj.notifyDataSetChanged();
                                Toast.makeText(context, "The case has been successfully closed.", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Error occurred: Can't Close the case ", Toast.LENGTH_SHORT).show();

                            }
                        });
//



        });

        }
    }

    private void openMap(double latitude, double longitude){
        String mapUrl = String.format("https://www.google.com/maps/search/?api=1&query=%f,%f", latitude, longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse(mapUrl));
        context.startActivity(intent);
    }



}
