package com.example.feedbackapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.feedbackapplication.R;
import com.example.feedbackapplication.api.FeedbackDataApi;
import com.example.feedbackapplication.request.FeedbackDataReq;

public class FeedbackFormActivity extends AppCompatActivity {

    EditText studentId;
    EditText emailId;
    EditText coursename;
    EditText instructorname;
    Button submit;
    long student=0;
    String  email=null,course=null,instructor=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_form);

        studentId=findViewById(R.id.Student_editbox);
        emailId=findViewById(R.id.Email_editbox);
        coursename=findViewById(R.id.Course_editbox);
        instructorname=findViewById(R.id.Instructor_editbox);
        submit=findViewById(R.id.submit_button);
    }
    public void sendMessage(View view){
        student=Long.parseLong(studentId.getText().toString());
        email=emailId.getText().toString();
        course=coursename.getText().toString();
        instructor=instructorname.getText().toString();

        try {
            FeedbackDataReq req=new FeedbackDataReq();
            req.setId(student);
            req.setEmail(email);
            req.setCoursename(course);
            req.setInstructorname(instructor);

            FeedbackDataApi dataApi=new FeedbackDataApi();
            dataApi.execute(req);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}