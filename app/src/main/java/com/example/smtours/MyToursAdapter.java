package com.example.smtours;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyToursAdapter extends FirebaseRecyclerAdapter<TourList, MyToursAdapter.TourViewHolder>{

    String shortmail, parentkey;
    private OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(String parentkey);
    }

    public void setOnDeleteClickListener(MyToursAdapter.OnDeleteClickListener listener) {
        onDeleteClickListener = listener;
    }

    public MyToursAdapter(@NonNull FirebaseRecyclerOptions<TourList> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TourViewHolder holder, int position, @NonNull TourList model) {

        shortmail = (model.getUseremail()).substring(0, ((model.getUseremail()).length())-4);
        parentkey = model.getParentkey();

        holder.tourplace.setText("Place: " + model.getTourplace());
        holder.tourdate.setText("Date: " + model.getTourdate());
        holder.totalperson.setText("Person: " + model.getTotalperson());
        holder.startlocation.setText("Start location: " + model.getStartlocation());
        holder.transport.setText("Transport: " + model.getTransport());
        holder.starttime.setText("Start time: " + model.getStarttime());
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_row_item, parent, false);
        return new TourViewHolder(view, onDeleteClickListener);
    }


    class TourViewHolder extends RecyclerView.ViewHolder {

        TextView tourplace, tourdate, totalperson, startlocation, transport, starttime;
        Button cancelButton;

       // shortmail; //= TourList.getUsermail().substring(0, (TourList.getUsermail().length())-4);

         public TourViewHolder(@NonNull View itemView, OnDeleteClickListener listener) {
             super(itemView);
             tourplace = itemView.findViewById(R.id.placeNameInTour);
             tourdate = itemView.findViewById(R.id.dateInTour);
             totalperson = itemView.findViewById(R.id.personInTour);
             startlocation = itemView.findViewById(R.id.startLocationInTour);
             transport = itemView.findViewById(R.id.transportInTour);
             starttime = itemView.findViewById(R.id.startTimeInTour);
             cancelButton = itemView.findViewById(R.id.cancelButtonintours);

             cancelButton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     listener.onDeleteClick(parentkey);

                     }
             });
         }
    }
}
