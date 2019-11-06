package com.example.hitman.sang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class inscription extends AppCompatActivity {
    EditText cin,nom,prenom,age, poids,  tele, email, mdps;
    Button env;
    Spinner type, ville,sexe;
    database db=new database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        cin=(EditText)findViewById(R.id.cin);
        nom=(EditText)findViewById(R.id.nom);
        prenom=(EditText)findViewById(R.id.prenom);
        sexe=(Spinner)findViewById(R.id.sexe);
        age=(EditText)findViewById(R.id.age);
        poids=(EditText)findViewById(R.id.poids);
        tele=(EditText)findViewById(R.id.telephone);
        email=(EditText)findViewById(R.id.email);
        mdps=(EditText)findViewById(R.id.mdps);
        env=(Button)findViewById(R.id.ajouter);
        type=(Spinner)findViewById(R.id.type_s);
        ville=(Spinner)findViewById(R.id.ville);
        ArrayAdapter ar = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getListVille());
        ville.setAdapter(ar);
        ArrayAdapter ar1 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getListtype());
        type.setAdapter(ar1);
        ArrayAdapter ar2 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,
                db.getListsexe());
        sexe.setAdapter(ar2);
        env.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insert( cin.getText().toString(), nom.getText().toString(), prenom.getText().toString(), sexe.getSelectedItem().toString(), age.getText().toString(), poids.getText().toString(), type.getSelectedItem().toString(), ville.getSelectedItem().toString(), tele.getText().toString(), email.getText().toString(), mdps.getText().toString());
                Toast.makeText(getApplicationContext(),"Ajouter effecter avec succes",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(inscription.this,connection.class);
                startActivity(intent);
            }
        });
    }
}
