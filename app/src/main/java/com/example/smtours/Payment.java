package com.example.smtours;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import papaya.in.sendmail.SendMail;

public class Payment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    Tours tours;
    private DrawerLayout dl;
    private Toolbar tb;
    private NavigationView nv;
    private Dialog myDialog;

    private String shortmail;

    private MaterialButton back, home, tour;

    private ShapeableImageView paymentIcon;
    private TabLayout paymentTab;
    private TextInputLayout agentnumber, totalAmount, transactionID;
    private TextInputEditText merchantText, totalToPay, txnID, merchantNumber;
    private MaterialButton getTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        setTitle("Payment");


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


        paymentIcon = findViewById(R.id.payment_icon);
        merchantText = findViewById(R.id.merchantText);
        merchantNumber = findViewById(R.id.merchant_number);
        agentnumber = findViewById(R.id.text_agent_number);
        totalAmount = findViewById(R.id.text_total_amount_to_pay);
        transactionID = findViewById(R.id.text_transaction_id);


        FirebaseDatabase DB = FirebaseDatabase.getInstance();
        DatabaseReference dRef = DB.getReference().child("payment");

        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                merchantNumber.setText(snapshot.child("bkash").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //PAYMENT TAB starts here
        paymentTab = findViewById(R.id.tab_payment);
        paymentTab.getTabAt(0).select();
        paymentTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(paymentTab.getSelectedTabPosition() == 0)
                {
                    merchantText.setText("BKASH merchant number");
                    paymentIcon.setImageResource(R.drawable.bkash_logo);
                    agentnumber.setBoxBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.bkash, null));
                    totalAmount.setBoxBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.bkash, null));
                    transactionID.setBoxBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.bkash, null));
                }
                else if(paymentTab.getSelectedTabPosition() == 1)
                {
                    merchantText.setText("NAGAD merchant number");
                    paymentIcon.setImageResource(R.drawable.nagad_logo);
                    agentnumber.setBoxBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.nogod, null));
                    totalAmount.setBoxBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.nogod, null));
                    transactionID.setBoxBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.nogod, null));

                }
                else if(paymentTab.getSelectedTabPosition() == 2)
                {
                    merchantText.setText("ROCKET merchant number");
                    paymentIcon.setImageResource(R.drawable.rocket_logo);
                    agentnumber.setBoxBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rocket, null));
                    totalAmount.setBoxBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rocket, null));
                    transactionID.setBoxBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.rocket, null));
                }

                //MERCHANT NUMBER change starts here
                dRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(paymentTab.getSelectedTabPosition() == 0)
                        {
                            merchantNumber.setText(snapshot.child("bkash").getValue().toString());
                        }
                        if(paymentTab.getSelectedTabPosition() == 1)
                        {
                            merchantNumber.setText(snapshot.child("nagad").getValue().toString());
                        }
                        if(paymentTab.getSelectedTabPosition() == 2)
                        {
                            merchantNumber.setText(snapshot.child("rocket").getValue().toString());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                //MERCHANT NUMBER change ends here

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //PAYMENT TAB ends here


        totalToPay = findViewById(R.id.totalToPayInPayment);
        totalToPay.setText(getIntent().getStringExtra("TOTALPAY"));

        txnID = findViewById(R.id.txnInPayment);

        shortmail = (getIntent().getStringExtra("USERMAIL")).substring(0, (getIntent().getStringExtra("USERMAIL").length())-4);

        //GET TICKET button starts here
        getTicket = findViewById(R.id.button_get_ticket);
        getTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txnID.getText().toString().equals("")) {
                    Toast.makeText(Payment.this, "Please enter Transaction Number", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Payment.this, "Tour confirmation successful", Toast.LENGTH_SHORT).show();

                    SendMail mail = new SendMail("smtoursfall20@gmail.com", "smfall20",
                            "smtoursfall20@gmail.com",
                            "Tour Confirmation",
                            "Tourist Mail: " + getIntent().getStringExtra("USERMAIL") +
                                    "\nTour Place: " + getIntent().getStringExtra("PLACENAME") +
                                    "\nTour Date: " + getIntent().getStringExtra("TOURDATE") +
                                    "\nTotal Person: " + getIntent().getStringExtra("TOTALPERSON"));
                    mail.execute();

                    FirebaseDatabase DB = FirebaseDatabase.getInstance();
                    DatabaseReference dRef = DB.getReference().child("tours").child(shortmail);

                    String key = dRef.push().getKey();

                    tours = new Tours(getIntent().getStringExtra("USERMAIL"),
                            getIntent().getStringExtra("PLACENAME"),
                            getIntent().getStringExtra("TOURDATE"),
                            getIntent().getStringExtra("TOTALPERSON"),
                            getIntent().getStringExtra("TOTALPAY"),
                            getIntent().getStringExtra("BABYFOOD"),
                            txnID.getText().toString(),
                            key,
                            "Will be updated soon",
                            "Will be updated soon",
                            "Will be updated soon");

                    dRef.child(key).setValue(tours);

                    DatabaseReference dRef2 = DB.getReference().child("places").child("all");
                    Query visitorupdate = dRef2.orderByChild("placename").equalTo(getIntent().getStringExtra("PLACENAME"));

                    visitorupdate.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            if(snapshot.exists()) {
                                int visitor = Integer.parseInt(snapshot.child("totalvisited").getValue().toString());

                                visitor += Integer.parseInt(getIntent().getStringExtra("TOTALPERSON"));
                                //visitor++;

                                int finalVisitor = visitor;
                                visitorupdate.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        if(task.isSuccessful())
                                        {
                                            for(DataSnapshot parentkeySnapshot : task.getResult().getChildren()) {
                                                String parentKey = parentkeySnapshot.getKey();

                                                dRef2.child(parentKey).child("totalvisited").setValue(String.valueOf(finalVisitor));
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

                    Intent intent = new Intent(Payment.this, MyTours.class);
                    intent.putExtra("PLACENAME", getIntent().getStringExtra("PLACENAME"));
                    intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                    startActivity(intent);
                    finish();
                }
            }
        });
        //GET TICKET button ends here


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
                Intent intent = new Intent(Payment.this, TourType.class);
                intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                startActivity(intent);
            }
        });

        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment.this, MyTours.class);
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
                Intent intent = new Intent(Payment.this, MyTours.class);
                intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                startActivity(intent);
                break;

            case R.id.my_profile:
                Intent intent2 = new Intent(Payment.this, TouristProfile.class);
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
                Intent intent3 = new Intent(Payment.this, LogIn.class);
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