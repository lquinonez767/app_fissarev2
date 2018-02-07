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
import com.fissare.activities.ProviderActivity;

import java.util.HashMap;
import java.util.Map;

public class LoginProvider extends AppCompatActivity {

    EditText emailBox, passwordBox;
    Button loginButton;
    TextView registerProviderLink;
    //String URL = "http://192.168.100.4:8080/appServiHogar/srv/web/login";
    String URL = "http://fissare.ayniwork.com/appServiHogar/srv/web/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_provider);

        emailBox = (EditText)findViewById(R.id.emailBoxProvider);
        passwordBox = (EditText)findViewById(R.id.passwordBoxProvider);
        loginButton = (Button)findViewById(R.id.loginButtonProvider);
        registerProviderLink = (TextView)findViewById(R.id.registerLinkProvider);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String s) {
                        if(s.equals("true")){
                            Toast.makeText(LoginProvider.this, "Login Exitoso", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(LoginProvider.this, ProviderActivity.class));
                        }
                        else{
                            Toast.makeText(LoginProvider.this, "Datos Incorrectos", Toast.LENGTH_LONG).show();
                        }
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(LoginProvider.this, "Ocurrió un error -> "+volleyError, Toast.LENGTH_LONG).show();;
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("email", emailBox.getText().toString());
                        parameters.put("password", passwordBox.getText().toString());
                        return parameters;
                    }
                };

                RequestQueue rQueue = Volley.newRequestQueue(LoginProvider.this);
                rQueue.add(request);
            }
        });

        registerProviderLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginProvider.this, RegisterProvider.class));
            }
        });
    }
}
