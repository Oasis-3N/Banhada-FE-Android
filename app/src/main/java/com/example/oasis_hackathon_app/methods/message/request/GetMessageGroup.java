package com.example.oasis_hackathon_app.methods.message.request;

import com.example.oasis_hackathon_app.methods.Query_manager;
import com.example.oasis_hackathon_app.methods.message.model.MessageGroup;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class GetMessageGroup {
    Query_manager query_manager;

    String id;
    String userinfo;
    ArrayList<MessageGroup> result_list;
    JSONArray result;

    String get_articles_url = "http://52.79.54.196:2500/message/get_message_groups";
    Request_Thread request_thread;
    String new_get_articles_url;

    public GetMessageGroup(String id, String userinfo) {
        this.id = id;
        this.userinfo = userinfo;
        request_thread = new Request_Thread();
        query_manager = new Query_manager();
    }


    public JSONArray query() {
        result_list = new ArrayList<MessageGroup>();

        new_get_articles_url = get_articles_url + "?id=" + id;

        request_thread.start();

        try {
            request_thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    private class Request_Thread extends Thread {
        public void run() {
            try {
                result = query_manager.request_arr(new_get_articles_url, "GET", null);

                for (int i = 0; i < result.length(); i++) {
                    result_list.add(new MessageGroup(result.getJSONObject(i), userinfo));
                }

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
