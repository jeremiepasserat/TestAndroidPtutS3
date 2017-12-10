package com.example.chrx.testaixenbus.Applications;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chrx.testaixenbus.R;

/**
 * Created by chrx on 05/12/17.
 */

public class Bug extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bug);

        Button button = findViewById(R.id.accueil);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Bug.this, Menu.class);
                startActivity(intent);

            }});

        Button envoi = findViewById(R.id.ebug);
        envoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Bug.this, "Merci de votre aide !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Bug.this, Menu.class);
                startActivity(intent);

            }});

        //Intent intent = new Intent(Menu.this, Itineraire.class);
        //startActivity(intent);
    }
}