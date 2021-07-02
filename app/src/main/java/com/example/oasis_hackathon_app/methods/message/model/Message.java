package com.example.oasis_hackathon_app.methods.message.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Message {
    String nickname;
    String message;
    String image;
    String reg_date;
    String type;

    public Message(JSONObject infoObject) {
        try {
            this.nickname = infoObject.getString("submitter");
            this.message = infoObject.getString("message");
            this.image = infoObject.getString("image");
            this.reg_date = infoObject.getString("reg_date");
            if (message.equals("")){
                type = "image";
            }
            else {
                type = "message";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getNickname() {
        return nickname;
    }

    public String getMessage() {
        return message;
    }

    public String getImage() {
        return image;
    }

    public String getReg_date() {
        return reg_date;
    }

    public String getType() {
        return type;
    }
}
