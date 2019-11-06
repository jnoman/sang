package com.example.hitman.sang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;

public class liste_donneur extends AppCompatActivity {
    Spinner spinner_ville,spinner_type;
    ListView list_donneur;
    database db=new database(this);
    ArrayList<Hashtable<String,String>> list=new ArrayList<Hashtable<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_donneur);
        spinner_ville=(Spinner)findViewById(R.id.spinner_ville);
        spinner_type=(Spinner)findViewById(R.id.spinner_type);
        list_donneur=(ListView)findViewById(R.id.list_donneur);
        ArrayAdapter ar = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getListVille());
        spinner_ville.setAdapter(ar);
        ArrayAdapter ar1 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getListtype());
        spinner_type.setAdapter(ar1);

        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final ArrayList<Hashtable<String,String>> list = db.getListdonneur(spinner_ville.getSelectedItem().toString(), spinner_type.getSelectedItem().toString());
                SimpleAdapter adp = new SimpleAdapter
                        (getApplicationContext(),
                                list,
                                R.layout.layout_list_donneur,
                                new String[]{"nom", "cin"},
                                new int[]{R.id.nom_donneur, R.id.cin_donneur});
                list_donneur.setAdapter(adp);

                list_donneur.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(liste_donneur.this,info_donneur.class);
                        intent.putExtra("cin",list.get(position).get("cin"));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
