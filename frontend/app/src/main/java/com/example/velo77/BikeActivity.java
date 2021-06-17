package com.example.velo77;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BikeActivity extends AppCompatActivity implements NetworkAsyncTask.Listeners  {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike);


        this.textView = findViewById(R.id.welcome);
        
        this.executeHttpRequest();


    }


    public String bonsoir(){
        return "bonsoir";
    }




    private void executeHttpRequest(){
        new NetworkAsyncTask(this).execute("http://localhost:80/devweb/stage_velo77/backend/api/item/list.php");
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
