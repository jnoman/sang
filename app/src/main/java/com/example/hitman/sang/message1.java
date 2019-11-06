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
import java.util.List;

public class message1 extends AppCompatActivity {
    ListView list_donneur;
    database db=new database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message1);
        list_donneur=(ListView)findViewById(R.id.list_donneur1);
        final ArrayList<Hashtable<String,String>> list=db.getListdonneurs();
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
                Intent intent=new Intent(message1.this,message.class);
                intent.putExtra("cin",list.get(position).get("cin"));
                startActivity(intent);
            }
        });
    }
}
