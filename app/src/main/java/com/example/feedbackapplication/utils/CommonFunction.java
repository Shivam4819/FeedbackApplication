package com.example.feedbackapplication.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CommonFunction {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String sendDataToServer(String POST_URL, String data){

        try {
            URL url = new URL(POST_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStream outputStream = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            osw.write(data);
            osw.flush();
            osw.close();
            int responseCode=conn.getResponseCode();
            if (responseCode==HttpURLConnection.HTTP_OK){
                return readResponse(conn);
            }
            return null;
         } catch (Exception e) {
            System.out.println("conncetion prob---"+e);
            return null;
        }

    }
    public String readResponse(HttpURLConnection conn) {
        try {
            System.out.println("in response");
            StringBuilder output = new StringBuilder();
            InputStream inputStream = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s = "";
            while ((s = bufferedReader.readLine()) != null)
                output.append(s);
            return output.toString();

        } catch (Exception e) {
            System.out.println("in res---"+e);
        }
        return null;
    }
}