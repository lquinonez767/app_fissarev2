package com.fissare.activities.init;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fissare.R;
import com.fissare.activities.ProviderActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterProvider extends AppCompatActivity {

    private static final String TAG = "RegisterProveedor";
    private static final String URL_FOR_REGISTRATION = "http://192.168.100.4:8080/appServiHogar/srv/web/registro";
    //private static final String URL_FOR_REGISTRATION = "http://fissare.ayniwork.com/appServiHogar/srv/web/registro";
    ProgressDialog progressDialog;

    private EditText _cedulaText;
    private EditText _namesText;
    private EditText _addressText;
    private EditText _phoneText;
    private EditText _mobileText;
    private EditText _emailText;
    private EditText _experienceText;
    private EditText _passwordText;
    private Button _acceptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_provider);

        //Progres Dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        _cedulaText = (EditText) findViewById(R.id.register_provider_cedula);
        _namesText = (EditText) findViewById(R.id.register_provider_name);
        _addressText = (EditText) findViewById(R.id.register_provider_address);
        _phoneText = (EditText) findViewById(R.id.register_provider_phone);
        _mobileText = (EditText) findViewById(R.id.register_provider_mobile);
        _emailText = (EditText) findViewById(R.id.register_provider_email);
        _experienceText = (EditText) findViewById(R.id.register_provider_experience);
        _passwordText = (EditText) findViewById(R.id.register_provider_password);
        _acceptButton = (Button) findViewById(R.id.accept_provider);


        _acceptButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                register();
                //startActivity(new Intent(RegisterProvider.this, ProviderActivity.class));
                //Toast.makeText(RegisterProvider.this, "Registro Exitoso", Toast.LENGTH_LONG).show();
            }
        });
    }




    private void register() {
        registerProvider(
                _cedulaText.getText().toString(), _namesText.getText().toString(),
                _addressText.getText().toString(), _phoneText.getText().toString(),
                _mobileText.getText().toString(), _emailText.getText().toString(),
                _experienceText.getText().toString(), _passwordText.getText().toString()
        );
    }





    private void registerProvider(final String cedula, final String nombres, final String direccion,
                                   final String telfijo, final String telmovil, final String email,
                                   final String experiencia, final String password){

        String cancel_req_tag = "register";

        progressDialog.setMessage("Agregando ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_REGISTRATION, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {
                        String user = jObj.getJSONObject("user").getString("name");
                        Toast.makeText(getApplicationContext(), "Hola " + user + ", Has sido agregado exitosamente!", Toast.LENGTH_SHORT).show();

                        // Launch register activity
                        Intent intent = new Intent(
                                RegisterProvider.this, ProviderActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("cedula", cedula);
                params.put("nombres", nombres);
                params.put("direccion", direccion);
                params.put("telfijo", telfijo);
                params.put("telmovil", telmovil);
                params.put("email", email);
                params.put("experiencia", experiencia);
                params.put("password", password);
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);

    }//end registroProveedor() method



    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }


    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

}