package com.dev.wash_car;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductsAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> names;
    private List<String> prices;
    private List<Integer> images;

    public ProductsAdapter(Context context, List<String> names, List<String> prices, List<Integer> images) {
        mContext = context;
        this.names = names;
        this.prices = prices;
        this.images = images;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.product_item, parent, false);
        }

        TextView textViewProductName = convertView.findViewById(R.id.textViewProductName);
        TextView textViewProductPrice = convertView.findViewById(R.id.textViewProductPrice);
        ImageView imageViewProduct = convertView.findViewById(R.id.imageViewProduct);
        textViewProductName.setText(names.get(position));
        textViewProductPrice.setText(prices.get(position));
        imageViewProduct.setImageResource(images.get(position));

        return convertView;
    }
}
