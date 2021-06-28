package com.example.velo77.request;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncTaskPost extends AsyncTask<String, String, String> {

    private String url;
    private String objSend;
    private JSONObject result = null;

    public AsyncTaskPost(String objSend){

        this.objSend = objSend;

    }


    @Override
    protected String doInBackground(String... params){
        try{
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");//important
            httpURLConnection.addRequestProperty("Accept", "application/json");
            httpURLConnection.addRequestProperty("Content-Type", "application/json");
            httpURLConnection.connect();
            //write data to the server using BufferedWriter
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(httpURLConnection
                    .getOutputStream()));
            writer.write(objSend);
            writer.flush();
            //get response code and check if valid (HTTP OK)
            int responseCode = httpURLConnection.getResponseCode();
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result){





    }




}
