package com.example.hitman.sang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Hashtable;

public class connection extends AppCompatActivity {
    EditText login,passowrd;
    Button connection;
    database db=new database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        login = (EditText) findViewById(R.id.login);
        passowrd = (EditText) findViewById(R.id.password);
        connection = (Button) findViewById(R.id.connecter);
        connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hashtable<String, String> ht = db.connection(login.getText().toString(), passowrd.getText().toString());
                if (ht == null)
                    Toast.makeText(getApplicationContext(), "user name ou password est incorrect",Toast.LENGTH_LONG).show();
                else {
                    if (ht.get("type").equals("u")) {
                        Intent intent = new Intent(connection.this, utilisateur.class);
                        intent.putExtra("cin", ht.get("cin"));
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(connection.this, admin.class);
                        intent.putExtra("cin", ht.get("cin"));
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
