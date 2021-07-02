package com.example.oasis_hackathon_app.endpoints;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oasis_hackathon_app.R;
import com.example.oasis_hackathon_app.methods.CategoryBtn;
import com.example.oasis_hackathon_app.methods.CategoryBtnAdapter;
import com.example.oasis_hackathon_app.methods.account.model.User;
import com.example.oasis_hackathon_app.methods.article.model.ArticlePreview;
import com.example.oasis_hackathon_app.methods.article.adapter.ArticlePreviewAdapter;
import com.example.oasis_hackathon_app.methods.article.request.GetArticles;
import com.example.oasis_hackathon_app.methods.article.request.PostArticle;

import java.util.ArrayList;

public class CategoryView extends AppCompatActivity {
    User user;
    TextView title;

    GetArticles getArticlesManager;
    int previewArticleStartIdx = 0;

    String article_type;
    String article_category = "vegetable";
    String[] freshCategoryString = {"vegetable", "fruit", "beverage", "salad", "sidemenu", "daily", "processed"};
    String[] deliveryCategoryString = {"vegetable", "fruit", "beverage", "salad", "sidemenu", "daily", "processed"};

    ArrayList<ArticlePreview> articlePreviewArrayList;

    GridView articlePreviewGridView;
    GridView categoryBtnGridView;
    ArticlePreviewAdapter articlePreviewAdapter;
    CategoryBtnAdapter categoryBtnAdapter;

    LinearLayout home_btn;
    LinearLayout banhada_btn;
    LinearLayout communication_btn;
    LinearLayout mybanhada_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_page);

        Intent nowintent = getIntent();
        user = new User(nowintent.getStringExtra("userinfo"));
        article_type = nowintent.getStringExtra("article_type");

        title = (TextView) findViewById(R.id.category_page_title);
        title.setText(article_type);

        getArticlesManager = new GetArticles(user.UsertoString());
        getArticlesManager.setAddress(user.getAddress());

        articlePreviewGridView = (GridView) findViewById(R.id.category_page_article_gridview);
        articlePreviewAdapter = new ArticlePreviewAdapter();
        articlePreviewGridView.setAdapter(articlePreviewAdapter);

        setPreviewArticles();

        articlePreviewGridView.setClickable(true);

        articlePreviewGridView.setOnItemClickListener((parent, view, position, id) -> {
            Log.d("CLICK", "Item Clicked");
            ArticlePreview clicked_article = (ArticlePreview) articlePreviewAdapter.getItem(position);
            Intent intent = new Intent(CategoryView.this, ArticleInfoView.class);
            intent.putExtra("userinfo", user.UsertoString());
            intent.putExtra("article_id", clicked_article.getArticle_id());
            startActivity(intent);
        });

        categoryBtnGridView = (GridView) findViewById(R.id.category_page_category_gridview);
        categoryBtnAdapter = new CategoryBtnAdapter();
        int category_size;
        if (article_type.equals("fresh")) {
            for (int i = 0; i < freshCategoryString.length; i++) {
                categoryBtnAdapter.addItem(new CategoryBtn(freshCategoryString[i]));
            }
            category_size = freshCategoryString.length;
        } else {
            for (int i = 0; i < deliveryCategoryString.length; i++) {
                categoryBtnAdapter.addItem(new CategoryBtn(deliveryCategoryString[i]));
            }
            category_size = deliveryCategoryString.length;
        }
        categoryBtnGridView.setNumColumns(category_size);

        categoryBtnGridView.setAdapter(categoryBtnAdapter);

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
                Intent intent = new Intent(CategoryView.this, PostArticle.class);
                intent.putExtra("userinfo", user.UsertoString());
                startActivity(intent);
            }
        });

        communication_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryView.this, MessageGroupView.class);
                intent.putExtra("userinfo", user.UsertoString());
                startActivity(intent);
            }
        });

        mybanhada_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryView.this, ProfileView.class);
                intent.putExtra("userinfo", user.UsertoString());
            }
        });
    }

    public void setPreviewArticles() {
        articlePreviewArrayList = getArticlesManager.query_with_category(article_type, article_category, previewArticleStartIdx, previewArticleStartIdx + 19);
        previewArticleStartIdx += 20;

        for (int i = 0; i < articlePreviewArrayList.size(); i++) {
            articlePreviewAdapter.addItem(articlePreviewArrayList.get(i));
        }

        articlePreviewAdapter.notifyDataSetChanged();
    }
}
