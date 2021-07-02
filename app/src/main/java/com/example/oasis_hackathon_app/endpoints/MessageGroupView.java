package com.example.oasis_hackathon_app.endpoints;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oasis_hackathon_app.R;
import com.example.oasis_hackathon_app.methods.account.model.User;
import com.example.oasis_hackathon_app.methods.article.request.PostArticle;
import com.example.oasis_hackathon_app.methods.message.request.GetMessageGroup;
import com.example.oasis_hackathon_app.methods.message.model.MessageGroup;
import com.example.oasis_hackathon_app.methods.message.adapter.MessageGroupAdapter;

import org.json.JSONArray;
import org.json.JSONException;

public class MessageGroupView extends AppCompatActivity {
    User user;

    GetMessageGroup getMessageGroupManager;

    JSONArray messageGroupArrayList;

    ListView messageGroupListView;
    MessageGroupAdapter messageGroupAdapter;

    LinearLayout home_btn;
    LinearLayout banhada_btn;
    LinearLayout mybanhada_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_group_page);

        Intent nowintent = getIntent();
        user = new User(nowintent.getStringExtra("userinfo"));

        getMessageGroupManager = new GetMessageGroup(user.getId(), user.UsertoString());

        messageGroupListView = (ListView) findViewById(R.id.communication_page_message_group_listview);
        messageGroupAdapter = new MessageGroupAdapter();

        messageGroupArrayList = getMessageGroupManager.query();

        for (int i = 0; i < messageGroupArrayList.length(); i++) {
            try {
                messageGroupAdapter.addItem(new MessageGroup(messageGroupArrayList.getJSONObject(i), user.UsertoString()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        messageGroupListView.setAdapter(messageGroupAdapter);

        messageGroupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessageGroup clicked_group = (MessageGroup) messageGroupAdapter.getItem(position);
                Intent intent = new Intent(MessageGroupView.this, MessageView.class);
                intent.putExtra("userinfo", user.UsertoString());
                intent.putExtra("message_group_id", clicked_group.getGroupId());
                startActivity(intent);
            }
        });


        home_btn = (LinearLayout) findViewById(R.id.bottom_bar_home_btn);
        banhada_btn = (LinearLayout) findViewById(R.id.bottom_bar_banhada_btn);
        mybanhada_btn = (LinearLayout) findViewById(R.id.bottom_bar_mybanhada_btn);

        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageGroupView.this, HomeView.class);
                intent.putExtra("id", user.getId());
                startActivity(intent);
            }
        });

        banhada_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageGroupView.this, PostArticleView.class);
                intent.putExtra("userinfo", user.UsertoString());
                startActivity(intent);
            }
        });

        mybanhada_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageGroupView.this, ProfileView.class);
                intent.putExtra("userinfo", user.UsertoString());
                startActivity(intent);
            }
        });
    }
}
