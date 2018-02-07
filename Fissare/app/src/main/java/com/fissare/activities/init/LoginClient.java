package com.fissare.activities.init;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fissare.R;
import com.fissare.activities.ClientActivity;

import java.util.HashMap;
import java.util.Map;

public class LoginClient extends AppCompatActivity {

    EditText emailBoxClient, passwordBoxClient;
    Button loginButtonClient;
    TextView registerLinkClient;
    //String URL = "http://192.168.100.4:8080/appServiHogar/srv/web/login";
    String URL = "http://fissare.ayniwork.com/appServiHogar/srv/web/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_client);

        emailBoxClient = (EditText)findViewById(R.id.emailBoxClient);
        passwordBoxClient = (EditText)findViewById(R.id.passwordBoxClient);
        loginButtonClient = (Button)findViewById(R.id.loginButtonClient);
        registerLinkClient = (TextView)findViewById(R.id.registerLinkClient);

        loginButtonClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        if(s.equals("true")){
                            Toast.makeText(LoginClient.this, "Login Exitoso", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(LoginClient.this, ClientActivity.class));
                        }
                        else{
                            Toast.makeText(LoginClient.this, "Datos Incorrectos", Toast.LENGTH_LONG).show();
                        }
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(LoginClient.this, "Ocurrió un error -> "+volleyError, Toast.LENGTH_LONG).show();;
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("email", emailBoxClient.getText().toString());
                        parameters.put("password", passwordBoxClient.getText().toString());
                        return parameters;
                    }
                };

                RequestQueue rQueue = Volley.newRequestQueue(LoginClient.this);
                rQueue.add(request);
            }
        });

        registerLinkClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginClient.this, RegisterClient.class));
            }
        });
    }
}
