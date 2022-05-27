package com.example.smtours;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import java.util.Calendar;

public class TouristProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    int day, month, year, daycurrent, monthcurrent, yearcurrent;
    User user;
    private TextInputEditText profileFullName, profilePhone, profileGender, profileEmail, profileDateOfBirth, profilePassword;
    private MaterialButton updateButton;
    private Dialog myDialog;
    private DrawerLayout dl;
    private Toolbar tb;
    private NavigationView nv;

    private MaterialButton back, home, tour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_profile);
        setTitle("My Profile");


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



        //INITIAL DATA SHOW starts here
        profileFullName = findViewById(R.id.profile_fullname);
        profilePhone = findViewById(R.id.profile_phonenumber);
        profileGender = findViewById(R.id.profile_gender);
        profileDateOfBirth = findViewById(R.id.profile_dateofbirth);
        profileEmail = findViewById(R.id.profile_email);
        profilePassword = findViewById(R.id.profile_newpassword);

        FirebaseDatabase DB = FirebaseDatabase.getInstance();
        DatabaseReference dRef = DB.getReference().child("users");
        Query checkTourist = dRef.orderByChild("useremail").equalTo(getIntent().getStringExtra("USERMAIL"));

        checkTourist.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.exists()) {
                    profileFullName.setText(snapshot.child("userfullname").getValue().toString());
                    profilePhone.setText(snapshot.child("userphone").getValue().toString());
                    profileGender.setText(snapshot.child("usergender").getValue().toString());
                    profileDateOfBirth.setText(snapshot.child("userdateofbirth").getValue().toString());
                    profileEmail.setText(snapshot.child("useremail").getValue().toString());
                    profilePassword.setText(snapshot.child("usernewpassword").getValue().toString());
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
        //INITIAL DATA SHOW ends here


        //DATE OF BIRTH input starts here
        Calendar calendar = Calendar.getInstance();

        yearcurrent  = calendar.get(Calendar.YEAR);
        monthcurrent = calendar.get(Calendar.MONTH);
        daycurrent   = calendar.get(Calendar.DAY_OF_MONTH);

        profileDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(yearcurrent-18, monthcurrent-1, daycurrent-1);

                year  = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day   = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(TouristProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        profileDateOfBirth.setText(dayOfMonth+"/" + (month+1) + "/" + year);
                    }
                }, year, month, day);

                //calendar.set(yearcurrent-18, monthcurrent-1, daycurrent-1);
                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                datePickerDialog.show();


            }

        });
        //DATE OF BIRTH input ends here


        // UPDATE BUTTON starts here
        updateButton = findViewById(R.id.profile_updatebutton);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase DB = FirebaseDatabase.getInstance();
                DatabaseReference dRef = DB.getReference().child("users");
                Query checkUseremail = dRef.orderByChild("useremail").equalTo(profileEmail.getText().toString());

                if (profileFullName.getText().toString().equals("")) {
                    Toast.makeText(TouristProfile.this, "Please enter your full name", Toast.LENGTH_SHORT).show();
                } else if ((profilePhone.getText().toString()).length() != 11) {
                    Toast.makeText(TouristProfile.this, "Please enter 11 digit number", Toast.LENGTH_SHORT).show();
                } else if (profilePhone.getText().toString().equals("")) {
                    Toast.makeText(TouristProfile.this, "Please select a birth date", Toast.LENGTH_SHORT).show();
                } else if (profilePassword.getText().toString().equals("")) {
                    Toast.makeText(TouristProfile.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                } else {

                    checkUseremail.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if(task.isSuccessful())
                            {
                                for(DataSnapshot parentkeySnapshot : task.getResult().getChildren()) {
                                    String parentKey = parentkeySnapshot.getKey();

                                    //Toast.makeText(TouristProfile.this, parentKey, Toast.LENGTH_SHORT).show();
                                    //Firebase start
                                    user = new User(profileFullName.getText().toString(),
                                            profilePhone.getText().toString(),
                                            profileEmail.getText().toString(),
                                            profileGender.getText().toString(),
                                            profileDateOfBirth.getText().toString(),
                                            profilePassword.getText().toString(),
                                            parentKey);

                                    dRef.child(parentKey).setValue(user);
                                    //Firebase end
                                }
                            }
                        }
                    });
                }
            }
        });
        // UPDATE BUTTON ends here


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
                Intent intent = new Intent(TouristProfile.this, TourType.class);
                intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                startActivity(intent);
            }
        });

        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TouristProfile.this, MyTours.class);
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
                Intent intent = new Intent(TouristProfile.this, MyTours.class);
                intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                startActivity(intent);
                break;

            case R.id.my_profile:
                Intent intent2 = new Intent(TouristProfile.this, TouristProfile.class);
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
                Intent intent3 = new Intent(TouristProfile.this, LogIn.class);
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