package com.example.feedbackapplication.api;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.feedbackapplication.response.FeedbackDataRes;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FeedbackDataApi extends AsyncTask<Object,Void,Void> {
    String POST_URL = "http://192.168.29.205:8080/JasonServlet/display";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(Object... objects) {
        try {


            String res=new Gson().toJson(objects[0]);

            System.out.println("data -"+res);

            URL url = new URL(POST_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStream outputStream = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            osw.write(res);
            osw.flush();
            osw.close();
            int responsecode = conn.getResponseCode();
            System.out.println("response code-" + responsecode);

            if (responsecode==HttpURLConnection.HTTP_OK){
                String res1= response(conn);
                System.out.println("response is--" + res1);
            }
        }catch (Exception e){
            System.out.println("no connection--"+e);
        }
        return null;
    }

    public String response(HttpURLConnection conn) {
        try {
            System.out.println("in response");
            StringBuilder output = new StringBuilder();
            InputStream inputStream = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s = "";
            while ((s = bufferedReader.readLine()) != null)
                output.append(s);

            Gson gson=new Gson();
            FeedbackDataRes res=gson.fromJson(output.toString(),FeedbackDataRes.class);
            return res.getResponseString();
        }catch (Exception e){
            System.out.println("unable to read response--"+e);
        }

        return null;
    }



}
