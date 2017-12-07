package com.example.ishupreetsingh.smartenergy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Created by Ishupreet Singh on 26-11-2017.
 */

public class Utils {
    public static String global_key = "";
    private static final String USER_AGENT = "Android/5.0";
    public static void sendGet(String url)  {
    try {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        if(url.contains("getKey")){
            global_key= response.toString();
        }
    }
    catch (Exception e){
        e.printStackTrace();
    }

    }
    public static void threadGET(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sendGet(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
