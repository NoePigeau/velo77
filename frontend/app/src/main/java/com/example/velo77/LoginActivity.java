package com.example.velo77;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

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
                JSONObject login = new JSONObject();
                try {
                    login.put("email", edtEmail.getText().toString());
                    login.put("password", edtPwd.getText().toString());

                }catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }

        public  void  getInputs()  throws UnsupportedEncodingException {
            // Get user defined values
            email = edtEmail.getText().toString();
            pwd   = edtPwd.getText().toString();

            JSONObject data = new JSONObject();
            try {
                data.put("email", edtEmail.getText().toString());
                data.put("password", edtPwd.getText().toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }



            // Create data variable for sent values to server

            /*String data = URLEncoder.encode("email", "UTF-8")
                    + "=" + URLEncoder.encode(email, "UTF-8");

            data += "&" + URLEncoder.encode("password", "UTF-8") + "="
                    + URLEncoder.encode(pwd, "UTF-8");*/


            String text = "";
            BufferedReader reader=null;

            // Send data
            try
            {

                // Defined URL  where to send data
                URL url = new URL("http://10.0.2.2/velo77/backend/api/user/login.php");

                // Send POST data request

                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write( data.toString() );
                wr.flush();

                // Get the server response

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    // Append server response in string
                    sb.append(line + "\n");
                }


                text = sb.toString();
            }
            catch(Exception ex)
            {

            }
            finally
            {
                try
                {

                    reader.close();
                }

                catch(Exception ex) {}
            }

            // Show response on activity
            responseText.setText( text  );

        }

    }




