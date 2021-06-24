package com.example.velo77;

import android.os.AsyncTask;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallAPI extends AsyncTask<String, String, String> {

    public CallAPI() {
        //set context variables if required
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0]; // URL to call
        String data = params[1]; //data to post
        OutputStream out = null;



        try {
            URL url = new URL(params[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");//important
            httpURLConnection.connect();
            //write data to the server using BufferedWriter
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(httpURLConnection
                    .getOutputStream()));
            writer.write("email=email&password=1234");
            writer.flush();

            String responseCode = "Code: " + httpURLConnection.getResponseCode();
            /*StringBuilder stringBuilder = new StringBuilder();
            InputStream in = urlConnection.getInputStream();
            // 3 - Download and decode the string response
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();*/
            return responseCode;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error";
        }
    }
}