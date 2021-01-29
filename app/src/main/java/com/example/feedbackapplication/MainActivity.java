package com.example.feedbackapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.feedbackapplication.activity.FeedbackFormActivity;
import com.example.feedbackapplication.activity.RegisterActivity;
import com.example.feedbackapplication.api.LoginApi;
import com.example.feedbackapplication.request.LoginReq;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    String  user=null,pass=null;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.Username_editbox);
        password=findViewById(R.id.Password_editbox);
        login=findViewById(R.id.submit_button);
    }
    public void sendMessage(View view){

        LoginReq loginReq=new LoginReq();
        loginReq.setUsername(username.getText().toString());
        loginReq.setPassword(password.getText().toString());

        LoginApi loginApi=new LoginApi();
        loginApi.execute(loginReq);

         Intent intent=new Intent(MainActivity.this, FeedbackFormActivity.class);
         startActivity(intent);
         finish();
    }

    public void registerUser(View view){
        Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

}
