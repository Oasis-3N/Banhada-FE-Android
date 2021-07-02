package com.example.oasis_hackathon_app.endpoints;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oasis_hackathon_app.R;
import com.example.oasis_hackathon_app.methods.article.model.Article;
import com.example.oasis_hackathon_app.methods.article.request.GetArticle;

import java.util.Base64;

public class ArticleInfoView extends AppCompatActivity {
    GetArticle getArticleManager;
    Article article;
    int article_id;

    ImageView image;
    TextView remain_time;
    TextView max_group;
    TextView cost;
    TextView title;

    TextView article_info;
    TextView category;
    TextView content;

    ImageView seller_image;
    TextView nickname;
    TextView rate;

    LinearLayout chatting;
    Button back_btn;
    ImageView fork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_view_page);

        Intent nowintent = getIntent();
        article_id = nowintent.getIntExtra("article_id", -1);

        getArticleManager = new GetArticle();
        article = getArticleManager.query(article_id);

        image = (ImageView) findViewById(R.id.article_view_product);
        if (article.getImage().equals("no image")) {
            image.setImageResource(R.drawable.sample_product);
        }
        else {
            image.setImageBitmap(getBitmapFromString(article.getImage()));
        }

        //remain_time = (TextView) findViewById(R.id.article_view_remain_time);
        //remain_time.setText(Integer.toString(article.getRemain_time()));

        //max_group = (TextView) findViewById(R.id.article_view_max_group);
        //if (article.getArticle_type().equals("fresh")) {
            //max_group.setVisibility(View.GONE);
        //}
        //else {
            //max_group.setText(Integer.toString(article.getMax_num()));
        //}

        cost = (TextView) findViewById(R.id.article_view_cost);
        cost.setText(Integer.toString(article.getCost()));

        title = (TextView) findViewById(R.id.article_view_title);
        title.setText(article.getTitle());

        //article_info = (TextView) findViewById(R.id.article_view_article_info);
        //article_info.setText("이것저것");

        category = (TextView) findViewById(R.id.article_view_article_category);
        category.setText(article.getArticle_category());

        content = (TextView) findViewById(R.id.article_view_content);
        content.setText(article.getContent());

        seller_image = (ImageView) findViewById(R.id.article_view_seller_image);

        nickname = (TextView) findViewById(R.id.article_view_seller_nickname);
        nickname.setText(article.getSubmitter_nickname());

        rate = (TextView) findViewById(R.id.article_view_seller_rate);
        rate.setText("50");

        fork = (ImageView) findViewById(R.id.article_view_fork);

        chatting = (LinearLayout) findViewById(R.id.article_view_make_group);
        chatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private Bitmap getBitmapFromString(String stringPicture) {
        byte[] decodedString = Base64.getDecoder().decode(stringPicture);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
