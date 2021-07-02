package com.example.oasis_hackathon_app.methods.message.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oasis_hackathon_app.R;
import com.example.oasis_hackathon_app.endpoints.MessageView;
import com.example.oasis_hackathon_app.methods.message.model.MessageGroup;

import java.util.ArrayList;
import java.util.Base64;

public class MessageGroupAdapter extends BaseAdapter {
    ArrayList<MessageGroup> items = new ArrayList<MessageGroup>();
    Context context;

    public void addItem(MessageGroup item) {
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
        MessageGroup listItem = items.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.message_group_view, parent, false);
        }

        ImageView profile_image = (ImageView) convertView.findViewById(R.id.message_group_view_profile_img);
        ImageView product_image = (ImageView) convertView.findViewById(R.id.message_group_view_product_image);
        TextView nickname = (TextView) convertView.findViewById(R.id.message_group_view_nickname);
        TextView preview = (TextView) convertView.findViewById(R.id.message_group_view_preview);
        TextView timer = (TextView) convertView.findViewById(R.id.message_group_view_timer);

        Button communication = (Button) convertView.findViewById(R.id.message_group_view_communication);

        if(listItem.getCounterImage().equals("no image")) {
            profile_image.setImageResource(R.drawable.sample_product);
        }
        else {
            profile_image.setImageBitmap(getBitmapFromString(listItem.getCounterImage()));
        }

        if(listItem.getProductImage().equals("no image")) {
            product_image.setImageResource(R.drawable.sample_product);
        }
        else {
            product_image.setImageBitmap(getBitmapFromString(listItem.getProductImage()));
        }

        nickname.setText(listItem.getCounterNickname());
        preview.setText(listItem.getPreview());
        timer.setText(listItem.getRemainTime());

        communication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MessageView.class);
                intent.putExtra("userinfo", listItem.getUserinfo());
                intent.putExtra("message_group_id", listItem.getGroupId());
                v.getContext().startActivity(intent);
            }
        });

        return convertView;
    }

    private Bitmap getBitmapFromString(String stringPicture) {
        byte[] decodedString = Base64.getDecoder().decode(stringPicture);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
