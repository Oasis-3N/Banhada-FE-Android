package com.example.oasis_hackathon_app.methods.article.request;

import com.example.oasis_hackathon_app.methods.Query_manager;
import com.example.oasis_hackathon_app.methods.article.model.ArticlePreview;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class GetArticles {
    Query_manager query_manager;

    String address;
    String userinfo;
    ArrayList<ArticlePreview> result_list;

    String get_articles_url = "http://52.79.54.196:2500/article/get_articles";
    Request_Thread request_thread;
    String new_get_articles_url;

    public GetArticles(String userinfo) {
        this.userinfo = userinfo;
        request_thread = new Request_Thread();
        query_manager = new Query_manager();
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public ArrayList<ArticlePreview> query_with_no_category(int start_idx, int end_idx) {
        result_list = new ArrayList<ArticlePreview>();

        new_get_articles_url = get_articles_url + "?address="  + address +
                "&start_idx=" + Integer.toString(start_idx) +
                "&end_idx=" + Integer.toString(end_idx);

        request_thread.start();

        try {
            request_thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result_list;
    }

    public ArrayList<ArticlePreview> query_with_category(String article_type, String article_category, int start_idx, int end_idx) {
        result_list = new ArrayList<ArticlePreview>();

        new_get_articles_url = get_articles_url + "?address="  + address +
                "&article_type=" + article_type +
                "&article_category=" + article_category +
                "&start_idx=" + Integer.toString(start_idx) +
                "&end_idx=" + Integer.toString(end_idx);

        request_thread.start();

        try {
            request_thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result_list;
    }

    private class Request_Thread extends Thread {
        public void run() {
            JSONArray result;
            try {
                result = query_manager.request_arr(new_get_articles_url, "GET", null);
                for (int i = 0; i < result.length(); i++) {
                    result_list.add(new ArticlePreview(result.getJSONObject(i), userinfo));
                }

            } catch (IOException | JSONException | NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
