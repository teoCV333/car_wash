package com.dev.wash_car;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText editTextEmailSignUp, editTextPasswordSignUp;
    Button btnCancelSignUp, btnEndSignUp;
    UserModel userModel;
    public DatabaseReference dbRef;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        editTextEmailSignUp = findViewById(R.id.editTextEmailSignUp);
        editTextPasswordSignUp = findViewById(R.id.editTextPasswordSignUp);
        btnCancelSignUp = findViewById(R.id. btnCancelSignUp);
        btnEndSignUp = findViewById(R.id. btnEndSignUp);

        userModel = new UserModel();

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
                String user = editTextEmailSignUp.getText().toString().trim();
                String pass = editTextPasswordSignUp.getText().toString().trim();
                auth.createUserWithEmailAndPassword(user, pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "registro exitoso", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "registro fallido", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        } catch(NumberFormatException e) {
            new AlertDialog.Builder(this)
                    .setMessage("Ha ocurrido un error al crear su usuario, intentelo nuevamente")
                    .show();
        }
    }
}