package com.example.smtours;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import papaya.in.sendmail.SendMail;


public class MyTours extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    MyToursAdapter myToursAdapter;

    private DrawerLayout dl;
    private Toolbar tb;
    private NavigationView nv;
    private Dialog myDialog;

    private String shortmail;

    private MaterialButton back, home, tour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tours);
        setTitle("My Tours");


        // navigation menu starts here
        dl = findViewById(R.id.drawl);
        tb = findViewById(R.id.toolbar);
        nv = findViewById(R.id.navigation_view);

        setSupportActionBar(tb);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, tb, R.string.open, R.string.close);
        dl.addDrawerListener(toggle);
        toggle.syncState();
        nv.setNavigationItemSelectedListener(this);

        myDialog = new Dialog(this);
        // navigation menu ends here


        //MY TOURS LIST SHOW starts here
        shortmail = (getIntent().getStringExtra("USERMAIL")).substring(0, (getIntent().getStringExtra("USERMAIL").length())-4);

        recyclerView = findViewById(R.id.tourListID);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<TourList> options =
                new FirebaseRecyclerOptions.Builder<TourList>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("tours").child(shortmail), TourList.class)
                        .build();

        myToursAdapter = new MyToursAdapter(options);
        recyclerView.setAdapter(myToursAdapter);
        //MY TOURS LIST SHOW ends here


        //DELETE BUTTON starts here
        myToursAdapter.setOnDeleteClickListener(new MyToursAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(String parentkey) {
                FirebaseDatabase DB = FirebaseDatabase.getInstance();
                DatabaseReference dRef = DB.getReference().child("tours").child(shortmail).child(parentkey);
                Query tourcheck = dRef;

                tourcheck.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            Toast.makeText(MyTours.this, "Tour canceled", Toast.LENGTH_SHORT).show();

                            SendMail mail = new SendMail("smtoursfall20@gmail.com", "smfall20",
                                    "smtoursfall20@gmail.com",
                                    "Tour Cancellation",
                                    "The following tourist canceled the below mentioned tour." +
                                            "\nTourist Mail: " + getIntent().getStringExtra("USERMAIL") +
                                            "\nTour Place: " + snapshot.child("tourplace").getValue().toString() +
                                            "\nTour Date: " + snapshot.child("tourdate").getValue().toString() +
                                            "\nTotal Person: " + snapshot.child("totalperson").getValue().toString());
                            mail.execute();

                            dRef.removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        //DELETE BUTTON ends here


        //BOTTOM BAR starts here
        back = findViewById(R.id.backButtonInAll);
        home = findViewById(R.id.homeButtonInAll);
        tour = findViewById(R.id.tourButtonInAll);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyTours.this, TourType.class);
                intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyTours.this, TourType.class);
                intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                startActivity(intent);
            }
        });

        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyTours.this, MyTours.class);
                intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                startActivity(intent);
            }
        });
        //BOTTOM BAR ends here

    }

    @Override
    protected void onStart() {
        super.onStart();
        myToursAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myToursAdapter.stopListening();
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
                Intent intent = new Intent(MyTours.this, MyTours.class);
                intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                startActivity(intent);
                break;

            case R.id.my_profile:
                Intent intent2 = new Intent(MyTours.this, TouristProfile.class);
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
                Intent intent3 = new Intent(MyTours.this, LogIn.class);
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