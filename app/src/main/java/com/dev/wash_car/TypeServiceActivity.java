package com.dev.wash_car;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TypeServiceActivity extends AppCompatActivity {

    Button btnSimple, btnNormal, btnEspecial, btnPremium, btnBack, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_service);

        btnSimple = findViewById(R.id.btnSimple);
        btnNormal = findViewById(R.id.btnNormal);
        btnEspecial = findViewById(R.id.btnEspecial);
        btnPremium = findViewById(R.id.btnPremium);
        btnBack = findViewById(R.id.btnBack);
        btnSave = findViewById(R.id.btnSave);
    }

    public void back(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void save(View view) {
        Toast.makeText(TypeServiceActivity.this, "Se guardó tu selección", Toast.LENGTH_SHORT).show();
    }

}