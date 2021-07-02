package com.example.oasis_hackathon_app.endpoints;

import android.app.admin.DelegatedAdminReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
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

public class DeliveryView extends AppCompatActivity {
    User user;

    GetArticles getArticlesManager;
    int previewArticleStartIdx = 0;

    String article_category = "vegetable";

    ArrayList<ArticlePreview> articlePreviewArrayList;

    GridView articlePreviewGridView;
    ArticlePreviewAdapter articlePreviewAdapter;

    LinearLayout home_btn;
    LinearLayout banhada_btn;
    LinearLayout communication_btn;
    LinearLayout mybanhada_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_page);

        Intent nowintent = getIntent();
        user = new User(nowintent.getStringExtra("userinfo"));

        getArticlesManager = new GetArticles(user.UsertoString());
        getArticlesManager.setAddress(user.getAddress());

        articlePreviewGridView = (GridView) findViewById(R.id.category_page_article_gridview);
        articlePreviewAdapter = new ArticlePreviewAdapter();
        articlePreviewGridView.setAdapter(articlePreviewAdapter);

        setPreviewArticles();

        articlePreviewGridView.setOnItemClickListener((parent, view, position, id) -> {
            Log.d("CLICK", "Item Clicked");
            ArticlePreview clicked_article = (ArticlePreview) articlePreviewAdapter.getItem(position);
            Intent intent = new Intent(DeliveryView.this, ArticleInfoView.class);
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
                Intent intent = new Intent(DeliveryView.this, PostArticle.class);
                intent.putExtra("userinfo", user.UsertoString());
                startActivity(intent);
            }
        });

        communication_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeliveryView.this, MessageGroupView.class);
                intent.putExtra("userinfo", user.UsertoString());
                startActivity(intent);
            }
        });

        mybanhada_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeliveryView.this, ProfileView.class);
                intent.putExtra("userinfo", user.UsertoString());
            }
        });
    }

    public void setPreviewArticles() {
        articlePreviewArrayList = getArticlesManager.query_with_category("fresh", article_category, previewArticleStartIdx, previewArticleStartIdx + 19);
        previewArticleStartIdx += 20;

        for (int i = 0; i < articlePreviewArrayList.size(); i++) {
            articlePreviewAdapter.addItem(articlePreviewArrayList.get(i));
        }

        articlePreviewAdapter.notifyDataSetChanged();
    }
}
