package com.dev.wash_car;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText editTextEmailSignUp, editTextPasswordSignUp;
    Button btnCancelSignUp, btnEndSignUp;
    UserModel user;
    public DatabaseReference dbRef;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextEmailSignUp = findViewById(R.id.editTextEmailSignUp);
        editTextPasswordSignUp = findViewById(R.id.editTextPasswordSignUp);
        btnCancelSignUp = findViewById(R.id. btnCancelSignUp);
        btnEndSignUp = findViewById(R.id. btnEndSignUp);

        user = new UserModel();

        email = getIntent().getStringExtra("email");

        editTextEmailSignUp.setText(email);
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

    public void signUp(View v) {

        try {
            if(TextUtils.isEmpty(editTextPasswordSignUp.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Porfavor ingrese una contraseña", Toast.LENGTH_SHORT).show();
            }
            else {
                user.setEmail(editTextEmailSignUp.getText().toString());
                user.setPassword(editTextPasswordSignUp.getText().toString());
                dbRef.push().setValue(user);
                new AlertDialog.Builder(this)
                        .setMessage("Su usuario fue creado exitosamente.")
                        .show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        } catch(NumberFormatException e) {
            new AlertDialog.Builder(this)
                    .setMessage("Ha ocurrido un error al crear su usuario, intentelo nuevamente")
                    .show();
        }
    }
}