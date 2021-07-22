package com.example.velo77;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SelectItemActivity extends AppCompatActivity {

    private Button btn_bike, btn_piece, btn_accessories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_item);

        this.btn_bike = findViewById(R.id.btn_bikes);
        this.btn_piece = findViewById(R.id.btn_piece);
        this.btn_accessories = findViewById(R.id.btn_accessories);

        this.btn_bike.setOnClickListener(new View.OnClickListener() {
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
