package com.example.chrx.testaixenbus.Applications;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chrx.testaixenbus.R;

import java.util.ArrayList;

import DAO.DAOArret;
import DAO.DAOFactory;
import DAO.DAOFactoryProducer;
import DAO.JDBC.DAOFactoryJDBC;

/**
 * Created by chrx on 05/12/17.
 */

public class Arret extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arret);

        TextView textView = findViewById(R.id.textView);
        textView.setText("ouqyegzfiqzebgf");

        DAOArret daoArret = DAOFactoryProducer.getFactory("JDBC").createDAOArret();
        ArrayList<String> arrets = new ArrayList<>();
        //daoArret.getById(5);
//        System.out.println(daoArret.FindAll().size());
/*
        for(int i = 0; i < daoArret.FindAll().size(); ++i)
        {
            arrets.add(daoArret.FindAll().get(i).getNomArret());
            textView.setText(arrets.get(i));
        }*/


        Button button = findViewById(R.id.accueil);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Arret.this, Menu.class);
                startActivity(intent);

            }});
        //Intent intent = new Intent(Menu.this, Itineraire.class);
        //startActivity(intent);
    }
}
