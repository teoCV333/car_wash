package com.dev.wash_car;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.HashMap;
import java.util.List;

public class MyProductsActivity extends AppCompatActivity {

    List<HashMap<String, Object>> items;
    MyProductsAdapter myProductsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products);

        items = (List<HashMap<String, Object>>) getIntent().getSerializableExtra("items");
        GridView gridView = findViewById(R.id.gridViewMyProducts);
        gridView.setAdapter(new MyProductsAdapter(this, items));
    }

    public void back(View v) {
        finish();
    }
}