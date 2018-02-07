package com.fissare.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.ExpandableListView;

import com.fissare.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServiceCategory extends AppCompatActivity {

    private ExpandableListView expListView;
    private ServiceCategoryListAdapter adapter;
    private ArrayList<String> listCategories;
    private Map<String, ArrayList<String>> mapChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_category);

        expListView = (ExpandableListView) findViewById(R.id.expLV);
        listCategories = new ArrayList<>();
        mapChild = new HashMap<>();

        createData();
    }


    public void createData() {

        ArrayList<String> listElectricidad = new ArrayList<>();
        ArrayList<String> listGasfiteria = new ArrayList<>();
        ArrayList<String> listEbanisteria = new ArrayList<>();
        ArrayList<String> listConstruccion = new ArrayList<>();
        ArrayList<String> listJardineria = new ArrayList<>();
        ArrayList<String> listElectrodomesticos = new ArrayList<>();


        listCategories.add("ELECTRICIDAD");
        listCategories.add("GASFITERÍA - FONTANERIA");
        listCategories.add("EBANISTERIA");
        listCategories.add("CONSTRUCCIÓN");
        listCategories.add("DECORACIÓN Y JARDINERÍA");
        listCategories.add("ELECTRODOMÉSTICOS");

        listElectricidad.add("Instalaciones Eléctricas");
        listElectricidad.add("Instalaciones Eléctricas");

        listGasfiteria.add("Limpieza");
        listGasfiteria.add("Instalacion");
        //listGasfiteria.add("Reparacion");

        listEbanisteria.add("Reparación de muebles");
        listEbanisteria.add("Entablado");

        listConstruccion.add("Reparacion de paredes");
        listConstruccion.add("Arreglo de fachadas");

        listJardineria.add("Jardines");
        listJardineria.add("Instalacion de riego");

        listElectrodomesticos.add("Lavadoras");
        listElectrodomesticos.add("Reparacion de electrodomesticos");

        mapChild.put(listCategories.get(0), listElectricidad);
        mapChild.put(listCategories.get(1), listGasfiteria);
        mapChild.put(listCategories.get(2), listEbanisteria);
        mapChild.put(listCategories.get(3), listConstruccion);
        mapChild.put(listCategories.get(4), listJardineria);
        mapChild.put(listCategories.get(5), listElectrodomesticos);

        adapter = new ServiceCategoryListAdapter(this, listCategories, mapChild);
        expListView.setAdapter(adapter);
    }
}
