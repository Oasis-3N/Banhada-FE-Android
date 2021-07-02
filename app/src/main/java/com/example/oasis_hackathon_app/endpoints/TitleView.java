package com.example.oasis_hackathon_app.endpoints;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oasis_hackathon_app.R;

public class TitleView extends AppCompatActivity {
    Button gotoLogin_btn;
    Button gotoRegister_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_page);

        gotoLogin_btn = (Button) findViewById(R.id.title_page_gotoLogin_btn);
        gotoRegister_btn = (Button) findViewById(R.id.title_page_gotoRegister_btn);

        gotoLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TitleView.this, LoginView.class);
                startActivity(intent);
            }
        });

        gotoRegister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TitleView.this, RegisterView.class);
                startActivity(intent);
            }
        });
    }
}
