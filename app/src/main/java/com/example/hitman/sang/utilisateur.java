package com.example.hitman.sang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class utilisateur extends AppCompatActivity {
    ListView list_donnation;
    String cin;
    Button ajouter_rend;
    EditText date_rend;
    database db=new database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilisateur);
        list_donnation=(ListView) findViewById(R.id.date_donnatio);
        ajouter_rend=(Button)findViewById(R.id.ajouter_rend);
        date_rend=(EditText)findViewById(R.id.date_rend);
        Intent intent1=getIntent();
        if(intent1!=null) {
            Bundle bundle = intent1.getExtras();
            if (bundle != null) {
                cin = bundle.getString("cin");
                if (cin != null) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            this,
                            android.R.layout.simple_list_item_1,
                            db.getListdonnation(cin));
                    list_donnation.setAdapter(adapter);
                }
                ajouter_rend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean b=db.ajouter_rendez_vous(cin,date_rend.getText().toString());
                        if(b==true)
                            Toast.makeText(utilisateur.this,"rendez_vous ajouter avec succes",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(utilisateur.this,"echec d'ajouter rendez_vous",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_message,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_message:
                Intent intent=new Intent();
                intent.setClass(utilisateur.this,message.class);
                intent.putExtra("cin", cin);
                startActivity(intent);
                break;
        }
        return  true;
    }
}
