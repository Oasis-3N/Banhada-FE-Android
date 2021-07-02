package com.example.oasis_hackathon_app.methods.article.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ArticlePreview {
    int cost;
    int article_id;
    String image;
    String reg_date;
    String submitter_nickname;
    String title;

    String userinfo;

    public ArticlePreview(JSONObject infoObject, String userinfo) {
        this.userinfo = userinfo;

        try {
            this.cost = infoObject.getInt("cost");
            this.article_id = infoObject.getInt("id");
            this.image = infoObject.getString("image");
            this.reg_date = infoObject.getString("reg_date");
            this.submitter_nickname = infoObject.getString("submitter");
            this.title = infoObject.getString("title");
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getSubmitter_nickname() {
        return submitter_nickname;
    }

    public String getImage() {
        return image;
    }

    public int getCost() {
        return cost;
    }

    public int getArticle_id() {
        return article_id;
    }

    public String getRemainTime() {
        return reg_date;
    }

    public String getUserinfo() {
        return userinfo;
    }
}
