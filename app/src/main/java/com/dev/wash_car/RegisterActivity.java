package com.dev.wash_car;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    public Button btnCancel, btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnCancel = findViewById(R.id.btnCancelRegist);
    }

    public void cancel(View v) {
        new AlertDialog.Builder(this)
                .setMessage("¿Estás seguro de que desea cancelar el registro?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    super.onBackPressed();
                })
                .setNegativeButton("No", (dialog, which) -> {
                })
                .show();
    }
}