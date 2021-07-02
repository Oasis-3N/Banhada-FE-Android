package com.example.oasis_hackathon_app.endpoints;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oasis_hackathon_app.R;
import com.example.oasis_hackathon_app.methods.account.request.GetUserData;
import com.example.oasis_hackathon_app.methods.account.model.User;
import com.example.oasis_hackathon_app.methods.article.model.ArticlePreview;
import com.example.oasis_hackathon_app.methods.article.adapter.ArticlePreviewAdapter;
import com.example.oasis_hackathon_app.methods.article.request.GetArticles;

import java.util.ArrayList;

public class HomeView extends AppCompatActivity {
    GetUserData getUserData;
    User user;

    GetArticles getArticlesManager;
    int previewArticleStartIdx = 0;

    ArrayList<ArticlePreview> previewArticles;

    GridView articlePreviewGridView;
    ArticlePreviewAdapter articlePreviewAdapter;

    LinearLayout freshTypeBtn;
    LinearLayout deliveryTypeBtn;

    LinearLayout banhada_btn;
    LinearLayout communication_btn;
    LinearLayout mybanhada_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        Intent nowintent = getIntent();
        getUserData = new GetUserData();
        user = getUserData.GetUser(nowintent.getStringExtra("id"));

        getArticlesManager = new GetArticles(user.UsertoString());
        getArticlesManager.setAddress(user.getAddress());
        articlePreviewAdapter = new ArticlePreviewAdapter();
        previewArticles = getArticlesManager.query_with_no_category(previewArticleStartIdx, previewArticleStartIdx + 20);
        previewArticleStartIdx = 21;

        for (int i = 0; i < previewArticles.size(); i++) {
            articlePreviewAdapter.addItem(previewArticles.get(i));
        }

        articlePreviewGridView = (GridView) findViewById(R.id.home_page_gridview);
        articlePreviewGridView.setAdapter(articlePreviewAdapter);

        articlePreviewGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ArticlePreview clicked_article = (ArticlePreview) articlePreviewAdapter.getItem(position);
                Intent intent = new Intent(HomeView.this, ArticleInfoView.class);
                intent.putExtra("userinfo", user.UsertoString());
                intent.putExtra("article_id", clicked_article.getArticle_id());
                startActivity(intent);
            }


        });

        freshTypeBtn = (LinearLayout) findViewById(R.id.article_type_fresh_btn);
        deliveryTypeBtn = (LinearLayout) findViewById(R.id.article_type_delivery_btn);

        freshTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeView.this, FreshView.class);
                intent.putExtra("userinfo", user.UsertoString());
                startActivity(intent);
            }
        });

        deliveryTypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeView.this, DeliveryView.class);
                intent.putExtra("userinfo", user.UsertoString());
                startActivity(intent);
            }
        });

        banhada_btn = (LinearLayout) findViewById(R.id.bottom_bar_banhada_btn);
        communication_btn = (LinearLayout) findViewById(R.id.bottom_bar_communication_btn);
        mybanhada_btn = (LinearLayout) findViewById(R.id.bottom_bar_mybanhada_btn);

        banhada_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeView.this, PostArticleView.class);
                intent.putExtra("userinfo", user.UsertoString());
                startActivity(intent);
            }
        });

        communication_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeView.this, MessageGroupView.class);
                intent.putExtra("userinfo", user.UsertoString());
                startActivity(intent);
            }
        });

        mybanhada_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeView.this, ProfileView.class);
                intent.putExtra("userinfo", user.UsertoString());
            }
        });
    }
}
