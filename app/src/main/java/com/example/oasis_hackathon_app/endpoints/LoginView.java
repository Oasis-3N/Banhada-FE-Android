package com.example.oasis_hackathon_app.endpoints;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oasis_hackathon_app.R;
import com.example.oasis_hackathon_app.methods.account.request.Login;

public class LoginView extends AppCompatActivity {
    Login login_manager;

    Button back_btn;
    Button login_btn;

    EditText id_edittext;
    EditText password_edittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        login_manager = new Login();

        id_edittext = (EditText) findViewById(R.id.login_page_id_edittext);
        password_edittext = (EditText) findViewById(R.id.login_page_password_edittext);

        back_btn = (Button) findViewById(R.id.login_page_back_button);
        login_btn = (Button) findViewById(R.id.login_page_login_button);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = id_edittext.getText().toString();
                String password = password_edittext.getText().toString();

                if (id.length() == 0 || password.length() == 0) {
                    // 미입력 오류 Toast 띄우기
                    return;
                }

                login_manager.Set_data(id, password);
                if (login_manager.Try_login()) {
                    Intent intent = new Intent(LoginView.this, HomeView.class);
                    intent.putExtra("id", id);

                    startActivity(intent);
                } else {
                    // 로그인 실패 Toast 띄우기
                }
            }
        });

    }
}
