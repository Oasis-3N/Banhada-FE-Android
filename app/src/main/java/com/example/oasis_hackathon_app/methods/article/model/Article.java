package com.example.oasis_hackathon_app.methods.article.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Base64;

public class Article {
    String address;
    String article_category;
    String article_type;
    String content;
    int cost;
    int article_id;
    String image;
    int max_num;
    String reg_date;
    int remain_time;
    String submitter_nickname;
    String title;

    public Article (JSONObject infoObject) {
        try {
            this.address = infoObject.getString("address");
            this.article_category = infoObject.getString("article_category");
            this.article_type = infoObject.getString("article_type");
            this.content = infoObject.getString("content");
            this.cost = infoObject.getInt("cost");
            this.article_id = infoObject.getInt("id");
            this.image = infoObject.getString("image");
            this.max_num = infoObject.getInt("max_num");
            this.reg_date = infoObject.getString("reg_date");
            this.remain_time = infoObject.getInt("remain_time");
            this.submitter_nickname = infoObject.getString("submitter");
            this.title = infoObject.getString("title");
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    public String getAddress() {
        return address;
    }

    public int getArticle_id() {
        return article_id;
    }

    public int getCost() {
        return cost;
    }

    public int getMax_num() {
        return max_num;
    }

    public int getRemain_time() {
        return remain_time;
    }

    public String getArticle_category() {
        return article_category;
    }

    public String getArticle_type() {
        return article_type;
    }

    public String getContent() {
        return content;
    }

    public String getImage() {
        return image;
    }

    public String getReg_date() {
        return reg_date;
    }

    public String getSubmitter_nickname() {
        return submitter_nickname;
    }

    public String getTitle() {
        return title;
    }
}
