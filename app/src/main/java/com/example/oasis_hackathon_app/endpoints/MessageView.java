package com.example.oasis_hackathon_app.endpoints;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oasis_hackathon_app.R;
import com.example.oasis_hackathon_app.methods.account.model.User;
import com.example.oasis_hackathon_app.methods.message.request.GetMessage;
import com.example.oasis_hackathon_app.methods.message.model.Message;
import com.example.oasis_hackathon_app.methods.message.adapter.MessageAdapter;
import com.example.oasis_hackathon_app.methods.message.request.SendMessage;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MessageView extends AppCompatActivity {
    User user;
    int article_id;
    int message_group_id;

    GetMessage getMessageManager;
    SendMessage sendMessageManager;

    ArrayList<Message> messageArrayList;

    ListView messageListView;
    MessageAdapter messageAdapter;

    LinearLayout backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_page);

        Intent nowintent = getIntent();
        user = new User(nowintent.getStringExtra("userinfo"));
        article_id = nowintent.getIntExtra("article_id", -1);
        message_group_id = nowintent.getIntExtra("message_group_id", -1);

        getMessageManager = new GetMessage(message_group_id);
        sendMessageManager = new SendMessage();

        messageListView = (ListView) findViewById(R.id.message_view_message_listview);
        messageAdapter = new MessageAdapter(user.getNickname());

        messageArrayList = getMessageManager.query();
        messageListView.setAdapter(messageAdapter);

        updateMessages();
    }

    public void updateMessages() {
        for (int i = 0; i < messageArrayList.size(); i++) {
            messageAdapter.addItem(messageArrayList.get(i));
        }
        messageAdapter.notifyDataSetChanged();
    }
}
