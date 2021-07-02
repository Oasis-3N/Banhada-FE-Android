package com.example.oasis_hackathon_app.endpoints;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oasis_hackathon_app.R;
import com.example.oasis_hackathon_app.methods.account.request.GetUserData;
import com.example.oasis_hackathon_app.methods.account.model.User;
import com.example.oasis_hackathon_app.methods.article.model.Article;
import com.example.oasis_hackathon_app.methods.article.request.PostArticle;

import org.json.JSONObject;

public class PostArticleView extends AppCompatActivity {
    GetUserData getUserData;
    User user;

    Article newArticle;
    PostArticle postArticleManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_article_page);

        Intent nowintent = getIntent();
        user = new User(nowintent.getStringExtra("userinfo"));

        postArticleManager = new PostArticle();


    }
}
