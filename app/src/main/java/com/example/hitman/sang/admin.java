package com.example.hitman.sang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class admin extends AppCompatActivity {
    Button donneur,donnation;
    database db=new database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        donneur=(Button)findViewById(R.id.liste_donneur);
        donnation=(Button)findViewById(R.id.liste_donnation);
        donneur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(admin.this,liste_donneurs.class);
                startActivity(intent);
            }
        });
        donnation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(admin.this,liste_donnations.class);
                startActivity(intent);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_message,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_message:
                db.Update_admin();
                Intent intent = new Intent();
                intent.setClass(admin.this, message1.class);
                startActivity(intent);
        }
        return true;

    }
}
