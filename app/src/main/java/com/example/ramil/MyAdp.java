package com.example.ramil;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdp extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<retDataClass> dataClassList;

    public MyAdp(Context context, List<retDataClass> dataClassList) {
        this.context = context;
        this.dataClassList = dataClassList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

     holder.recdes.setText(dataClassList.get(position).getTripDestination());
     holder.recdate.setText(dataClassList.get(position).getTripDate());


     holder.cardOFrec.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(context, DetailTrip.class);
             intent.putExtra("destination",dataClassList.get(holder.getAdapterPosition()).getTripDestination());
             intent.putExtra("date",dataClassList.get(holder.getAdapterPosition()).getTripDate());
             intent.putExtra("tripName",dataClassList.get(holder.getAdapterPosition()).getTripName());
             intent.putExtra("tripKm",dataClassList.get(holder.getAdapterPosition()).getTripKilometer());
             intent.putExtra("personNumber",dataClassList.get(holder.getAdapterPosition()).getNumberOfPersons());
             intent.putExtra("ChildOnTrip",dataClassList.get(holder.getAdapterPosition()).getChildOnTrip());
             intent.putExtra("medicalCondition",dataClassList.get(holder.getAdapterPosition()).getSickPeople());
             intent.putExtra("RoadAids",dataClassList.get(holder.getAdapterPosition()).getRoadAids());
             intent.putExtra("FirsAid",dataClassList.get(holder.getAdapterPosition()).getFirstAidKit());
             intent.putExtra("DiseaseType",dataClassList.get(holder.getAdapterPosition()).getDiseaseType());
             intent.putExtra("phonePrim",dataClassList.get(holder.getAdapterPosition()).getPrimaryPhone());
             intent.putExtra("phoneSec",dataClassList.get(holder.getAdapterPosition()).getSecondaryPhone());

             context.startActivity(intent);
         }
     });
    }
    @Override
    public int getItemCount() {

        return dataClassList.size();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{

    TextView recdate,recdes;
    CardView cardOFrec;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        recdes = itemView.findViewById(R.id.recdes);
        recdate = itemView.findViewById(R.id.recdate);
        cardOFrec = itemView.findViewById(R.id.cardOFrec);

    }
}