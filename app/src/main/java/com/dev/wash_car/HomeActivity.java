package com.dev.wash_car;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    public TextView textViewFullnameHome;
    String email;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    ServiceModel service;
    VehicleRegistModel vehicle;
    String typeService;
    List<HashMap<String, Object>> products;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        vehicle = new VehicleRegistModel();
        service = ServiceModel.getInstance();
        textViewFullnameHome = findViewById(R.id.textViewFullnameHome);

        email = getIntent().getStringExtra("email");
        queryUserByEmail(email);

        if (getIntent().hasExtra("products")) {
            products = (List<HashMap<String, Object>>) getIntent().getSerializableExtra("products");
            service.setProducts(products);
        }
        if (getIntent().hasExtra("typeService")) {
            typeService = (String) getIntent().getSerializableExtra("typeService");
            service.setTypeService(typeService);
        }


    }

    @Override
    public void onBackPressed() {
        logOut(null);
    }


    public void productsList(View v) {
        Intent intent = new Intent(this, ProductsActivity.class);
        startActivity(intent);
    }

    public void typeService(View v) {
        Intent intent = new Intent(this, TypeServiceActivity.class);
        startActivity(intent);
    }

    public void myService(View v) {
        Intent intent = new Intent(this, MyServiceActivity.class);
        startActivity(intent);
    }

    public void payments(View v) {
        Intent intent = new Intent(this, PaymentsActivity.class);
        startActivity(intent);
    }

    public void logOut(View v) {
        new AlertDialog.Builder(this)
                .setMessage("¿Estás seguro de que deseas cerrar sesión?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    public void schedule(View v) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(HomeActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(HomeActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                        String date = String.format("%04d-%02d-%02d %02d:%02d",
                                                year, monthOfYear + 1, dayOfMonth, hourOfDay, minute);

                                        try {
                                            Date schedule = dateFormat.parse(date);
                                            service.setSchedule(schedule);
                                        } catch (ParseException err) {
                                            err.printStackTrace();
                                        }
                                    }
                                }, hour, minute, true);

                        // Show the time picker after selecting the date
                        timePickerDialog.show();
                    }
                }, year, month, day);

        // Show the date picker
        datePickerDialog.show();
    }


    private void queryUserByEmail(String email) {
        Query query = databaseReference.child("vehicleRegist").orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Iterate through the results
                for (DataSnapshot vehicleSnapshot : dataSnapshot.getChildren()) {
                    textViewFullnameHome.setText(vehicleSnapshot.child("fullname").getValue(String.class));
                    vehicle.setFullname(vehicleSnapshot.child("fullname").getValue(String.class));
                    vehicle.setLastname(vehicleSnapshot.child("lastname").getValue(String.class));
                    vehicle.setPlate(vehicleSnapshot.child("plate").getValue(String.class));
                    vehicle.setEmail(vehicleSnapshot.child("email").getValue(String.class));
                    vehicle.setAge(vehicleSnapshot.child("age").getValue(String.class));
                    vehicle.setPhone(vehicleSnapshot.child("phone").getValue(String.class));
                    vehicle.setVehicleType(vehicleSnapshot.child("vehicleType").getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Log.e("Firebase", "Error in query: " + databaseError.getMessage());
            }
        });
    }

}