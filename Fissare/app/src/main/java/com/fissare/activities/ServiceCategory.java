package com.fissare.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.ExpandableListView;

import com.fissare.R;

public class ServiceCategory extends AppCompatActivity {

    // more efficient than HashMap for mapping integers to objects
    SparseArray<ServiceCategoryGroup> groups = new SparseArray<ServiceCategoryGroup>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_category);

        createData();
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listServiceCategory);
        ServiceCategoryListAdapter adapter = new ServiceCategoryListAdapter(this,
                groups);
        listView.setAdapter(adapter);
    }

    String[] catServ = {"ELECTRICIDAD", "GASFITERÍA - FONTANERIA", "EBANISTERIA", "CONSTRUCCIÓN",
                        "DECORACIÓN Y JARDINERÍA", "ELECTRODOMÉSTICOS"};
/*
    String[][] servicios = {{"Instalaciones Eléctricas", "Mantenimiento Eléctrico"},
            {"Limpieza","Instalación","Reparacion"},
            {"Reparación de muebles","Entablado"},
            {"Reparación de paredes","Arreglo de fachadas"},
            {"Jardines", "Instalación de riego"},
            {"Lavadoras","Reparación electrodomésticos"}};
//*/
    String[] servicios = {"Instalaciones Eléctricas", "Mantenimiento Eléctrico",
            "Limpieza","Instalación","Reparacion",
            "Reparación de muebles","Entablado",
            "Reparación de paredes","Arreglo de fachadas",
            "Jardines", "Instalación de riego",
            "Lavadoras","Reparación electrodomésticos"};

    public void createData() {
        for (int j = 0; j < catServ.length; j++) {
            ServiceCategoryGroup group = new ServiceCategoryGroup(catServ[j]);
            for (int i = 0; i < servicios.length; i++) {
                group.children.add(servicios[i]);
            }
            groups.append(j, group);
        }
    }
}
