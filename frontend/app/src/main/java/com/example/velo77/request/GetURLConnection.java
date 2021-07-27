package com.example.velo77.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetURLConnection {

    public static String startHttpRequest(String urlString){

        StringBuilder stringBuilder = new StringBuilder();

        try {
            // 1 - Declare a URL Connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 2 - Open InputStream to connection
            conn.connect();
            InputStream in = conn.getInputStream();
            // 3 - Download and decode the string response

            int responseCode = conn.getResponseCode();

            if(responseCode == 410) return "410";
            if(responseCode == 400) return "400";
            if(responseCode == 404) return "404";
            if(responseCode == 500) return "500";
            if(responseCode == 409) return "409";


            if(responseCode == 200) {//if valid, read result from server

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            }

        } catch (MalformedURLException exception){

        } catch (IOException exception) {

        } catch (Exception e){

        }

        return "505";
    }

}