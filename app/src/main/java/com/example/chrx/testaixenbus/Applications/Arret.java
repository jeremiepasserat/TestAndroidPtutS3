package com.example.chrx.testaixenbus.Applications;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.chrx.testaixenbus.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrx on 05/12/17.
 */

public class Arret extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arret);

        Spinner spinner = findViewById(R.id.spinner);


        List exemple = new ArrayList();

        ArrayList<String> test = Test();


        for(int c = 0; c < test.size(); ++c) {
            exemple.add(test.get(c));
        }

        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                exemple
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        TextView textView = findViewById(R.id.textView);





        Button button = findViewById(R.id.accueil);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Arret.this, Menu.class);
                startActivity(intent);

            }});}



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

        //Intent intent = new Intent(Menu.this, Itineraire.class);
        //startActivity(intent);
    }

