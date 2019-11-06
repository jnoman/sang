package com.example.hitman.sang;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Hashtable;

public class info_donneur extends AppCompatActivity {
    Button mail,appele;
    TextView nom,sexe,age,email,tel;
    database db=new database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_donneur);
        Intent intent1=getIntent();
        if(intent1!=null) {
            Bundle bundle = intent1.getExtras();
            if (bundle != null) {
                String cin = bundle.getString("cin");
                appele = (Button) findViewById(R.id.appele);
                mail = (Button) findViewById(R.id.mail);
                nom = (TextView) findViewById(R.id.info_nom);
                sexe = (TextView) findViewById(R.id.info_sexe);
                age = (TextView) findViewById(R.id.info_age);
                tel = (TextView) findViewById(R.id.info_telephone);
                email = (TextView) findViewById(R.id.info_email);
                Hashtable<String, String> hashtable = db.getdonneur(cin);
                nom.setText(hashtable.get("nom"));
                sexe.setText(hashtable.get("sexe"));
                age.setText(hashtable.get("age"));
                tel.setText(hashtable.get("telephone"));
                email.setText(hashtable.get("email"));
                appele.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + tel.getText()));
                        startActivity(intent);
                    }
                });
                mail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", email.getText().toString(), null));
                        startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                    }
                });
            }
        }
    }
}
