package com.example.hitman.sang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Hashtable;

public class liste_donnations extends AppCompatActivity {
    ListView listView;
    database db=new database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_donnations);
        listView=(ListView)findViewById(R.id.list_donnation);
        final ArrayList<Hashtable<String,String>> list = db.getListdonnation();
        SimpleAdapter adp = new SimpleAdapter
                (getApplicationContext(),
                        list,
                        R.layout.layout_list_donneur,
                        new String[]{"date", "cin"},
                        new int[]{R.id.nom_donneur, R.id.cin_donneur});
        listView.setAdapter(adp);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(liste_donnations.this,etat.class);
                intent.putExtra("cin",list.get(position).get("cin"));
                intent.putExtra("date",list.get(position).get("date"));
                startActivity(intent);
            }
        });
    }
}
