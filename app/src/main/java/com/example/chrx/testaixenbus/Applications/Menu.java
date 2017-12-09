package com.example.chrx.testaixenbus.Applications;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chrx.testaixenbus.R;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Button itineraire = findViewById(R.id.itineraire);
        itineraire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Menu.this, Itineraire.class);
                startActivity(intent);

            }});

        Button arrets = findViewById(R.id.arrets);

        arrets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Menu.this, Arret.class);
                startActivity(intent);

            }});

        Button lignes = findViewById(R.id.lignes);

        lignes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Menu.this, Ligne.class);
                startActivity(intent);

            }});

        Button informations = findViewById(R.id.informations);
        informations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Menu.this, Informations.class);
                startActivity(intent);

            }});
        //Intent intent = new Intent(Menu.this, Itineraire.class);
        //startActivity(intent);
    }
}
