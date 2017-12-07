package com.example.chrx.testaixenbus.Applications;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chrx.testaixenbus.R;

/**
 * Created by chrx on 05/12/17.
 */

public class Informations extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informations);

        TextView infos = findViewById(R.id.infos);
        TextView tarif = findViewById(R.id.tarifs);

        tarif.setText(Html.fromHtml(getResources().getString(R.string.tarif)));
        Button button = findViewById(R.id.accueil);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Informations.this, Menu.class);
                startActivity(intent);

            }});
        //Intent intent = new Intent(Menu.this, Itineraire.class);
        //startActivity(intent);

        Button bug = findViewById(R.id.bug);
        bug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Informations.this, Bug.class);
                startActivity(intent);

            }});



    }
}
