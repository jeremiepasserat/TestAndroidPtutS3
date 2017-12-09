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
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.example.chrx.testaixenbus.Applications.Arret.Test;

/**
 * Created by chrx on 05/12/17.
 */

public class Ligne extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ligne);

        ArrayList<String> test2 = TestLigne();

        Spinner spinner = findViewById(R.id.spinner2);
        List exemple = new ArrayList();

        for(int c = 0; c < test2.size(); ++c) {
            exemple.add(test2.get(c));
        }

        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                exemple
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final TextView textView = findViewById(R.id.horairesLigne);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                String position_nom = parent.getItemAtPosition(position).toString();
                ArrayList<String> listeLignesparArret = getHorairesLigne(position_nom);
                String resultats = "";
                for (String s : listeLignesparArret)
                {
                    resultats += "Horaire : n°" + s + "\n";
                }
                textView.setText(resultats);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });



        Button button = findViewById(R.id.accueil);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Ligne.this, Menu.class);
                startActivity(intent);

            }
        });
    }

        public static ArrayList<String> getHorairesLigne(String ligne)
        {
            String ligne_url = "http://ptutequipe2g1.alwaysdata.net/horaires_ligne.php";
            ArrayList<String> resultats = new ArrayList<>();
            try {
                URL url = new URL(ligne_url);
                HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                huc.setRequestMethod("POST");
                huc.setDoInput(true);
                huc.setDoOutput(true);
                OutputStream outputStream = huc.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String pos_data = URLEncoder.encode("ligne", "UTF-8")+"="+URLEncoder.encode(ligne, "UTF-8");
                bufferedWriter.write(pos_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                // Récupération des informations de la base de données en fct de la requete présente dans lignes_arrets.php
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

    public static ArrayList<String> TestLigne()
    {
        String ligne_url = "http://ptutequipe2g1.alwaysdata.net/all_lignes.php";
        ArrayList<String> resultats = new ArrayList<>();
        try {
            URL url = new URL(ligne_url);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("POST");
            huc.setDoInput(true);
            huc.setDoOutput(true);

            // Récupération des informations de la base de données en fct de la requete présente dans lignes_arrets.php
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

