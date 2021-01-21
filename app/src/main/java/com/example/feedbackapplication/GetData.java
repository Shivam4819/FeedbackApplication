package com.example.feedbackapplication;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONObject;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


class GetData extends AsyncTask<Void,Void,Void> {


    String POST_URL="http://192.168.29.119:8080/FeedbackAppServer/display";
    JSONObject jsonObject=new JSONObject();
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(Void... voids) {
        try {
          // jsonObject.put("id",student);
          //  jsonObject.put("email",email);
          //  jsonObject.put("coursename",course);
          //  jsonObject.put("instructorname",instructor);
            System.out.println("in getdata class");


            URL url=new URL(POST_URL);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStream outputStream = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(outputStream, StandardCharsets.UTF_16);
            osw.write(jsonObject.toString());
            osw.flush();
            osw.close();
            int responsecode=conn.getResponseCode();
            System.out.println("response---"+responsecode);

        }catch (Exception e){
            System.out.println("connection problem-----"+e);
        }
    return null;
    }
}
