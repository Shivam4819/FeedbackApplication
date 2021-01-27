package com.example.feedbackapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.feedbackapplication.MainActivity;
import com.example.feedbackapplication.R;
import com.example.feedbackapplication.api.RegisterApi;
import com.example.feedbackapplication.request.RegisterReq;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText fullname;
    Button register;
    String  user=null,pass=null,name=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullname=findViewById(R.id.Fullname_editbox);
        username=findViewById(R.id.Username_editbox);
        password=findViewById(R.id.Password_editbox);
        register=findViewById(R.id.register_button);

    }
    public void registerUser(View view){
        user=username.getText().toString();
        pass=password.getText().toString();
        name=fullname.getText().toString();

        RegisterReq registerReq=new RegisterReq();
        registerReq.setFullname(name);
        registerReq.setUsername(user);
        registerReq.setPassword(pass);

        RegisterApi registerApi=new RegisterApi();
        registerApi.execute(registerReq);

         Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
         startActivity(intent);
         finish();

    }
}