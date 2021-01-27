package com.example.feedbackapplication.api;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.feedbackapplication.response.RegisterRes;
import com.example.feedbackapplication.utils.CommonFunction;
import com.google.gson.Gson;

public class RegisterApi extends AsyncTask<Object,Void,Void> {
    String POST_URL = "http://192.168.29.205:8080/JasonServlet/registerApi";
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(Object... objects) {
        try {
            String requestString = new Gson().toJson(objects[0]);

            CommonFunction commonFunction = new CommonFunction();
            String result = commonFunction.sendDataToServer(POST_URL, requestString);

            Gson gson = new Gson();
            RegisterRes registerRes = gson.fromJson(result, RegisterRes.class);
            System.out.println("res from registration--" + registerRes.getRegistereResponse());
        }catch (Exception e){
            System.out.println("problem-"+e);
        }
        return null;
    }
}
