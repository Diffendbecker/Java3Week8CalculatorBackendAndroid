package com.akomlev.calculatorweek7.calculatorweek7;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestService  {

    //Shouldn't ever use it in production. According to my research.
    // The best way - to use Tasks, but my Android and Java knowledge not so deep.
    // Thread is a way to use also.
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    public String SendRestRequest(String value1, String value2, String action) {
        //Shouldn't ever use it in production. According to my research.
        // The best way - to use Tasks, but my Android and Java knowledge not so deep.
        // Thread is a way to use also.
        StrictMode.setThreadPolicy(policy);
        String response = "0";
        try {
            URL url = new URL("http://10.0.2.2:8080/test/rest/calculate/" + action + "/" + value1 + "/" + value2);
            HttpURLConnection  urlc = (HttpURLConnection) url.openConnection();

            urlc.setRequestMethod("GET");
            urlc.setRequestProperty("User-Agent", "12345");
            urlc.setRequestProperty("Accept-Charset", "UTF-8");
            urlc.setRequestProperty("Content-Type", "application/json");

            int responseCode = urlc.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(urlc
                    .getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                response += line;
            }
            br.close();
        }
        catch (Exception ex)
        {
            return response;
        }
        return response;
    }
}
