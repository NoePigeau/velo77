package com.example.velo77;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.velo77.request.PostAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements PostAsyncTask.Listeners {

    private Button btnLogin;
    private EditText edtEmail, edtPwd;
    private TextView responseText;
    private SharedPreferences shp = this.getSharedPreferences("ID" , Context.MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        this.edtEmail = findViewById(R.id.edt_email);
        this.edtPwd = findViewById(R.id.edt_pwd);
        this.btnLogin = findViewById(R.id.btn_login);
        this.responseText = findViewById(R.id.response_text);

        this.btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                executeHttpRequest();

            }
        });
    }

        public  void  responseLogin(String response) throws JSONException {

            switch (response){
                case "500": this.responseText.setText("Erreur lors de la connection !");
                    break;
                case "404": this.responseText.setText("Identifiants incorrectes !");
                    break;
                default:
                    JSONObject json = new JSONObject( response );
                    this.responseText.setText(json.getString("token"));

                    Intent i = new Intent(this, HomeActivity.class);
                    shp.edit().clear();
                    shp.edit().putString("token" , json.getString("token"));
                    shp.edit().putString("idUser" , json.getString("idUser"));
                    shp.edit().apply();

                    startActivity(i);
            }
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

    // ------------------
    //  UPDATE UI
    // ------------------

    private void updateUIWhenStartingHTTPRequest(){
        this.responseText.setText("wait a sec ... ");

    }

    private void updateUIWhenStopingHTTPRequest(String response) throws JSONException {
        responseLogin(response);

    }

    public void executeHttpRequest(){
        String url = "http://10.0.2.2/velo77/backend/api/user/login.php";
        //String url = "https://ghibliapi.herokuapp.com/films";

        JSONObject inputs = new JSONObject();
        try {
            inputs.put("email", edtEmail.getText().toString());
            inputs.put("password", edtPwd.getText().toString());

        }catch (JSONException e) {
            e.printStackTrace();
        }

        new PostAsyncTask(this, inputs).execute(url);
    }

}




