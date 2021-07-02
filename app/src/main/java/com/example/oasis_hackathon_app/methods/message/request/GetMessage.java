package com.example.oasis_hackathon_app.methods.message.request;

import com.example.oasis_hackathon_app.methods.Query_manager;
import com.example.oasis_hackathon_app.methods.message.model.Message;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class GetMessage {
    Query_manager query_manager;

    int message_group_id;
    ArrayList<Message> result_list;

    String get_articles_url = "http://52.79.54.196:2500/message/get_messages";
    Request_Thread request_thread;
    String new_get_articles_url;

    public GetMessage(int message_group_id) {
        this.message_group_id = message_group_id;
        request_thread = new Request_Thread();
        query_manager = new Query_manager();
    }


    public ArrayList<Message> query() {
        result_list = new ArrayList<Message>();

        new_get_articles_url = get_articles_url + "?group_id=" + Integer.toString(message_group_id);

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
                    result_list.add(new Message(result.getJSONObject(i)));
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
