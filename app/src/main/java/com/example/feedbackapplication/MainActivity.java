package com.example.feedbackapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.feedbackapplication.activity.FeedbackFormActivity;
import com.example.feedbackapplication.api.FeedbackDataApi;
import com.example.feedbackapplication.request.FeedbackDataReq;

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
        user=username.getText().toString();
        pass=password.getText().toString();

        System.out.println("username--"+user);
        System.out.println("passs--"+pass);

        Intent intent=new Intent(MainActivity.this, FeedbackFormActivity.class);
        startActivity(intent);
        finish();
    }

}
