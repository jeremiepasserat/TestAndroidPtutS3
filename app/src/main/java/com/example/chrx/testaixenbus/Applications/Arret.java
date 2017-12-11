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

/**
 * Created by chrx on 05/12/17.
 */

public class Arret extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arret);

        Spinner spinner = findViewById(R.id.spinner);

        TextView etatArret = findViewById(R.id.etatArret);



        List exemple = new ArrayList();

        ArrayList<String> test = Test();


        for(int c = 0; c < test.size(); ++c) {
            if (test.get(c).charAt(0) == '1')
                etatArret.setText("Arret disponible");
            else
                etatArret.setText("Arret non disponible");
            exemple.add(test.get(c).substring(1));
        }

        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                exemple
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final TextView textView = findViewById(R.id.textView);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                  Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                                                  String position_nom = Integer.toString(position+1);
                                                    ArrayList<String> listeLignesparArret = getLignesArret(position_nom);
                                                    String resultats = "";
                                                    for (String s : listeLignesparArret)
                                                    {
                                                        resultats += "Ligne n°" + s + "\n";
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

                Intent intent = new Intent(Arret.this, Menu.class);
                startActivity(intent);

            }});

    Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(Arret.this, MapsActivity.class);
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

    public static ArrayList<String> getLignesArret (String arret){
        String arret_url = "http://ptutequipe2g1.alwaysdata.net/lignes_arret.php";
        ArrayList<String> resultats = new ArrayList<>();


        try {
            URL url = new URL(arret_url);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("POST");
            huc.setDoInput(true);
            huc.setDoOutput(true);
            OutputStream outputStream = huc.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String pos_data = URLEncoder.encode("arret", "UTF-8")+"="+URLEncoder.encode(arret, "UTF-8");
            bufferedWriter.write(pos_data);
            bufferedWriter.flush();
            bufferedWriter.close();
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

