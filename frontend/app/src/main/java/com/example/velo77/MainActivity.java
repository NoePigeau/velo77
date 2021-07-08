package com.example.velo77;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.velo77.request.LoadImages;

public class MainActivity extends AppCompatActivity {

    private Button btnVisitor, btnLogin, btnSignup ;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnVisitor = findViewById(R.id.btn_enter);
        this.btnLogin = findViewById(R.id.btn_login);
        this.btnSignup = findViewById(R.id.btn_signup);

        this.iv = findViewById(R.id.imageView);

        this.LoadImage();

        this.btnVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this , HomeActivity.class);
                startActivity(i);

            }
        });

        this.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this , SignupActivity.class);
                startActivity(i);

            }
        });

        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this , LoginActivity.class);
                startActivity(i);

            }
        });
    }

    public void LoadImage(){

        String url = "https://api.androidhive.info/images/sample.jpg";
        new LoadImages( this.iv ).execute(url);
    }

}