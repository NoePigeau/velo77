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
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.logout = findViewById(R.id.btn_logout);


        this.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences shp = getSharedPreferences("ID" , MODE_MULTI_PROCESS);
                Toast.makeText(getApplicationContext(),shp.getString("idUser" , "raté"),Toast.LENGTH_SHORT).show();
                shp.edit().clear();
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(i);
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
                        startActivity(new Intent(getApplicationContext(),SelectItemActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_store:
                        startActivity(new Intent(getApplicationContext(),SelectItemActivity.class));
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
