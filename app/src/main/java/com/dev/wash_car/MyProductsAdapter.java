package com.dev.wash_car;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class MyProductsAdapter extends BaseAdapter {

    private Context mContext;
    private List<HashMap<String, Object>> items;

    public MyProductsAdapter(Context context, List<HashMap<String, Object>> items) {
        mContext = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.product_item, parent, false);
        }
        TextView textViewProductName = convertView.findViewById(R.id.textViewProductName);
        TextView textViewProductPrice = convertView.findViewById(R.id.textViewWelcome);
        ImageView imageViewProduct = convertView.findViewById(R.id.imageViewProduct);
        textViewProductName.setText((String) items.get(position).get("name"));
        textViewProductPrice.setText(String.valueOf(items.get(position).get("price")));
        imageViewProduct.setImageResource((int) items.get(position).get("image"));
        return convertView;
    }
}
