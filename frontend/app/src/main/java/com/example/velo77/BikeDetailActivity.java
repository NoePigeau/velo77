package com.example.velo77;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BikeDetailActivity extends AppCompatActivity {

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_detail);

        this.title = findViewById(R.id.name_bike);

        Intent i = getIntent();
        if (i != null && i.hasExtra("idItem") ){

            this.title.setText( "Nom du Vélo, id: " + i.getStringExtra("idItem"));

        }
    }
}
