package com.example.velo77;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText edtEmail, edtPwd;
    private String email, pwd;
    private TextView responseText;

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
                try {
                    getInputs();

                }catch (Exception e){

                    //responseText.setText(" url exeption! " );

                }
                /*Intent i = new Intent( LoginActivity.this , HomeActivity.class);
                startActivity(i);*/
            }
        });
    }


    public void getInputs() throws UnsupportedEncodingException, JSONException {

        email = edtEmail.getText().toString();
        pwd = edtPwd.getText().toString();

        String data = URLEncoder.encode("email", "UTF-8")
                + "=" + URLEncoder.encode(email, "UTF-8");

        data += "&" + URLEncoder.encode("password", "UTF-8") + "="
                + URLEncoder.encode(pwd, "UTF-8");


        String[] array = {"http://10.0.2.2/velo77/backend/api/user/login.php" , data};
        CallAPI api = new CallAPI();
        String result = api.doInBackground(array);
        JSONArray json = new JSONArray(result);

        responseText.setText(json.toString());

    }

}
