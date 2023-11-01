package com.dev.wash_car;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {
    ArrayList<HashMap<String, Object>> itemsSelected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        GridView gridView = findViewById(R.id.gridViewProducts);
        List<String> names = Arrays.asList(
                "Shampoo premium",
                "Encerado",
                "Restaurador partes negras",
                "Shampoo ultra brillo",
                "Aspirada"
        );
        List<String> prices = Arrays.asList(
                "5.000",
                "10.000",
                "8.000",
                "8.000",
                "12.000"
        );
        List<Integer> images = Arrays.asList(
                R.drawable.img,
                R.drawable.img_1,
                R.drawable.img_2,
                R.drawable.img_3,
                R.drawable.img_4
        );
        gridView.setAdapter(new ProductsAdapter(this, names, prices, images));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                itemsSelected.add(new HashMap<String, Object>() {{
                    put("name", names.get(position));
                    put("price", prices.get(position));
                    put("image", images.get(position));
                }});
                Toast.makeText(ProductsActivity.this, "Se agregó a tu selección", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void back(View v) {
        finish();
    }
}