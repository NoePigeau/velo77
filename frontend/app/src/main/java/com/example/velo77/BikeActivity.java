package com.example.velo77;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.BreakIterator;


public class BikeActivity extends AppCompatActivity implements NetworkAsyncTask.Listeners  {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike);


        this.textView = findViewById(R.id.text_bike);

        /*this.textView.setText( this.bonsoir() );*/
        this.executeHttpRequest();


    }

    public String bonsoir(){
        return "bonsoir";
    }

    private void executeHttpRequest(){
        new NetworkAsyncTask(this).execute("http://transport.opendata.ch/v1/connections?from=Lausanne&to=Gen%C3%A8ve");
    }

    @Override
    public void onPreExecute() {
        this.updateUIWhenStartingHTTPRequest();
    }

    @Override
    public void doInBackground() { }

    @Override
    public void onPostExecute(String json) {
        this.updateUIWhenStopingHTTPRequest(json);
    }

    // ------------------
    //  UPDATE UI
    // ------------------

    private void updateUIWhenStartingHTTPRequest(){
        this.textView.setText("Downloading...");
    }

    private void updateUIWhenStopingHTTPRequest(String response){
        this.textView.setText(response);
    }




}
