package com.example.velo77;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.velo77.obj.Item;
import com.example.velo77.obj.PanierAdapter;
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
    private TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        this.totalPrice = findViewById(R.id.price_panier);


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
                        startActivity(new Intent(getApplicationContext(), SelectItemActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_store:
                        startActivity(new Intent(getApplicationContext(), WorkshopActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_shop:
                        return true;
                }
                return false;
            }
        });
        getPanier();
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
        PanierAdapter adapter = new PanierAdapter( PanierActivity.this , this.result);
        this.widget.setAdapter(adapter);


    }

    private void addBike( String response ) throws JSONException {

        switch (response){
            case "500": Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                break;
            case "404": Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                break;
            case "410":
                result.clear();
                getPanier();
                break;
            default:
                JSONArray json = new JSONArray( response );
                //this.textView.setText(json.toString());

                double sum = 0.0;

                for( int i = 0 ; i < json.length() ; i++) {
                    sum += json.getJSONObject(i).getDouble("price");
                    this.result.add( new Item(json.getJSONObject(i).getString("idPanier"),
                            json.getJSONObject(i).getString("name"),
                            json.getJSONObject(i).getString("description"),
                            json.getJSONObject(i).getDouble("price"),
                            json.getJSONObject(i).getString("size"),
                            json.getJSONObject(i).getString("collection"),
                            json.getJSONObject(i).getString("gamme"),
                            json.getJSONObject(i).getString("brand"),
                            json.getJSONObject(i).getString("type")
                    ) );
                    this.result.get(i).setNumber(json.getJSONObject(i).getInt("nbItem"));
                }
                this.totalPrice.setText("Prix total : " + sum + " €");


        }

    }


    public void getPanier(){
        SharedPreferences shp = getSharedPreferences("ID" , MODE_MULTI_PROCESS);
        String url = "http://10.0.2.2/velo77/backend/api/panier/list.php?idUser=" + shp.getString("idUser" , "0");
        //String url = "https://ghibliapi.herokuapp.com/films";



        new GetAsyncTask(this).execute(url);
    }

    public void deletePanier(String id){
        String url = "http://10.0.2.2/velo77/backend/api/panier/delete.php?id=" + id;
        

        new GetAsyncTask(this).execute(url);
    }



}
