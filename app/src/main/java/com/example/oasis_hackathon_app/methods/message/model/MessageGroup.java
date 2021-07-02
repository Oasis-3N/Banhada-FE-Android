package com.example.oasis_hackathon_app.methods.message.model;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

public class MessageGroup {
    String counterImage;
    String counterNickname;
    String remainTime;
    String preview;
    String productImage;
    int groupId;

    String userinfo;

    public MessageGroup(JSONObject infoObject, String userinfo) {
        this.userinfo = userinfo;

        try {
            this.counterImage = "no image";
            this.counterNickname = infoObject.getString("counter_nickname");
            this.remainTime = "0";
            this.preview = infoObject.getString("preview");
            this.productImage = infoObject.getString("image");
            this.groupId = infoObject.getInt("group_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getGroupId() {
        return groupId;
    }

    public String getCounterImage() {
        return counterImage;
    }

    public String getCounterNickname() {
        return counterNickname;
    }

    public String getPreview() {
        return preview;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getRemainTime() {
        return remainTime;
    }

    public String getUserinfo() {
        return userinfo;
    }
}
