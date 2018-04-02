package com.example.chrx.testaixenbus.Applications;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chrx.testaixenbus.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by chrx on 05/12/17.
 */

public class Itineraire extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itineraire);

        Spinner spinnerD = findViewById(R.id.spinner3);
        Spinner spinnerA = findViewById(R.id.spinner4);


        Button button = findViewById(R.id.accueil);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Itineraire.this, Menu.class);
                startActivity(intent);

            }});


        ArrayAdapter <String> adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        final TextView textView = findViewById(R.id.textView);

        spinnerD.setAdapter(adapter);

        spinnerD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                String position_nom = Integer.toString(position+1);
                ArrayList<String> listeArrets = Test();
                String resultats = "";
                for (String s : listeArrets)
                {
                    resultats += "Ligne n°" + s + "\n";
                }
                textView.setText(resultats);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Itineraire.this, "blablalba", Toast.LENGTH_SHORT).show();
            }
        });
/*

        spinnerA.setAdapter(adapter);

        spinnerA.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                String position_nom = Integer.toString(position+1);
                ArrayList<String> listeArrets = Test();
                String resultats = "";
                for (String s : listeArrets)
                {
                    resultats += "Ligne n°" + s + "\n";
                }
                textView.setText(resultats);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });*/
        //Intent intent = new Intent(Menu.this, Itineraire.class);
        //startActivity(intent);
    }

    public static ArrayList<String> Test (){
        String arret_url = "http://ptutequipe2g1.alwaysdata.net/all_arret.php";
        ArrayList<String> resultats = new ArrayList<>();


        try {
            URL url = new URL(arret_url);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("POST");
            huc.setDoInput(true);
            huc.setDoOutput(true);
            InputStream inputStream = huc.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                resultats.add(line);
            }

            bufferedReader.close();
            inputStream.close();
            huc.disconnect();


        } catch (MalformedURLException e) {
            System.out.println("Malformed URL Exception");
        }catch (IOException e) {
            System.out.println("IOException");
        }
        return resultats;
    }
}
