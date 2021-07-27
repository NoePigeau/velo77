package com.example.velo77;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.velo77.obj.Item;
import com.example.velo77.request.LoadImages;
import com.example.velo77.request.PostAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BikeDetailActivity extends AppCompatActivity  implements  PostAsyncTask.Listeners{

    private TextView title;
    private Item data;
    private TextView price, description, collection, brand, series, type;
    private Button btnPanier, btnXS, btnS, btnM, btnL;
    private ImageView iv;
    private List<Button> allButton = new ArrayList<>();


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

        this.iv = findViewById(R.id.imageView);

        this.btnXS = findViewById(R.id.XS); this.btnS = findViewById(R.id.S);
        this.btnM = findViewById(R.id.M); this.btnL = findViewById(R.id.L);

        this.allButton.add(this.btnXS); this.allButton.add(this.btnS);
        this.allButton.add(this.btnM);this.allButton.add(this.btnL);

        for( int i = 0 ; i < allButton.size() ; i++) {

            int finalI = i;
            allButton.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonSelected(allButton.get(finalI));
                }
            });
        }

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
                        .setMessage("voulez-vous ajouter cet article au panier ?")
                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                executeHttpRequest();

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
        switch (response){
            case "500": Toast.makeText(getApplicationContext(),"une erreur est survenu !",Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getApplicationContext(),"ajouté au panier !",Toast.LENGTH_SHORT).show();

                updateImage(this.iv ,this.data.getId());

        }

        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();

    }



    public void executeHttpRequest(){
        String url = "http://10.0.2.2/velo77/backend/api/panier/create.php";

        JSONObject json = new JSONObject();
        try {
            SharedPreferences shp = getSharedPreferences("ID" , MODE_MULTI_PROCESS);
            json.put("idUser", shp.getString("idUser" , null) );
            json.put("idItem", this.data.getId());

        }catch (JSONException e) {
            e.printStackTrace();
        }

        new PostAsyncTask(this, json).execute(url);
    }

    public void buttonSelected(Button b) {

        for( int i = 0 ; i < allButton.size() ; i++) {

            this.allButton.get(i).setBackgroundColor(Color.parseColor("#FFFFFF"));
            this.allButton.get(i).setTextColor(Color.parseColor("#7CCB31"));

        }

        b.setTextColor(Color.parseColor("#FFFFFF"));
        b.setBackgroundColor(Color.parseColor("#7CCB31"));

    }

    public void updateImage(ImageView iv, String item) {


        String url = "http://10.0.2.2/velo77/backend/img-item/item_" + item + ".png";
        new LoadImages(iv).execute(url);
    }
}
