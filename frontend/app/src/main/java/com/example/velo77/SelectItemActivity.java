package com.example.velo77;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SelectItemActivity extends AppCompatActivity {

    private Button btn_bikes, btn_piece, btn_accessories;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_item);

        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.nav_search);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem MenuItem) {
                switch (MenuItem.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_search:
                        return true;
                    case R.id.nav_store:
                        startActivity(new Intent(getApplicationContext(),WorkshopActivity.class));
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

        this.btn_bikes = findViewById(R.id.btn_bikes);
        this.btn_piece = findViewById(R.id.btn_piece);
        this.btn_accessories = findViewById(R.id.btn_accessories);

        this.btn_bikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectItemActivity.this, BikeActivity.class );
                i.putExtra("url" , "http://10.0.2.2/velo77/backend/api/item/list.php?type=normal");
                startActivity(i);
            }
        });

        this.btn_piece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectItemActivity.this, BikeActivity.class );
                i.putExtra("url" , "http://10.0.2.2/velo77/backend/api/item/list.php?type=piece");
                startActivity(i);
            }
        });

        this.btn_accessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectItemActivity.this, BikeActivity.class );
                i.putExtra("url" , "http://10.0.2.2/velo77/backend/api/item/list.php?type=accessory");
                startActivity(i);
            }
        });
    }


}
