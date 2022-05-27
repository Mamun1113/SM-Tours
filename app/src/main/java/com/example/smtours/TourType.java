package com.example.smtours;

import androidx.annotation.NonNull;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class TourType extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    private Dialog myDialog;
    private DrawerLayout dl;
    private Toolbar tb;
    private NavigationView nv;

    private MaterialButton back, home, tour;

    private EditText searchBar;
    private MaterialButton searchButton;
    private TextInputLayout categorySelect;
    private AutoCompleteTextView dropdowncategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_type);
        setTitle("Tour Places");


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


        FirebaseDatabase DB = FirebaseDatabase.getInstance();
        DatabaseReference checkPlace = DB.getReference().child("places").child("all");


        //SEARCH BUTTON starts here
        searchBar = findViewById(R.id.searchBar);
        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query checkSearch = checkPlace.orderByChild("placename").equalTo(searchBar.getText().toString());

                checkSearch.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.exists()) {

                            FirebaseRecyclerOptions<PlaceList> options =
                                    new FirebaseRecyclerOptions.Builder<PlaceList>()
                                            .setQuery(checkSearch, PlaceList.class)
                                            .build();

                            recyclerViewAdapter = new RecyclerViewAdapter(options);
                            recyclerView.setAdapter(recyclerViewAdapter);

                            recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(String placename) {
                                    int state = getIntent().getIntExtra("STATE", 0);
                                    if (state == 1) {   // not signed in
                                        ShowPopUp();
                                    } else {            // signed in
                                        Intent intent = new Intent(TourType.this, TourDescription.class);
                                        intent.putExtra("PLACENAME", placename);
                                        intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                                        startActivity(intent);
                                    }
                                }
                            });
                            recyclerViewAdapter.startListening();
                        }
                        else {
                            Toast.makeText(TourType.this, "Place not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        //SEARCH BUTTON ends here


        recyclerView = findViewById(R.id.recyclerViewID);
        recyclerView.setLayoutManager(new LinearLayoutManager(TourType.this));

        FirebaseRecyclerOptions<PlaceList> options =
                new FirebaseRecyclerOptions.Builder<PlaceList>()
                        .setQuery(checkPlace, PlaceList.class)
                        .build();

        recyclerViewAdapter = new RecyclerViewAdapter(options);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String placename) {
                int state = getIntent().getIntExtra("STATE", 0);
                if (state == 1) {   // not signed in
                    ShowPopUp();
                } else {            // signed in
                    Intent intent = new Intent(TourType.this, TourDescription.class);
                    intent.putExtra("PLACENAME", placename);
                    intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                    startActivity(intent);
                }
            }
        });
        recyclerViewAdapter.startListening();


        //CATEGORY dropdown starts here
        categorySelect = findViewById(R.id.input_category);
        dropdowncategory = findViewById(R.id.dropdown_category);

        String[] categoryOptions = new String[]{"All", "Waterside", "Desert", "Forests", "City"};

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                TourType.this,
                R.layout.category_option,
                categoryOptions
        );
        dropdowncategory.setAdapter(categoryAdapter);

        dropdowncategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchBar.setText(null);

                Query checkPlaceWithCategory = null;

                if (position == 0) {
                    checkPlaceWithCategory = checkPlace;
                } else if (position == 1) {
                    checkPlaceWithCategory = checkPlace.orderByChild("part").equalTo("waterside");
                } else if (position == 2) {
                    checkPlaceWithCategory = checkPlace.orderByChild("part").equalTo("deserts");
                } else if (position == 3) {
                    checkPlaceWithCategory = checkPlace.orderByChild("part").equalTo("forests");
                } else if (position == 4) {
                    checkPlaceWithCategory = checkPlace.orderByChild("part").equalTo("city");
                }


                FirebaseRecyclerOptions<PlaceList> options =
                        new FirebaseRecyclerOptions.Builder<PlaceList>()
                                .setQuery(checkPlaceWithCategory, PlaceList.class)
                                .build();

                recyclerViewAdapter = new RecyclerViewAdapter(options);
                recyclerView.setAdapter(recyclerViewAdapter);

                recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String placename) {
                        int state = getIntent().getIntExtra("STATE", 0);
                        if (state == 1) {   // not signed in
                            ShowPopUp();
                        } else {            // signed in
                            Intent intent = new Intent(TourType.this, TourDescription.class);
                            intent.putExtra("PLACENAME", placename);
                            intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                            startActivity(intent);
                        }
                    }
                });
                recyclerViewAdapter.startListening();
            }
        });

        //CATEGORY dropdown ends here


        //BOTTOM BAR starts here
        back = findViewById(R.id.backButtonInAll);
        home = findViewById(R.id.homeButtonInAll);
        tour = findViewById(R.id.tourButtonInAll);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int state = getIntent().getIntExtra("STATE", 0);

                if(state == 1) {
                    Intent intent = new Intent(TourType.this, LogIn.class);
                    startActivity(intent);
                }
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int state = getIntent().getIntExtra("STATE", 0);

                if(state == 1) {
                    Intent intent = new Intent(TourType.this, LogIn.class);
                    startActivity(intent);
                }
            }
        });

        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int state = getIntent().getIntExtra("STATE", 0);

                if(state == 1) {
                    ShowPopUp();
                }
                else {
                    Intent intent = new Intent(TourType.this, MyTours.class);
                    intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                    startActivity(intent);
                }
            }
        });
        //BOTTOM BAR ends here

    }

    @Override
    protected void onStart() {
        super.onStart();
        //recyclerViewAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //recyclerViewAdapter.stopListening();
    }


    private void ShowPopUp() {
        MaterialButton signinButton, cancelButton;
        myDialog.setContentView(R.layout.popup_tourtype);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable());

        signinButton = myDialog.findViewById(R.id.signInButtonInPopUp);
        cancelButton = myDialog.findViewById(R.id.cancelButtonInPopUp);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TourType.this, LogIn.class);
                startActivity(intent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.show();
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

        int state = getIntent().getIntExtra("STATE", 0);

        if(state == 1) {
            ShowPopUp();
        }
        else  {
            switch (item.getItemId()) {
                case R.id.my_tours:
                    Intent intent = new Intent(TourType.this, MyTours.class);
                    intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                    startActivity(intent);
                    break;

                case R.id.my_profile:
                    Intent intent2 = new Intent(TourType.this, TouristProfile.class);
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
                    Intent intent3 = new Intent(TourType.this, LogIn.class);
                    startActivity(intent3);
                    finish();
                    break;

                default:
                    break;
            }
        }


        return false;
    }
    //NAVIGATION BAR ends here

}