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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {
    List<HashMap<String, Object>> itemsSelected = new ArrayList<>();

    ProductsAdapter productsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        if (getIntent().hasExtra("products")) {
            itemsSelected = (List<HashMap<String, Object>>) getIntent().getSerializableExtra("products");
        }

        GridView gridView = findViewById(R.id.gridViewMyProducts);
        List<String> names = Arrays.asList(
                "Shampoo premium",
                "Encerado",
                "Restaurador partes negras",
                "Shampoo ultra brillo",
                "Aspirada"
        );
        List<String> prices = Arrays.asList(
                "5.000$",
                "10.000$",
                "8.000$",
                "8.000$",
                "12.000$"
        );
        List<Integer> images = Arrays.asList(
                R.drawable.img,
                R.drawable.img_1,
                R.drawable.img_2,
                R.drawable.img_3,
                R.drawable.img_4
        );
        productsAdapter = new ProductsAdapter(this, names, prices, images);
        gridView.setAdapter(productsAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedProductName = names.get(position);

                boolean isAlreadySelected = false;
                for (HashMap<String, Object> selectedItem : itemsSelected) {
                    String itemName = (String) selectedItem.get("name");
                    if (itemName != null && itemName.equals(selectedProductName)) {
                        isAlreadySelected = true;
                        break;
                    }
                }

                if (isAlreadySelected) {
                    Toast.makeText(ProductsActivity.this, "Este producto ya está seleccionado", Toast.LENGTH_SHORT).show();
                } else {
                    itemsSelected.add(new HashMap<String, Object>() {{
                        put("name", selectedProductName);
                        put("price", prices.get(position));
                        put("image", images.get(position));
                    }});
                    Toast.makeText(ProductsActivity.this, "Se agregó a tu selección", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void back(View v) {
        Intent intent = new Intent(ProductsActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void mySelection(View v) {
        Intent intent = new Intent(ProductsActivity.this, MyProductsActivity.class);
        intent.putExtra("items", (Serializable) itemsSelected);
        startActivity(intent);
    }
}