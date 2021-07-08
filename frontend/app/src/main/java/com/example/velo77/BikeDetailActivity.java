package com.example.velo77;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.velo77.obj.Item;
import com.example.velo77.request.PostAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

public class BikeDetailActivity extends AppCompatActivity  implements  PostAsyncTask.Listeners{

    private TextView title;
    private Item data;
    private TextView price, description, collection, brand, series, type;
    private Button btnPanier;
    private SharedPreferences shp = this.getSharedPreferences("ID" , Context.MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_detail);

        this.title = findViewById(R.id.name_bike);
        this.price = findViewById(R.id.price_bike);
        this.description = findViewById(R.id.description_bike);
        this.collection = findViewById(R.id.collection_bike);
        this.brand = findViewById(R.id.brand_bike);
        this.series = findViewById(R.id.series_bike);
        this.type = findViewById(R.id.type_bike);

        Intent i = getIntent();
        if (i != null ){

            this.data = (Item) i.getExtras().getSerializable("item");
            this.title.setText( this.data.getName() );
            this.price.setText(this.data.getPrice() + " €");
            this.description.setText("Description: " + this.data.getDescription());
            this.collection.setText("Collection : " + this.data.getCollection());
            this.brand.setText("Marque : " + this.data.getBrand());
            this.series.setText("Gamme : " + this.data.getSeries());
            this.type.setText("Type : " + this.data.getType());

        }

        this.btnPanier = findViewById(R.id.btn_add);
        this.btnPanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder( BikeDetailActivity.this)
                        .setTitle("Ajouter au panier")
                        .setMessage("voulez-vous ajouter cette article au panier ?")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                executeHttpRequest();

                            }
                        })
                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setCancelable(false)
                        .show();
                
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



        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();





    }



    public void executeHttpRequest(){
        String url = "http://10.0.2.2/velo77/backend/api/panier/create.php";
        //String url = "https://ghibliapi.herokuapp.com/films";

        JSONObject json = new JSONObject();
        try {
            json.put("idUser", shp.getString("idUser" , null) );
            json.put("idItem", this.data.getId());

        }catch (JSONException e) {
            e.printStackTrace();
        }

        new PostAsyncTask(this, json).execute(url);
    }
}
