package com.example.feedbackapplication.api;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.feedbackapplication.utils.CommonFunction;
import com.example.feedbackapplication.response.FeedbackDataRes;
import com.google.gson.Gson;

public class FeedbackDataApi extends AsyncTask<Object,Void,Void> {
    String POST_URL = "http://192.168.29.205:8080/JasonServlet/display";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(Object... objects) {
        try {

            String res = new Gson().toJson(objects[0]);

            CommonFunction commonFunction = new CommonFunction();
            String responseResult = commonFunction.sendDataToServer(POST_URL,res);

            System.out.println("responseresult-"+responseResult);
            Gson gson=new Gson();
            FeedbackDataRes dataRes = gson.fromJson(responseResult,FeedbackDataRes.class);
            System.out.println("data--"+dataRes.getResponseString());
        }catch (Exception e){
            System.out.println("no connection--"+e);
        }
        return null;
    }
}
