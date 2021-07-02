package com.example.oasis_hackathon_app.methods.message.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oasis_hackathon_app.R;
import com.example.oasis_hackathon_app.methods.message.model.Message;

import java.util.ArrayList;
import java.util.Base64;

public class MessageAdapter extends BaseAdapter {
    ArrayList<Message> items = new ArrayList<Message>();
    Context context;
    String nickname;

    public MessageAdapter(String nickname) {
        this.nickname = nickname;
    }

    public void addItem(Message item) {
        items.add(item);
    }

    public void resetItem() {
        items = new ArrayList<Message>();
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
        Message listItem = items.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(listItem.getNickname().equals(this.nickname)) {
                convertView = inflater.inflate(R.layout.message_own_view, parent, false);
            }
            else {
                convertView = inflater.inflate(R.layout.message_counter_view, parent, false);
            }
        }

        TextView text = (TextView) convertView.findViewById(R.id.message_text);
        ImageView image = (ImageView) convertView.findViewById(R.id.message_image);

        if (listItem.getType().equals("imagee")) {
            image.setVisibility(View.VISIBLE);
            image.setImageBitmap(getBitmapFromString(listItem.getImage()));
        }
        else {
            text.setVisibility(View.VISIBLE);
            text.setText(listItem.getMessage());
        }

        return convertView;
    }

    private Bitmap getBitmapFromString(String stringPicture) {
        byte[] decodedString = Base64.getDecoder().decode(stringPicture);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
