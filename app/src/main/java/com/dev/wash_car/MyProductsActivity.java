package com.dev.wash_car;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class MyProductsActivity extends AppCompatActivity {

    List<HashMap<String, Object>> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products);

        items = (List<HashMap<String, Object>>) getIntent().getSerializableExtra("items");
        GridView gridView = findViewById(R.id.gridViewMyProducts);
        MyProductsAdapter adapter = new MyProductsAdapter(this, items);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                new AlertDialog.Builder(MyProductsActivity.this)
                        .setMessage("¿Estás seguro de que desea borrar este producto de su selección?")
                        .setPositiveButton("Sí", (dialog, which) -> {
                            items.remove(position);
                            adapter.notifyDataSetChanged();
                        })
                        .setNegativeButton("No", (dialog, which) -> {
                        })
                        .show();
            }
        });
    }

    public void back(View v) {
        Intent intent = new Intent(MyProductsActivity.this, ProductsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("products", (Serializable) items);
        startActivity(intent);
        finish();
    }

    public void save(View v) {
        Intent intent = new Intent(MyProductsActivity.this, HomeActivity.class);
        intent.putExtra("products", (Serializable) items);
        startActivity(intent);
    }
}