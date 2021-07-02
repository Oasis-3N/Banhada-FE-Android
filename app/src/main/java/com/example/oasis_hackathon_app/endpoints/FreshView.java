package com.example.oasis_hackathon_app.endpoints;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oasis_hackathon_app.R;
import com.example.oasis_hackathon_app.methods.account.model.User;
import com.example.oasis_hackathon_app.methods.article.adapter.ArticlePreviewAdapter;
import com.example.oasis_hackathon_app.methods.article.model.ArticlePreview;
import com.example.oasis_hackathon_app.methods.article.request.GetArticles;
import com.example.oasis_hackathon_app.methods.article.request.PostArticle;

import java.util.ArrayList;

public class FreshView extends AppCompatActivity {
    User user;

    GetArticles getArticlesManager;
    int previewArticleStartIdx = 0;

    String article_category = "vegetable";

    ArrayList<ArticlePreview> articlePreviewArrayList;

    GridView articlePreviewGridView;
    ArticlePreviewAdapter articlePreviewAdapter;

    LinearLayout vegetable;
    LinearLayout fruit;
    LinearLayout daily;
    LinearLayout processed;
    LinearLayout sidemenu;

    LinearLayout home_btn;
    LinearLayout banhada_btn;
    LinearLayout communication_btn;
    LinearLayout mybanhada_btn;

    ImageView back_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fresh_page);

        Intent nowintent = getIntent();
        user = new User(nowintent.getStringExtra("userinfo"));

        back_btn = (ImageView) findViewById(R.id.fresh_page_back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getArticlesManager = new GetArticles(user.UsertoString());
        getArticlesManager.setAddress(user.getAddress());

        articlePreviewGridView = (GridView) findViewById(R.id.category_page_article_gridview);
        articlePreviewAdapter = new ArticlePreviewAdapter();
        articlePreviewGridView.setAdapter(articlePreviewAdapter);

        setPreviewArticles();

        vegetable = (LinearLayout) findViewById(R.id.vegetable_btn_view);
        vegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                article_category = "vegetable";
                setPreviewArticles();
            }
        });

        fruit = (LinearLayout) findViewById(R.id.fruit_btn_view);
        fruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                article_category = "fruit";
                setPreviewArticles();
            }
        });

        daily = (LinearLayout) findViewById(R.id.daily_btn_view);
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                article_category = "daily";
                setPreviewArticles();
            }
        });

        processed = (LinearLayout) findViewById(R.id. processed_btn_view);
        processed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                article_category = "processed";
                setPreviewArticles();
            }
        });

        sidemenu = (LinearLayout) findViewById(R.id.sidemenu_btn_view);
        sidemenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                article_category = "sidemenu";
                setPreviewArticles();
            }
        });

        articlePreviewGridView.setOnItemClickListener((parent, view, position, id) -> {
            Log.d("CLICK", "Item Clicked");
            ArticlePreview clicked_article = (ArticlePreview) articlePreviewAdapter.getItem(position);
            Intent intent = new Intent(FreshView.this, ArticleInfoView.class);
            intent.putExtra("userinfo", user.UsertoString());
            intent.putExtra("article_id", clicked_article.getArticle_id());
            startActivity(intent);
        });

        home_btn = (LinearLayout) findViewById(R.id.bottom_bar_home_btn);
        banhada_btn = (LinearLayout) findViewById(R.id.bottom_bar_banhada_btn);
        communication_btn = (LinearLayout) findViewById(R.id.bottom_bar_communication_btn);
        mybanhada_btn = (LinearLayout) findViewById(R.id.bottom_bar_mybanhada_btn);

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        banhada_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FreshView.this, PostArticle.class);
                intent.putExtra("userinfo", user.UsertoString());
                startActivity(intent);
            }
        });

        communication_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FreshView.this, MessageGroupView.class);
                intent.putExtra("userinfo", user.UsertoString());
                startActivity(intent);
            }
        });

        mybanhada_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FreshView.this, ProfileView.class);
                intent.putExtra("userinfo", user.UsertoString());
            }
        });
    }

    public void setPreviewArticles() {
        articlePreviewArrayList = getArticlesManager.query_with_category("fresh", article_category, previewArticleStartIdx, previewArticleStartIdx + 19);
        articlePreviewAdapter.clearItem();

        for (int i = 0; i < articlePreviewArrayList.size(); i++) {
            articlePreviewAdapter.addItem(articlePreviewArrayList.get(i));
        }

        articlePreviewAdapter.notifyDataSetChanged();
    }

}
