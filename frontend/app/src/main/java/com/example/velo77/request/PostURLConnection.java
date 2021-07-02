package com.example.velo77.request;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PostURLConnection {

    public static String startHttpRequest(String urlString, JSONObject inputs) throws IOException {
        try {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");//important
            httpURLConnection.connect();
            //write data to the server using BufferedWriter
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(httpURLConnection
                    .getOutputStream()));
            writer.write(inputs.toString());
            writer.flush();
            //get response code and check if valid (HTTP OK)
            int responseCode = httpURLConnection.getResponseCode();

            if(responseCode == 400) return "400";
            if(responseCode == 404) return "404";
            if(responseCode == 500) return "500";
            if(responseCode == 409) return "409";

            if(responseCode == 200){//if valid, read result from server
                BufferedReader reader = new BufferedReader(new InputStreamReader
                        (httpURLConnection.getInputStream()));
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while((line = reader.readLine()) != null){
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();
            }
            if(  responseCode == 201 ){
                return "201";
            }

        } catch (MalformedURLException exception){

        } catch (IOException exception) {

        } catch (Exception e){

        }
        return "500";

    }

}