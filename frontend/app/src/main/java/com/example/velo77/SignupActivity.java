package com.example.velo77;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.velo77.request.PostAsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity implements PostAsyncTask.Listeners {

    private EditText fn, ln, email, pwd;
    private Button signup;
    private TextView responseText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        this.fn = findViewById(R.id.edt_firstname);
        this.ln = findViewById(R.id.edt_lastname);
        this.email = findViewById(R.id.edt_email);
        this.pwd = findViewById(R.id.edt_pwd);
        this.responseText = findViewById(R.id.response_text);

        this.signup = findViewById(R.id.btn_signup);
        this.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeHttpRequest();

            }
        });
    }

    public  void  responseSignup(String response)  {

        switch (response){
            case "500": this.responseText.setText("Erreur lors de l'inscription !");
                break;
            case "400": this.responseText.setText("Erreur avec les Inputs!");
                break;
            case "409": this.responseText.setText("Email deja pris !");
                break;
            default:
                Toast.makeText(getApplicationContext(),"Inscription réussie !",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, LoginActivity.class);
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
        responseSignup(response);

    }

    public void executeHttpRequest(){
        String url = "http://10.0.2.2/velo77/backend/api/user/create.php";

        JSONObject inputs = new JSONObject();
        try {
            inputs.put("firstname", fn.getText().toString());
            inputs.put("lastname", ln.getText().toString());
            inputs.put("email", email.getText().toString());
            inputs.put("password", pwd.getText().toString());

        }catch (JSONException e) {
            e.printStackTrace();
        }

        new PostAsyncTask(this, inputs).execute(url);
    }



}
