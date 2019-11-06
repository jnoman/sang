package com.example.hitman.sang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Hashtable;

public class etat extends AppCompatActivity {
    Button valide,non_valide;
    TextView nom,sexe,age,email,tel;
    database db=new database(this);
    String cin,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etat);
        Intent intent1=getIntent();
        if(intent1!=null) {
            Bundle bundle = intent1.getExtras();
            if (bundle != null) {
                cin = bundle.getString("cin");
                d = bundle.getString("date");
                nom = (TextView) findViewById(R.id.info_nom1);
                sexe = (TextView) findViewById(R.id.info_sexe1);
                age = (TextView) findViewById(R.id.info_age1);
                tel = (TextView) findViewById(R.id.info_telephone1);
                email = (TextView) findViewById(R.id.info_email1);
                valide=(Button)findViewById(R.id.valide);
                non_valide=(Button)findViewById(R.id.non_valide);
                Hashtable<String, String> hashtable = db.getdonneur(cin);
                nom.setText(hashtable.get("nom"));
                sexe.setText(hashtable.get("sexe"));
                age.setText(hashtable.get("age"));
                tel.setText(hashtable.get("telephone"));
                email.setText(hashtable.get("email"));
                valide.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.valide_rendez_vous(cin,d);
                        Intent intent = new Intent(etat.this, liste_donnations.class);
                        startActivity(intent);
                    }
                });
                non_valide.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.Delete_rendez_vous(cin);
                        Intent intent = new Intent(etat.this, liste_donnations.class);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
