package com.example.feedbackapplication.api;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.feedbackapplication.utils.CommonFunction;
import com.example.feedbackapplication.response.LoginRes;
import com.google.gson.Gson;

public class LoginApi extends AsyncTask<Object,Void,Void> {
    String POST_URL = "http://192.168.29.205:8080/loginApi";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(Object... objects) {

        try {
            String requestString = new Gson().toJson(objects[0]);

            CommonFunction commonFunction = new CommonFunction();
            String result = commonFunction.sendDataToServer(POST_URL, requestString);

            Gson gson = new Gson();
            LoginRes loginRes = gson.fromJson(result, LoginRes.class);
            System.out.println("response from server--" + loginRes.getResponseString());

        }catch (Exception e){
            System.out.println("problem in login api--"+e);
        }

        return null;
    }
}
