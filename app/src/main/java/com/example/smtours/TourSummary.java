package com.example.smtours;

import androidx.annotation.NonNull;
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
import android.widget.CheckBox;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.PrimitiveIterator;

public class TourSummary extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    int day, month, year;

    private DrawerLayout dl;
    private Toolbar tb;
    private NavigationView nv;
    private Dialog myDialog;

    private MaterialButton back, home, tour;

    private String babyFoodAdded;

    private CheckBox babyfood;
    private MaterialButton proceedToPayment, plusPerson, minusPerson;
    private TextInputEditText dateOfTour, totalPerson, totalAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_summary);
        setTitle("Confirmation");


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


        babyFoodAdded = "false";


        //DATE OF TOUR starts here
        dateOfTour = findViewById(R.id.tourDateInConfirmation);
        dateOfTour.setText(getIntent().getStringExtra("TOURDATE"));
        //DATE OF TOUR ends here


        totalPerson = findViewById(R.id.totalPersonInConfirmation);
        totalAmount = findViewById(R.id.totalAmountInConfirmation);
        totalAmount.setText(String.valueOf(1*Integer.parseInt(getIntent().getStringExtra("COSTPERPERSON"))) + " BDT");


        //PLUS button starts here
        plusPerson = findViewById(R.id.button_plusInConfirmation);
        plusPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int personCount;

                if(totalPerson.getText().toString().equals("")) {
                    totalPerson.setText("1");
                }

                personCount = Integer.parseInt(totalPerson.getText().toString());

                personCount++;
                totalPerson.setText(String.valueOf(personCount));
                if(babyfood.isChecked()) {
                    babyFoodAdded = "true";
                    totalAmount.setText(((personCount * Integer.parseInt(getIntent().getStringExtra("COSTPERPERSON"))) + 3000) + " BDT");
                }
                else {
                    babyFoodAdded = "false";
                    totalAmount.setText(personCount * Integer.parseInt(getIntent().getStringExtra("COSTPERPERSON")) + " BDT");
                }
            }
        });
        //PLUS button ends here


        //MINUS button starts here
        minusPerson = findViewById(R.id.button_minusInConfirmation);
        minusPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int personCount;

                if(totalPerson.getText().toString().equals("")) {
                    totalPerson.setText("1");
                }

                personCount = Integer.parseInt(totalPerson.getText().toString());

                if(personCount == 1)
                {
                    Toast.makeText(TourSummary.this, "You've reached minimum person", Toast.LENGTH_SHORT).show();
                    if(babyfood.isChecked()) {
                        babyFoodAdded = "true";
                        totalAmount.setText(((personCount * Integer.parseInt(getIntent().getStringExtra("COSTPERPERSON"))) + 3000) + " BDT");
                    }
                    else {
                        babyFoodAdded = "false";
                        totalAmount.setText(personCount * Integer.parseInt(getIntent().getStringExtra("COSTPERPERSON")) + " BDT");
                    }
                }
                else if(personCount > 1)
                {
                    personCount--;
                    totalPerson.setText(String.valueOf(personCount));
                    if(babyfood.isChecked()) {
                        babyFoodAdded = "true";
                        totalAmount.setText(((personCount * Integer.parseInt(getIntent().getStringExtra("COSTPERPERSON"))) + 3000) + " BDT");
                    }
                    else {
                        babyFoodAdded = "false";
                        totalAmount.setText(personCount * Integer.parseInt(getIntent().getStringExtra("COSTPERPERSON")) + " BDT");
                    }
                }
            }
        });
        //MINUS button ends here


        //TOTAL PERSON starts here
        totalPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int personCount;

                if(totalPerson.getText().toString().equals("")) {
                    totalPerson.setText("1");
                }

                personCount = Integer.parseInt(totalPerson.getText().toString());

                if(personCount < 1)
                {
                    //Toast.makeText(TourSummary.this, "You've reached minimum person", Toast.LENGTH_SHORT).show();
                    totalPerson.setText("1");
                    personCount = Integer.parseInt(totalPerson.getText().toString());
                    if(babyfood.isChecked()) {
                        babyFoodAdded = "true";
                        totalAmount.setText(((personCount * Integer.parseInt(getIntent().getStringExtra("COSTPERPERSON"))) + 3000) + " BDT");
                    }
                    else {
                        babyFoodAdded = "false";
                        totalAmount.setText(personCount * Integer.parseInt(getIntent().getStringExtra("COSTPERPERSON")) + " BDT");
                    }
                }
                else if(personCount > 0)
                {
                    //totalPerson.setText(totalPerson.getText());
                    if(babyfood.isChecked()) {
                        babyFoodAdded = "true";
                        totalAmount.setText(((personCount * Integer.parseInt(getIntent().getStringExtra("COSTPERPERSON"))) + 3000) + " BDT");
                    }
                    else {
                        babyFoodAdded = "false";
                        totalAmount.setText(personCount * Integer.parseInt(getIntent().getStringExtra("COSTPERPERSON")) + " BDT");
                    }
                }
            }
        });
        //TOTAL PERSON ends here


        //BABYFOOD CHECKBOX starts here
        babyfood = findViewById(R.id.babyfoodCheckbox);

        babyfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int personCount;

                if(totalPerson.getText().toString().equals("")) {
                    totalPerson.setText("1");
                }

                personCount = Integer.parseInt(totalPerson.getText().toString());

                if(babyfood.isChecked()) {
                    babyFoodAdded = "true";
                    totalAmount.setText(((personCount * Integer.parseInt(getIntent().getStringExtra("COSTPERPERSON"))) + 3000) + " BDT");
                }
                else if(!babyfood.isChecked()) {
                    babyFoodAdded = "false";
                    totalAmount.setText(personCount * Integer.parseInt(getIntent().getStringExtra("COSTPERPERSON")) + " BDT");
                }
            }
        });
        //BABYFOOD CHECKBOX ends here


        //PROCEED TO PAYMENT button starts here
        proceedToPayment = findViewById(R.id.button_paymentInConfirmation);
        proceedToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(totalPerson.getText().toString()) > 50) {
                    Toast.makeText(TourSummary.this, "Max person capacity 50", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(TourSummary.this, Payment.class);
                    intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                    intent.putExtra("PLACENAME", getIntent().getStringExtra("PLACENAME"));
                    intent.putExtra("TOURDATE", dateOfTour.getText().toString());
                    intent.putExtra("BABYFOOD", babyFoodAdded);
                    intent.putExtra("TOTALPERSON", totalPerson.getText().toString());
                    intent.putExtra("TOTALPAY", totalAmount.getText().toString());
                    startActivity(intent);
                }
            }
        });
        //PROCEED TO PAYMENT button ends here


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
                Intent intent = new Intent(TourSummary.this, TourType.class);
                intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                startActivity(intent);
            }
        });

        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TourSummary.this, MyTours.class);
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
                Intent intent = new Intent(TourSummary.this, MyTours.class);
                intent.putExtra("USERMAIL", getIntent().getStringExtra("USERMAIL"));
                startActivity(intent);
                break;

            case R.id.my_profile:
                Intent intent2 = new Intent(TourSummary.this, TouristProfile.class);
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
                Intent intent3 = new Intent(TourSummary.this, LogIn.class);
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