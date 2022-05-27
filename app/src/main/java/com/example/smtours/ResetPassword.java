package com.example.smtours;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Random;

//My emulator's sim number +1-555-521-5554
public class ResetPassword extends AppCompatActivity {

    private MaterialButton confirmOTP, resetPassword, backButton;
    private TextInputEditText inputOTP, inputNewPassword;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

        inputOTP = findViewById(R.id.inputOTPInReset);
        confirmOTP = findViewById(R.id.confirmOtpButtonInReset);
        inputNewPassword = findViewById(R.id.inputNewpasswordInReset);
        resetPassword = findViewById(R.id.resetButtonInReset);

        inputOTP.setEnabled(true);
        confirmOTP.setEnabled(true);
        inputNewPassword.setEnabled(false);
        resetPassword.setEnabled(false);



        Random random = new Random();

        int randomOTP = random.nextInt(999999-100000) + 100000;

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+1-555-521-5554", null, "Your OTP code for password reset: " + randomOTP, null, null);


        //CONFIRM OTP BUTTON starts here
        confirmOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(inputOTP.getText().toString()) == randomOTP) {
                    inputOTP.setEnabled(false);
                    confirmOTP.setEnabled(false);
                    inputNewPassword.setEnabled(true);
                    resetPassword.setEnabled(true);
                    Toast.makeText(ResetPassword.this, "OTP confirmed", Toast.LENGTH_SHORT).show();
                }
                else {
                    inputOTP.setError("Incorrect OTP");
                    inputOTP.requestFocus();
                }
            }
        });
        //CONFIRM OTP BUTTON ends here


        //RESET PASSWORD BUTTON starts here
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputNewPassword.getText().toString().equals("")) {
                    inputNewPassword.setError("Please enter a password");
                    inputNewPassword.requestFocus();
                }
                else {
                    FirebaseDatabase DB = FirebaseDatabase.getInstance();
                    DatabaseReference dRef = DB.getReference().child("users");
                    Query checkUseremail = dRef.orderByChild("useremail").equalTo(getIntent().getStringExtra("USERMAIL"));

                    checkUseremail.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            if(snapshot.exists()) {

                                checkUseremail.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        if(task.isSuccessful())
                                        {
                                            for(DataSnapshot parentkeySnapshot : task.getResult().getChildren()) {
                                                String parentKey = parentkeySnapshot.getKey();

                                                dRef.child(parentKey).child("usernewpassword").setValue(String.valueOf(inputNewPassword.getText().toString()));
                                                Toast.makeText(ResetPassword.this, "Password reset successful", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(ResetPassword.this, LogIn.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    }
                                });
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
                }
            }
        });
        //RESET PASSWORD BUTTON ends here


        //BACK BUTTON starts here
        backButton = findViewById(R.id.backbuttoninReset);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPassword.this, LogIn.class);
                startActivity(intent);
                finish();
            }
        });
        //BACK BUTTON ends here

    }
}