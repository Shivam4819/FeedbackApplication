package com.example.feedbackapplication.responseApi;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class ReadResponse {
   public String responseData(HttpURLConnection conn){
       try {
           StringBuffer output = new StringBuffer();
           InputStream inputStream = conn.getInputStream();
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
           String s = "";
           while ((s = bufferedReader.readLine()) != null)
               output.append(s);

           JSONObject jsonObject = new JSONObject(output.toString());
           String res = (String) jsonObject.get("response");
           return res;
       }catch (Exception e){
           System.out.println("unable to read response--"+e);
       }
       return null;
   }
}
