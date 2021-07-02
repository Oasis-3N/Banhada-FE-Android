package com.example.oasis_hackathon_app.methods.account.model;

public class User {
    String id;
    String email;
    String username;
    String address;
    String nickname;

    public User(String userinfo) {
        String[] tokenized_userinfo = userinfo.split("/");
        id = tokenized_userinfo[0];
        email = tokenized_userinfo[1];
        username = tokenized_userinfo[2];
        address = tokenized_userinfo[3];
        nickname = tokenized_userinfo[4];
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getNickname() {
        return nickname;
    }

    public String UsertoString() {
        return id+"/"+email+"/"+username+"/"+address+"/"+nickname;
    }
}
