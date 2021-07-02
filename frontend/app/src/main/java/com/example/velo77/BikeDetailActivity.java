package com.example.velo77;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.velo77.obj.Item;

public class BikeDetailActivity extends AppCompatActivity {

    private TextView title;
    private Item data;
    private TextView price, description, collection, brand, series, type;

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
    }
}
