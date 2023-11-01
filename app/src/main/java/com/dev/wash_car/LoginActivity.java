package com.dev.wash_car;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUser, txtPass;

    String user = "root";
    String pass = "root";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
    }

    public void register(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View v) {
        String user = txtUser.getText().toString();
        String pass = txtPass.getText().toString();
        if(user.equals(this.user)&& pass.equals(this.pass)) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else {
            new AlertDialog.Builder(this)
                    .setMessage("Credenciales incorrectas!!")
                    .show();
        }
    }

}