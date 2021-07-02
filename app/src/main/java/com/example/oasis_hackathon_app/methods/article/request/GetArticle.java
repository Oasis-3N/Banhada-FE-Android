package com.example.oasis_hackathon_app.methods.article.request;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.oasis_hackathon_app.methods.Query_manager;
import com.example.oasis_hackathon_app.methods.article.model.Article;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Base64;

public class GetArticle {
    Query_manager query_manager;

    Article result_article;

    String get_article_url = "http://52.79.54.196:2500/article/get_article";
    String new_get_article_url;
    Request_Thread request_thread;

    public GetArticle() {
        request_thread = new Request_Thread();
        query_manager = new Query_manager();
    }

    public Article query(int article_id) {
        new_get_article_url = get_article_url + "/" + Integer.toString(article_id);

        request_thread.start();

        try {
            request_thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result_article;
    }

    private class Request_Thread extends Thread {
        public void run() {
            JSONObject result;
            try {
                result = query_manager.request_obj(new_get_article_url, "GET", null);
                result_article = new Article(result);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
}
