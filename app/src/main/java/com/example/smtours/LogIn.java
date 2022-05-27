package com.example.smtours;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.atomic.AtomicInteger;

public class LogIn extends AppCompatActivity {

    private int state = 0;

    private MaterialButton takeATour, signInButton, signUpButton, forgotPasswordButton;
    private TextInputEditText useremailInput, userpasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        useremailInput = findViewById(R.id.input_useremail);
        userpasswordInput = findViewById(R.id.input_password);



        // TAKE A TOUR BUTTON starts here
        takeATour = findViewById(R.id.button_takeatour);

        takeATour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = 1; // TAKE A TOUR button clicked
                Intent intent = new Intent(LogIn.this, TourType.class);
                intent.putExtra("STATE", state);
                startActivity(intent);
            }
        });
        // TAKE A TOUR BUTTON ends here



        // SIGN IN BUTTON starts here
        signInButton = findViewById(R.id.button_signin);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase DB = FirebaseDatabase.getInstance();
                DatabaseReference dRef = DB.getReference().child("users");
                Query checkUsername = dRef.orderByChild("useremail").equalTo(useremailInput.getText().toString());
                Query checkPassword = dRef.orderByChild("usernewpassword").equalTo(userpasswordInput.getText().toString());

                checkUsername.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists())
                        {
                            if((snapshot.child("usernewpassword").getValue().toString()).equals(userpasswordInput.getText().toString()))
                            {
                                state = 2; // SIGN IN button clicked
                                Intent intent = new Intent(LogIn.this, TourType.class);
                                intent.putExtra("STATE", state);
                                intent.putExtra("USERFULLNAME", snapshot.child("userfullname").getValue().toString());
                                intent.putExtra("USERMAIL", snapshot.child("useremail").getValue().toString());
                                startActivity(intent);
                                finish();
                            }
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

                checkUsername.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.exists())
                        {
                            Toast.makeText(LogIn.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                checkPassword.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.exists())
                        {
                            Toast.makeText(LogIn.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        // SIGN IN BUTTON ends here



        // SIGN UP BUTTON starts here
        signUpButton = findViewById(R.id.button_signup);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, SignUp.class);
                startActivity(intent);
            }
        });
        // SIGN UP BUTTON ends here


        // FORGOT PASSWORD BUTTON starts here
        forgotPasswordButton = findViewById(R.id.button_forgot_password);

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase DB = FirebaseDatabase.getInstance();
                DatabaseReference dRef = DB.getReference().child("users");
                Query checkUsername = dRef.orderByChild("useremail").equalTo(useremailInput.getText().toString());

                checkUsername.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if(snapshot.exists()) {
                            Intent intent = new Intent(LogIn.this, ResetPassword.class);
                            intent.putExtra("USERMAIL", snapshot.child("useremail").getValue().toString());
                            startActivity(intent);
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

                checkUsername.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!snapshot.exists())
                        {
                            Toast.makeText(LogIn.this, "Write correct email and click again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        // FORGOT PASSWORD BUTTON ends here

    }
}