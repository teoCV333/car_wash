package com.dev.wash_car;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MyServiceActivity extends AppCompatActivity {

    TextView textViewMyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

        textViewMyService = findViewById(R.id.textViewMyService);
        textViewMyService.setTypeface(null, Typeface.BOLD);
    }
}