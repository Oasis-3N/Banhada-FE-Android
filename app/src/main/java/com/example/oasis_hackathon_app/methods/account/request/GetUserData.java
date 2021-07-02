package com.example.oasis_hackathon_app.methods.account.request;

import com.example.oasis_hackathon_app.methods.Query_manager;
import com.example.oasis_hackathon_app.methods.account.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class GetUserData {
    Query_manager query_manager;

    String userinfo = "";

    String login_url = "http://52.79.54.196:2500/account/user_data";
    String new_login_url;
    Request_Thread request_thread;

    public GetUserData() {
        query_manager = new Query_manager();
        request_thread = new Request_Thread();
    }

    public User GetUser(String id) {
        new_login_url = login_url + "?id=" + id;

        request_thread.start();

        try {
            request_thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new User(userinfo);
    }

    private class Request_Thread extends Thread {
        public void run() {
            JSONObject result;
            try {
                result = query_manager.request_obj(new_login_url, "GET", null);
                userinfo += result.getString("id") + '/';
                userinfo += result.getString("email") + '/';
                userinfo += result.getString("username") + '/';
                userinfo += result.getString("address") + '/';
                userinfo += result.getString("nickname");
            } catch (IOException | JSONException | IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
}
