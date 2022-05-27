package com.example.smtours;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class RecyclerViewAdapter extends FirebaseRecyclerAdapter<PlaceList, RecyclerViewAdapter.MyViewHolder> {

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(String placename);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public RecyclerViewAdapter(@NonNull FirebaseRecyclerOptions<PlaceList> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull PlaceList model) {
        Glide.with(holder.imageurl.getContext()).load(model.getImageurl()).into(holder.imageurl);
        holder.placename.setText(model.getPlacename());
        holder.placeshortdesc.setText(model.getLocation());
        holder.nexttour.setText("Next tour: " + model.getNexttour());
        holder.totalvisited.setText("Total Visited: " + model.getTotalvisited());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_row_item, parent, false);
        return new MyViewHolder(view, onItemClickListener);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageurl;
        TextView placename, placeshortdesc, nexttour, totalvisited;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            imageurl = itemView.findViewById(R.id.placeImageViewID);
            placename = itemView.findViewById(R.id.placeNameViewID);
            placeshortdesc = itemView.findViewById(R.id.placeShortDescriptionViewID);
            nexttour = itemView.findViewById(R.id.nextTourViewID);
            totalvisited = itemView.findViewById(R.id.totalVisitedViewID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(listener != null) {
                        int position = getAbsoluteAdapterPosition();

                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(placename.getText().toString());
                        }
                    }

                }
            });
        }
    }
}