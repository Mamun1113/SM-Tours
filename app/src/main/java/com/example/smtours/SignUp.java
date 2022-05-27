package com.example.smtours;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    private TextInputEditText inputFullName, inputPhone, inputEmail, inputDateOfBirth, inputPassword;
    private TextInputLayout genderSelect;
    private AutoCompleteTextView dropdownGender;
    private MaterialButton signUpButton, backbutton;

    private boolean registered;

    int day, month, year, daycurrent, monthcurrent, yearcurrent;

    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputFullName = findViewById(R.id.input_fullname);
        inputPhone = findViewById(R.id.input_phonenumber);
        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_newpassword);


        registered = false;


        //DATE OF BIRTH input starts here
        inputDateOfBirth = findViewById(R.id.input_dateofbirth);
        Calendar calendar = Calendar.getInstance();

        yearcurrent  = calendar.get(Calendar.YEAR);
        monthcurrent = calendar.get(Calendar.MONTH);
        daycurrent   = calendar.get(Calendar.DAY_OF_MONTH);

        inputDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(yearcurrent-18, monthcurrent-1, daycurrent-1);

                year  = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day   = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SignUp.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        inputDateOfBirth.setText(dayOfMonth+"/" + (month+1) + "/" + year);
                    }
                }, year, month, day);

                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });
        //DATE OF BIRTH input ends here


        //GENDER dropdown starts here
        genderSelect = findViewById(R.id.input_gender);
        dropdownGender = findViewById(R.id.dropdown_gender);

        String[] genderOptions = new String[]{"Male", "Female", "Other"};

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(
                SignUp.this,
                R.layout.gender_option,
                genderOptions
        );
        dropdownGender.setAdapter(genderAdapter);
        //GENDER dropdown ends here


        // SIGN UP BUTTON starts here
        signUpButton = findViewById(R.id.button_newsignup);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase DB = FirebaseDatabase.getInstance();
                DatabaseReference dRef = DB.getReference().child("users");
                Query checkUseremail = dRef.orderByChild("useremail").equalTo(inputEmail.getText().toString());

                String mail = inputEmail.getText().toString();

                if(inputFullName.getText().toString().equals("")) {
                    inputFullName.setError("Please enter your full name");
                    inputFullName.requestFocus();
                } else if ((inputPhone.getText().toString()).length() != 11) {
                    inputPhone.setError("Please enter 11 digit phone number");
                    inputPhone.requestFocus();
                } else if (dropdownGender.getText().toString().equals("")) {
                    Toast.makeText(SignUp.this, "Please select a gender", Toast.LENGTH_SHORT).show();
                    dropdownGender.requestFocus();
                } else if (inputDateOfBirth.getText().toString().equals("")) {
                    Toast.makeText(SignUp.this, "Please select a birth date", Toast.LENGTH_SHORT).show();
                    inputDateOfBirth.requestFocus();
                } else if (mail.length() < 10 || (!mail.endsWith("@gmail.com") && !mail.endsWith("@aust.edu") && !mail.endsWith("@yahoo.com"))) {
                    inputEmail.setError("Please enter a valid email address");
                    inputEmail.requestFocus();
                } else if (inputPassword.getText().toString().equals("")) {
                    inputPassword.setError("Please enter a password");
                    inputPassword.requestFocus();
                } else {
                    checkUseremail.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()) {
                                if(registered == false) {
                                    Toast.makeText(SignUp.this, "Email already exists!!! Try another", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                registered = true;

                                String key = dRef.push().getKey();

                                //Firebase start
                                user = new User(inputFullName.getText().toString(),
                                        inputPhone.getText().toString(),
                                        inputEmail.getText().toString(),
                                        dropdownGender.getText().toString(),
                                        inputDateOfBirth.getText().toString(),
                                        inputPassword.getText().toString(),
                                        key);

                                dRef.child(key).setValue(user);
                                //Firebase end

                                Toast.makeText(SignUp.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUp.this, LogIn.class);
                                startActivity(intent);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        // SIGN UP BUTTON ends here


        // BACK BUTTON starts here
        backbutton = findViewById(R.id.backbuttoninsignup);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, LogIn.class);
                startActivity(intent);
            }
        });
        // BACK BUTTON ends here
    }
}