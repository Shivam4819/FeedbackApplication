package com.example.feedbackapplication.requestApi;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.feedbackapplication.responseApi.ReadResponse;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SendDataToServlet extends AsyncTask<String,Void,Void> {

    String POST_URL = "http://192.168.29.205:8080/JasonServlet/display";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(String...strings) {
        try {

           // System.out.println(Arrays.toString(strings));

            URL url = new URL(POST_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStream outputStream = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            osw.write(strings[0]);
            osw.flush();
            osw.close();
            int responsecode = conn.getResponseCode();
            System.out.println("response code-" + responsecode);

            if (responsecode==HttpURLConnection.HTTP_OK){
                ReadResponse readResponse=new ReadResponse();
                String res= readResponse.responseData(conn);
                System.out.println("response is--" + res);
            }

        }catch (Exception e){
            System.out.println("connection problem----"+e);
        }
        return null;
    }
}