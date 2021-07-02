package com.example.oasis_hackathon_app.methods.account.request;

import android.util.Log;

import com.example.oasis_hackathon_app.methods.Query_manager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Login {
    Query_manager query_manager;

    String id;
    String password;

    String login_url = "http://52.79.54.196:2500/account/login";
    Request_Thread request_thread;
    Boolean if_login_success = false;
    RequestBody body;

    public Login() {
        query_manager = new Query_manager();
        request_thread = new Request_Thread();
    }

    public void Set_data(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public Boolean Try_login() {
        MediaType mediaType = MediaType.parse("application/json");
        body = RequestBody.create(mediaType, "{\r\n\"id\": \"" + id + "\",\r\n\"password\": \""+ password +"\"\r\n}");

        request_thread.start();

        try {
            request_thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return if_login_success;
    }

    private class Request_Thread extends Thread {
        public void run() {
            JSONObject result;
            try {
                result = query_manager.request_obj(login_url, "POST", body);
                if (result.getString("registered").equals("True")) {
                    if_login_success = true;
                } else {
                    if_login_success = false;
                }
            } catch (IOException | JSONException | IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
}
