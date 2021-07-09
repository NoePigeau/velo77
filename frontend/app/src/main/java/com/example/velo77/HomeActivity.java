package com.example.velo77;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    private Button bikes, testo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.bikes = findViewById(R.id.btn_bikes);
        this.testo = findViewById(R.id.btn_testo);
        this.bikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( HomeActivity.this , BikeActivity.class);
                startActivity(i);
            }
        });

        this.testo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences shp = getSharedPreferences("ID" , MODE_MULTI_PROCESS);
                Toast.makeText(getApplicationContext(),shp.getString("idUser" , "raté"),Toast.LENGTH_SHORT).show();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem MenuItem) {
                switch (MenuItem.getItemId()){
                    case R.id.nav_home:
                        return true;
                    case R.id.nav_search:
                        startActivity(new Intent(getApplicationContext(),BikeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_store:
                        startActivity(new Intent(getApplicationContext(),BikeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_shop:
                        startActivity(new Intent(getApplicationContext(),PanierActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }

}
