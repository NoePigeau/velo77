package com.example.velo77;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class BikeActivity extends AppCompatActivity implements NetworkAsyncTask.Listeners  {

    private TextView textView;
    private ListView widget;
    List<Item> result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike);


        this.textView = findViewById(R.id.welcome);

        this.executeHttpRequest();


    }

    private void executeHttpRequest(){
        //new NetworkAsyncTask(this).execute("https://ghibliapi.herokuapp.com/films");
        new NetworkAsyncTask(this).execute("http://10.0.2.2/velo77/backend/api/item/list.php");
    }

    @Override
    public void onPreExecute() {
        this.updateUIWhenStartingHTTPRequest();
    }

    @Override
    public void doInBackground() { }

    @Override
    public void onPostExecute(String json) throws JSONException {
        this.updateUIWhenStopingHTTPRequest(json);
    }

    // ------------------
    //  UPDATE UI
    // ------------------

    private void updateUIWhenStartingHTTPRequest(){

    }

    private void updateUIWhenStopingHTTPRequest(String response) throws JSONException {
        this.addBike(response);
        this.widget = findViewById(R.id.allCards);
        ItemAdapter adapter = new ItemAdapter( BikeActivity.this , this.result);
        this.widget.setAdapter(adapter);


    }

    private void addBike( String response ) throws JSONException {

         JSONArray json = new JSONArray( response );
         //this.textView.setText(json.toString());

        for( int i = 0 ; i < 3 ; i++) {
            this.result.add( new Item(json.getJSONObject(i).getString("id"), json.getJSONObject(i).getString("name") ) );
        }

    }




}
