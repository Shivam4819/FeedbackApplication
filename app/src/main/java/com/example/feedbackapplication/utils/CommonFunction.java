package com.example.feedbackapplication.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CommonFunction {

    ObjectMapper mapper = new ObjectMapper();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String sendDataToServer(String POST_URL, String data) {

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
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readResponse(conn);
            }
            return null;
        } catch (Exception e) {
            System.out.println("connection prob---" + e);
            return null;
        }

    }

    public String readResponse(HttpURLConnection conn) {
        try {
            StringBuilder output = new StringBuilder();
            InputStream inputStream = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s = "";
            while ((s = bufferedReader.readLine()) != null)
                output.append(s);
            return output.toString();

        } catch (Exception e) {
            System.out.println("problem in common function readResponse---" + e);
        }
        return null;
    }

    public <T> T convertJsonToJava(String jsonString, Class<T> obj) throws Exception {

        T result = null;
        result = mapper.readValue(jsonString, obj);
        return result;
    }

    public String convertJavaToJson(Object object) throws Exception {
        String jsonResult = null;
        jsonResult = mapper.writeValueAsString(object);
        return jsonResult;
    }
}
