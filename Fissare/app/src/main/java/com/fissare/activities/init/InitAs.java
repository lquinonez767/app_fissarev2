package com.fissare.activities.init;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fissare.R;
import com.fissare.activities.ClientActivity;
import com.fissare.activities.ProviderActivity;

public class InitAs extends AppCompatActivity {

    ProgressDialog progressDialog;

    private Button _clienteButton;
    private Button _proveedorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_as);

        //Progres Dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        _clienteButton = (Button) findViewById(R.id.initas_client);
        _proveedorButton = (Button) findViewById(R.id.initas_provider);

        _clienteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InitAs.this, LoginClient.class));
                //startActivity(new Intent(InitAs.this, ClientActivity.class));
            }
        });

        _proveedorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InitAs.this, LoginProvider.class));
                //startActivity(new Intent(InitAs.this, ProviderActivity.class));
            }
        });
    }
}
