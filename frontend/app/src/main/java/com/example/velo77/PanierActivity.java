package com.example.velo77;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.velo77.obj.Item;
import com.example.velo77.obj.ItemAdapter;
import com.example.velo77.request.GetAsyncTask;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class PanierActivity extends AppCompatActivity implements GetAsyncTask.Listeners  {

    BottomNavigationView bottomNavigationView;
    private ListView widget;
    private List<Item> result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);


        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_shop);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem MenuItem) {
                switch (MenuItem.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_search:
                        startActivity(new Intent(getApplicationContext(), BikeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_store:
                        startActivity(new Intent(getApplicationContext(), BikeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_shop:
                        return true;
                }
                return false;
            }
        });
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

    private void updateUIWhenStartingHTTPRequest(){

    }

    private void updateUIWhenStopingHTTPRequest(String response) throws JSONException {
        this.addBike(response);
        this.widget = findViewById(R.id.allCards);
        ItemAdapter adapter = new ItemAdapter( PanierActivity.this , this.result);
        adapter.updateImage();
        this.widget.setAdapter(adapter);


    }

    private void addBike( String response ) throws JSONException {

        JSONArray json = new JSONArray( response );
        //this.textView.setText(json.toString());

        for( int i = 0 ; i < json.length() ; i++) {
            this.result.add( new Item(json.getJSONObject(i).getString("id"),
                    json.getJSONObject(i).getString("name"),
                    json.getJSONObject(i).getString("description"),
                    json.getJSONObject(i).getDouble("price"),
                    json.getJSONObject(i).getString("size"),
                    json.getJSONObject(i).getString("collection"),
                    json.getJSONObject(i).getString("gamme"),
                    json.getJSONObject(i).getString("brand"),
                    json.getJSONObject(i).getString("type")
            ) );
        }

    }


    public void executeHttpRequest(){
        SharedPreferences shp = getSharedPreferences("ID" , MODE_MULTI_PROCESS);
        String url = "http://10.0.2.2/velo77/backend/api/panier/list.php?idUser=" + shp.getString("idUser" , "7");
        //String url = "https://ghibliapi.herokuapp.com/films";



        new GetAsyncTask(this).execute(url);
    }

}
