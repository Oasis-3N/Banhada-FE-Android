package com.example.oasis_hackathon_app.methods;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oasis_hackathon_app.R;

import java.util.ArrayList;

public class CategoryBtnAdapter extends BaseAdapter {
    ArrayList<CategoryBtn> items = new ArrayList<CategoryBtn>();
    Context context;

    public void addItem(CategoryBtn item) {
        items.add(item);
    }

    @Override
    public int getCount(){
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public boolean isEnabled (int position){
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        context = parent.getContext();
        CategoryBtn listItem = items.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.category_btn_view, parent, false);
        }

        ImageView image = (ImageView) convertView.findViewById(R.id.category_btn_imageview);
        TextView text = (TextView) convertView.findViewById(R.id.category_btn_textview);

        image.setImageResource(listItem.getImage());
        text.setText(listItem.getCategory());

        return convertView;
    }
}
