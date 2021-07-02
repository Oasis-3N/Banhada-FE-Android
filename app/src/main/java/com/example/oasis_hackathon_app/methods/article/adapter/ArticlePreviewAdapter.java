package com.example.oasis_hackathon_app.methods.article.adapter;

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

import com.example.oasis_hackathon_app.R;
import com.example.oasis_hackathon_app.endpoints.ArticleInfoView;
import com.example.oasis_hackathon_app.methods.article.model.ArticlePreview;

import java.util.ArrayList;
import java.util.Base64;

public class ArticlePreviewAdapter extends BaseAdapter {
    ArrayList<ArticlePreview> items = new ArrayList<ArticlePreview>();
    Context context;

    public void addItem(ArticlePreview item) {
        items.add(item);
    }

    public void clearItem() {
        items = new ArrayList<ArticlePreview>();
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
        ArticlePreview listItem = items.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.article_preview_view, parent, false);
        }

        ImageView image = (ImageView) convertView.findViewById(R.id.article_preview_image);
        ImageView fork = (ImageView) convertView.findViewById(R.id.article_preview_fork);
        TextView title = (TextView) convertView.findViewById(R.id.article_preview_title);
        TextView cost = (TextView) convertView.findViewById(R.id.article_preview_cost);
        TextView submitter = (TextView) convertView.findViewById(R.id.article_preview_submitter);
        TextView remain_time = (TextView) convertView.findViewById(R.id.article_preview_remain_time);
        Button look = (Button) convertView.findViewById(R.id.article_preview_look);

        if (listItem.getImage().equals("no image")) {
            image.setFocusable(false);
        } else {
            image.setImageBitmap(getBitmapFromString(listItem.getImage()));
            image.setFocusable(false);
        }

        fork.setFocusable(false);

        title.setText(listItem.getTitle());
        title.setFocusable(false);
        cost.setText(Integer.toString(listItem.getCost()));
        cost.setFocusable(false);
        submitter.setText(listItem.getSubmitter_nickname());
        submitter.setFocusable(false);
        remain_time.setText(listItem.getRemainTime());
        remain_time.setFocusable(false);

        look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ArticleInfoView.class);
                intent.putExtra("userinfo", listItem.getUserinfo());
                intent.putExtra("article_id", listItem.getArticle_id());
                v.getContext().startActivity(intent);
            }
        });

        convertView.setFocusable(true);

        return convertView;
    }

    private Bitmap getBitmapFromString(String stringPicture) {
        byte[] decodedString = Base64.getDecoder().decode(stringPicture);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
