package com.dev.wash_car;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    public Button btnCancel, btnConfirm;

    private EditText editTextNameRegist, editTextLastNameRegist, editTextEmailConfirmRegist, editTextVehicleTypeRegist, editTextAgeRegist, editTextPlateRegist, editTextPhoneRegist, editTextEmailRegist;

    public CheckBox checkBoxTyC, checkBoxPdP;

    public DatabaseReference dbRef;

    VehicleRegistModel vehicleRegistModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnCancel = findViewById(R.id.btnCancelRegist);
        editTextAgeRegist = findViewById(R.id.editTextAgeRegist);
        editTextEmailRegist = findViewById(R.id.editTextEmailRegist);
        editTextEmailConfirmRegist = findViewById(R.id.editTextEmailConfirmRegist);
        editTextNameRegist = findViewById(R.id.editTextNameRegist);
        editTextLastNameRegist = findViewById(R.id.editTextLastNameRegist);
        editTextPhoneRegist = findViewById(R.id.editTextPhoneRegist);
        editTextVehicleTypeRegist = findViewById(R.id.editTextVehicleTypeRegist);
        editTextPlateRegist = findViewById(R.id.editTextPlateRegist);
        checkBoxTyC = findViewById(R.id.checkBoxTyC);
        checkBoxPdP = findViewById(R.id.checkBoxPdP);

        vehicleRegistModel = new VehicleRegistModel();
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

    public void register(View v) {
        dbRef = FirebaseDatabase.getInstance().getReference().child("vehicleRegist");

        Calendar cal = Calendar.getInstance();
        System.out.println("The Current Year is:" + cal.get(Calendar.YEAR));
        try {
            if(TextUtils.isEmpty(editTextNameRegist.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Porfavor ingrese sus nombres", Toast.LENGTH_SHORT).show();
            } else if(TextUtils.isEmpty(editTextLastNameRegist.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Porfavor ingrese sus apellidos", Toast.LENGTH_SHORT).show();
            } else if(TextUtils.isEmpty(editTextVehicleTypeRegist.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Porfavor ingrese el tipo de vehiculo", Toast.LENGTH_SHORT).show();
            } else if(TextUtils.isEmpty(editTextAgeRegist.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Porfavor ingrese su edad", Toast.LENGTH_SHORT).show();
            } else if(TextUtils.isEmpty(editTextPhoneRegist.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Porfavor ingrese su número de telefono", Toast.LENGTH_SHORT).show();
            } else if(TextUtils.isEmpty(editTextEmailRegist.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Porfavor ingrese su correo electronico", Toast.LENGTH_SHORT).show();
            } else if(validateEmail(editTextEmailRegist.getText().toString()) == false) {
                Toast.makeText(getApplicationContext(), "Porfavor ingrese un correo electronico valido", Toast.LENGTH_SHORT).show();
            } else if(TextUtils.isEmpty(editTextEmailConfirmRegist.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Porfavor confirme su correo electronico", Toast.LENGTH_SHORT).show();
            } else if(validateEmail(editTextEmailConfirmRegist.getText().toString()) == false) {
                Toast.makeText(getApplicationContext(), "Porfavor ingrese un correo electronico valido", Toast.LENGTH_SHORT).show();
            } else if(TextUtils.isEmpty(editTextPlateRegist.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Porfavor ingrese la placa del vehiculo", Toast.LENGTH_SHORT).show();
            } else if(!checkBoxTyC.isChecked()){
                Toast.makeText(getApplicationContext(), "Porfavor acepte los Terminos y Condiciones", Toast.LENGTH_SHORT).show();
            } else if(!checkBoxPdP.isChecked()){
                Toast.makeText(getApplicationContext(), "Porfavor acepte las Politicas de Privacidad", Toast.LENGTH_SHORT).show();
            } else {
                vehicleRegistModel.setFullname(editTextNameRegist.getText().toString());
                vehicleRegistModel.setLastname(editTextLastNameRegist.getText().toString());
                vehicleRegistModel.setVehicleType(editTextVehicleTypeRegist.getText().toString());
                vehicleRegistModel.setAge(editTextAgeRegist.getText().toString());
                vehicleRegistModel.setPlate(editTextPlateRegist.getText().toString());
                vehicleRegistModel.setPhone(editTextPhoneRegist.getText().toString());
                vehicleRegistModel.setEmail(editTextEmailRegist.getText().toString());
                vehicleRegistModel.setId(String.format("%s%s", editTextPlateRegist.getText().toString(), cal.get(Calendar.YEAR)));
                dbRef.push().setValue(vehicleRegistModel);
                new AlertDialog.Builder(this)
                        .setMessage("Su registro fue exitoso.")
                        .show();
                Intent intent = new Intent(this, SignUpActivity.class);
                intent.putExtra("email", editTextEmailRegist.getText().toString());
                startActivity(intent);
            }
        } catch(NumberFormatException e) {
            new AlertDialog.Builder(this)
                    .setMessage("por favor acepta los tyc y los pdp")
                    .show();
        }
    }

    public boolean validateEmail(String email) {

        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(email);

        if (mather.find() == true) {
            System.out.println("El email ingresado es válido.");
            return true;
        } else {
            System.out.println("El email ingresado es inválido.");
            return false;
        }
    }

}