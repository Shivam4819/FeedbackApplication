package com.example.feedbackapplication;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


class GetData extends AsyncTask<String,Void,Void> {


    String POST_URL="http://192.168.29.205:8080/JasonServlet/display";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(String... strings) {
        try {

            System.out.println("in getdata class");
            System.out.println(Arrays.toString(strings));

            URL url=new URL(POST_URL);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStream outputStream = conn.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            osw.write(strings[0]);
            osw.flush();
            osw.close();
            int responsecode=conn.getResponseCode();
            System.out.println("response---"+responsecode);

            if (responsecode==HttpURLConnection.HTTP_OK){
                System.out.println("in response" );

                StringBuffer output=new StringBuffer();
                InputStream inputStream = conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String s = "";
                while ((s = bufferedReader.readLine()) != null)
                    output.append(s);

                JSONObject jsonObject =new JSONObject(output.toString());
                String res= (String)jsonObject.get("response");
                System.out.println("response--"+res);


            }
        }catch (Exception e){
            System.out.println("connection problem-----"+e);
        }
    return null;
    }
}
