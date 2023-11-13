package com.dev.wash_car;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    public TextView textViewFullnameHome;
    String email;

    ServiceModel service;
    VehicleRegistModel vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        vehicle = new VehicleRegistModel();
        service = new ServiceModel();
        textViewFullnameHome = findViewById(R.id.textViewFullnameHome);

        email = getIntent().getStringExtra("email");
        queryUserByEmail(email);



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
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void schedule(View v) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(HomeActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Handle the selected date
                        // The selected date is available in year, monthOfYear, and dayOfMonth
                    }
                }, year, month, day);

        // Show the dialog
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