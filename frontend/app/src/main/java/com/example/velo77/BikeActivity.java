package com.example.velo77;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class BikeActivity extends AppCompatActivity {

    private TextView bikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike);


            /*this.bikes.setText();*/


    }



    public String getBike() throws IOException {

        String url = "http://localhost:80/devweb/Sananair/api/planes/list.php";
        String charset = "UTF-8";
        /*String param1 = "value1";
        String param2 = "value2";*/

        /*String query = String.format("param1=%s&param2=%s",
                URLEncoder.encode(param1, charset),
                URLEncoder.encode(param2, charset));

        URLConnection connection = new URL(url + "?" + query).openConnection();
        connection.setRequestProperty("Accept-Charset", charset);
        InputStream response = connection.getInputStream();*/

        InputStream response = new URL(url).openStream();
        System.out.println(response);
        return response.toString();

    }
}
