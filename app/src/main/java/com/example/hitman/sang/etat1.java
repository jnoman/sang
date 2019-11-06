package com.example.hitman.sang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Hashtable;

public class etat1 extends AppCompatActivity {
    Button valide;
    TextView nom,sexe,age,email,tel;
    database db=new database(this);
    String cin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etat1);
        Intent intent1=getIntent();
        if(intent1!=null) {
            Bundle bundle = intent1.getExtras();
            if (bundle != null) {
                cin = bundle.getString("cin");
                nom = (TextView) findViewById(R.id.info_nom2);
                sexe = (TextView) findViewById(R.id.info_sexe2);
                age = (TextView) findViewById(R.id.info_age2);
                tel = (TextView) findViewById(R.id.info_telephone2);
                email = (TextView) findViewById(R.id.info_email2);
                valide = (Button) findViewById(R.id.supprimer);
                Hashtable<String, String> hashtable = db.getdonneur(cin);
                nom.setText(hashtable.get("nom"));
                sexe.setText(hashtable.get("sexe"));
                age.setText(hashtable.get("age"));
                tel.setText(hashtable.get("telephone"));
                email.setText(hashtable.get("email"));
                valide.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.Delete_donneur(cin);
                        Intent intent = new Intent(etat1.this, liste_donneurs.class);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
