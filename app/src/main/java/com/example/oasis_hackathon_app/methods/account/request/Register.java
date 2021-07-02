package com.example.oasis_hackathon_app.methods.account.request;

import com.example.oasis_hackathon_app.methods.Query_manager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Register {
    Query_manager query_manager;

    String id;
    String password;

    String address;

    String login_url = "http://52.79.54.196:2500/account/register";
    Request_Thread request_thread;
    RequestBody body;
    Boolean if_register_success = false;

    public Register() {
        query_manager = new Query_manager();
        request_thread = new Request_Thread();
    }

    public void Set_data(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public Boolean Try_login() {
        MediaType mediaType = MediaType.parse("application/json");
        body = RequestBody.create(mediaType, "{\r\n\"id\": \"" + id +
                "\",\r\n\"password\": \"" + password + "\"\r\n}");

        request_thread.start();

        try {
            request_thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return if_register_success;
    }

    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    private class Request_Thread extends Thread {
        public void run() {
            try {
                JSONObject result = query_manager.request_obj(login_url, "POST", body);
                address = result.getString("address");
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
