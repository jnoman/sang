package com.example.hitman.sang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class message extends AppCompatActivity {
    ListView list_message;
    String cin;
    Button envouyer_msg;
    EditText text_msg;
    database db=new database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Intent intent1=getIntent();
        if(intent1!=null) {
            Bundle bundle = intent1.getExtras();
            if (bundle != null) {
                cin = bundle.getString("cin");
                if (cin != null) {
                    list_message=(ListView) findViewById(R.id.list_message);
                    envouyer_msg=(Button)findViewById(R.id.envoyer_message);
                    text_msg=(EditText)findViewById(R.id.new_message);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            this,
                            android.R.layout.simple_list_item_1,
                            db.getListmessage(cin));
                    list_message.setAdapter(adapter);
                    envouyer_msg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean b = db.envoyer_message(cin, text_msg.getText().toString());
                            if (b == true)
                                Toast.makeText(message.this, "message envoyer", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(message.this, "echec d'envoyer", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }
    }
}
