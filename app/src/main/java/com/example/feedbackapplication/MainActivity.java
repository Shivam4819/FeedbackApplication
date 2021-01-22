package com.example.feedbackapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    EditText studentId;
    EditText emailId;
    EditText coursename;
    EditText instructorname;
    Button submit;
    long student=0;
    JSONObject jsonObject=new JSONObject();
    Map obj=new HashMap();

     String  email=null,course=null,instructor=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentId=findViewById(R.id.Student_editbox);
        emailId=findViewById(R.id.Email_editbox);
        coursename=findViewById(R.id.Course_editbox);
        instructorname=findViewById(R.id.Instructor_editbox);
        submit=findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                student=Long.parseLong(studentId.getText().toString());
                email=emailId.getText().toString();
                course=coursename.getText().toString();
                instructor=instructorname.getText().toString();
                System.out.println("in button");


                try {
                    jsonObject.put("k1",student);
                    jsonObject.put("k2",email);
                    jsonObject.put("k3",course);
                    jsonObject.put("k4",instructor);
                    GetData get=new GetData();
                    String jsonString=jsonObject.toString();
                    get.execute(jsonString);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
