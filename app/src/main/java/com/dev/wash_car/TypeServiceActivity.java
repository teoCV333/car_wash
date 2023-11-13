package com.dev.wash_car;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TypeServiceActivity extends AppCompatActivity {

    Button btnSimple, btnNormal, btnEspecial, btnPremium, btnBack, btnSave;

    String typeService;

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

        btnSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSimple.setSelected(true);
                btnNormal.setSelected(false);
                btnEspecial.setSelected(false);
                btnPremium.setSelected(false);
                typeService = "Sencillo";
            }
        });

        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSimple.setSelected(false);
                btnNormal.setSelected(true);
                btnEspecial.setSelected(false);
                btnPremium.setSelected(false);
                typeService = "Normal";
            }
        });

        btnEspecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSimple.setSelected(false);
                btnNormal.setSelected(false);
                btnEspecial.setSelected(true);
                btnPremium.setSelected(false);
                typeService = "Especial";
            }
        });

        btnPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSimple.setSelected(false);
                btnNormal.setSelected(false);
                btnEspecial.setSelected(false);
                btnPremium.setSelected(true);
                typeService = "Premium";
            }
        });

    }

    public void back(View view) {
        Intent intent = new Intent(TypeServiceActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


    public void save(View view) {
        Toast.makeText(TypeServiceActivity.this, "Se guardó tu selección", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(TypeServiceActivity.this, HomeActivity.class);
        intent.putExtra("typeService", typeService);
        startActivity(intent);
    }

}