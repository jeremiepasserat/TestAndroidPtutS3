package com.example.chrx.testaixenbus.Applications;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.chrx.testaixenbus.R;

public class Menu extends AppCompatActivity {

    static boolean setNotifs = false;

    public static boolean getStateNotifs (){
        return setNotifs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

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

        final ToggleButton notifications = findViewById(R.id.notifications);
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notifications.isChecked()) {
                    setNotifs = true;
                    Toast.makeText(Menu.this, "Notifications activées", Toast.LENGTH_LONG).show();
                }
                else
                {
                    setNotifs = false;
                    Toast.makeText(Menu.this, "Notifications desactivées", Toast.LENGTH_LONG).show();
                }
                }
        });
        //Intent intent = new Intent(Menu.this, Itineraire.class);
        //startActivity(intent);
    }
}
