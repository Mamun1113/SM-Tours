package com.example.smtours;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class TourDescription extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private  String costPerP, tourdate;

    private DrawerLayout dl;
    private Toolbar tb;
    private NavigationView nv;
    private Dialog myDialog;

    private MaterialButton back, home, tour;

    private MaterialTextView placeName;
    private MaterialButton confirmClicked;
    private TextInputEditText nextTour,  distance, transport, costPerPerson, duration, food;
    private TextView popupDescriptionText;
    private ShapeableImageView placeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_description);
        setTitle("Place Description");


        // navigation menu start
        dl = findViewById(R.id.drawl);
        tb = findViewById(R.id.toolbar);
        nv = findViewById(R.id.navigation_view);

        setSupportActionBar(tb);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, tb, R.string.open, R.string.close);
        dl.addDrawerListener(toggle);
        toggle.syncState();
        nv.setNavigationItemSelectedListener(this);

        myDialog = new Dialog(this);
        // navigation menu end


        placeName = findViewById(R.id.placeNameInDescription);
        placeName.setText(getIntent().getStringExtra("PLACENAME"));

        nextTour = findViewById(R.id.nextTourInDescription);
        distance = findViewById(R.id.distanceInDescription);
        transport = findViewById(R.id.transportInDescription);
        costPerPerson = findViewById(R.id.costperpersonInDescription);
        duration = findViewById(R.id.durationInDescription);
        food = findViewById(R.id.foodInDescription);

        FirebaseDatabase DB = FirebaseDatabase.getInstance();
        DatabaseReference dRef = DB.getReference().child("places").child("all");
        Query checkPlace = dRef.orderByChild("placename").equalTo(getIntent().getStringExtra("PLACENAME"));

        checkPlace.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.exists()) {
                    costPerP = snapshot.child("costperperson").getValue().toString();
                    tourdate = snapshot.child("nexttour").getValue().toString();

                    nextTour.setText(snapshot.child("nexttour").getValue().toString());
                    distance.setText(snapshot.child("distance").getValue().toString());
                    transport.setText(snapshot.child("transport").getValue().toString());
                    costPerPerson.setText(snapshot.child("costperperson").getValue().toString() + " BDT");
                    duration.setText( snapshot.child("duration").getValue().toString());
                    food.setText(snapshot.child("food").getValue().toString());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        // CONFIRM button starts here
        confirmClicked = findViewById(R.id.button_confirmInDescription);
        confirmClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TourDescription.this, TourSummary.class);
                intent.putExtra("PLACENAME", String.valueOf(placeName));
                intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                intent.putExtra("PLACENAME", getIntent().getStringExtra("PLACENAME"));
                intent.putExtra("COSTPERPERSON", costPerP);
                intent.putExtra("TOURDATE", tourdate);
                startActivity(intent);
            }
        });
        // CONFIRM button ends here


        //BOTTOM BAR starts here
        back = findViewById(R.id.backButtonInAll);
        home = findViewById(R.id.homeButtonInAll);
        tour = findViewById(R.id.tourButtonInAll);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TourDescription.this, TourType.class);
                intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                startActivity(intent);
            }
        });

        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TourDescription.this, MyTours.class);
                intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                startActivity(intent);
            }
        });
        //BOTTOM BAR ends here

    }


    //NAVIGATION BAR starts here
    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(GravityCompat.START)) {
            dl.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_tours:
                Intent intent = new Intent(TourDescription.this, MyTours.class);
                intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                startActivity(intent);
                break;

            case R.id.my_profile:
                Intent intent2 = new Intent(TourDescription.this, TouristProfile.class);
                intent2.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                startActivity(intent2);
                break;

            case R.id.terms_conditions:
                myDialog.setContentView(R.layout.popup_terms);
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
                myDialog.show();
                break;

            case R.id.about_page:
                myDialog.setContentView(R.layout.popup_about);
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
                myDialog.show();
                break;

            case R.id.sign_out:
                Intent intent3 = new Intent(TourDescription.this, LogIn.class);
                startActivity(intent3);
                finish();
                break;

            default:
                break;
        }
        return false;
    }
    //NAVIGATION BAR ends here

}