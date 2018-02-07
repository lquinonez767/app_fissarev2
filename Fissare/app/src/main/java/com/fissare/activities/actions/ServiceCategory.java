package com.fissare.activities.actions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.fissare.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.view.View;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class ServiceCategory extends AppCompatActivity {

    private static ExpandableListView expandableListView;
    private static ServiceCategoryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_category);

        expandableListView = (ExpandableListView) findViewById(R.id.simple_expandable_listview);

        // Setting group indicator null for custom indicator
        expandableListView.setGroupIndicator(null);

        setItems();
        setListener();
    }





    // Setting headers and childs to expandable listview
    void setItems() {

        // Array list for header (categories)
        ArrayList<String> header = new ArrayList<String>();
        String [] categories = {"ELECTRICIDAD", "GASFITERIA - FONTANERIA","EBANISTERIA",
                "CONSTRUCCION","DECORACION Y JARDINERIA","ELECTRODOMESTICOS"};

        // Array list for child items
        List<String> child1 = new ArrayList<String>();
        List<String> child2 = new ArrayList<String>();
        List<String> child3 = new ArrayList<String>();
        List<String> child4 = new ArrayList<String>();
        List<String> child5 = new ArrayList<String>();
        List<String> child6 = new ArrayList<String>();


        String [] electricidad = {"Instalaciones electricas", "Mantenimiento Electrico"};
        String [] gasfiteria = {"Limpieza", "Reparacion", "Instalacion"};
        String [] ebanisteria = {"Reparación de muebles", "entablado"};
        String [] construccion = {"Reparacion de paredes", "Arreglo de fachadas"};
        String [] jardin = {"Jardines","Instalacion de Riego"};
        String [] electrodomesticos = {"Lavadoras", "Reparacion electrodomesticos"};

        // Hash map for both header and child
        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();

        // Adding headers to list
        for (int i = 0; i < categories.length; i++) {
            header.add(categories[i]);

        }
        // Adding child data
        for (int i = 0; i < electricidad.length; i++) {
            child1.add(categories[0] + " - " + electricidad[i]);

        }
        // Adding child data
        for (int i = 0; i < gasfiteria.length; i++) {
            child2.add(categories[1] + " - " + gasfiteria[i]);

        }
        // Adding child data
        for (int i = 0; i < ebanisteria.length; i++) {
            child3.add(categories[2] + " - " + ebanisteria[i]);

        }
        // Adding child data
        for (int i = 0; i < construccion.length; i++) {
            child4.add(categories[3] + " - " + construccion[i]);

        }
        // Adding child data
        for (int i = 0; i < jardin.length; i++) {
            child5.add(categories[4] + " - " + jardin[i]);

        }
        // Adding child data
        for (int i = 0; i < electrodomesticos.length; i++) {
            child6.add(categories[5] + " - " + electrodomesticos[i]);

        }

        // Adding header and childs to hash map
        hashMap.put(header.get(0), child1);
        hashMap.put(header.get(1), child2);
        hashMap.put(header.get(2), child3);
        hashMap.put(header.get(3), child4);
        hashMap.put(header.get(4), child5);
        hashMap.put(header.get(5), child6);

        adapter = new ServiceCategoryListAdapter(ServiceCategory.this, header, hashMap);

        // Setting adpater over expandablelistview
        expandableListView.setAdapter(adapter);
    }





    // Setting different listeners to expandablelistview
    void setListener() {

        // This listener will show toast on group click
        expandableListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView listview, View view,
                                        int group_pos, long id) {

                Toast.makeText(ServiceCategory.this,
                        "You clicked : " + adapter.getGroup(group_pos),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // This listener will expand one group at one time
        // You can remove this listener for expanding all groups
        expandableListView
                .setOnGroupExpandListener(new OnGroupExpandListener() {

                    // Default position
                    int previousGroup = -1;

                    @Override
                    public void onGroupExpand(int groupPosition) {
                        if (groupPosition != previousGroup)

                            // Collapse the expanded group
                            expandableListView.collapseGroup(previousGroup);
                        previousGroup = groupPosition;
                    }

                });

        // This listener will show toast on child click
        expandableListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView listview, View view,
                                        int groupPos, int childPos, long id) {
                Toast.makeText(
                        ServiceCategory.this,
                        "You clicked : " + adapter.getChild(groupPos, childPos),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
