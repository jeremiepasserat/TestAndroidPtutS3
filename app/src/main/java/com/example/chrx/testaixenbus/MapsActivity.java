package com.example.chrx.testaixenbus;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ArrayList<String> coordonnees = getCoordonnees();

    }

    public static ArrayList<String> getCoordonnees() {
        String arret_url = "http://ptutequipe2g1.alwaysdata.net/coord_arret.php";
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
            while ((line = bufferedReader.readLine()) != null) {
                resultats.add(line);
            }

            bufferedReader.close();
            inputStream.close();
            huc.disconnect();


        } catch (MalformedURLException e) {
            System.out.println("Malformed URL Exception");
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return resultats;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Recupere les coordonnées ordonnées de la BD
        ArrayList<String> latitudes = getLatitude(getCoordonnees());
        ArrayList<String> longitudes = getLongitude(getCoordonnees());
        ArrayList<String> nomArrets = getNomArret(getCoordonnees());



        // Ajoute ces coordonnées à la carte
        for (int i = 0; i < latitudes.size(); ++i) {
            LatLng latlng = new LatLng(Double.parseDouble(latitudes.get(i)), Double.parseDouble(longitudes.get(i)));
            mMap.addMarker(new MarkerOptions().position(latlng).title(nomArrets.get(i)));
  //          System.out.println(nomArrets.get(i));
          //  mMap.addMarker(new MarkerOptions().position(latlng));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));


        }

        // Ajoute un marqueur à l'IUT et zoome dessus
        LatLng iut = new LatLng(43.514591, 5.451379);
        mMap.addMarker(new MarkerOptions().position(iut).title("Vous êtes ici"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(iut));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(iut, 14));
    }

    public ArrayList<String> getLatitude(ArrayList<String> coordonnees) {
        ArrayList<String> latitudes = new ArrayList<>();
        ArrayList<String> tmp = new ArrayList<>();

       /* for (String s : coordonnees)
        {
            int i = 0;
            while(s.charAt(i) != '*') {
                ++i;
            }
            System.out.println(s.substring(0,i-1));
            System.out.println(s.substring(i+2));
        }*/

        //Traitement pour enlever les noms d'arrêt des Strings

        for (String s : coordonnees)
        {
            int i = 0;
            while (s.charAt(i) != '*') {
                ++i;
            }
            tmp.add(s.substring(i+2));
        }

        // Isolement des latitudes grâce à l'ArrayList Retraitée
        for (String s : tmp) {
            int i = 0;
            while (s.charAt(i) != ' ') {
                ++i;
            }
            latitudes.add(s.substring(0, i - 1));

        }


        return latitudes;
    }

    public ArrayList<String> getLongitude(ArrayList<String> coordonnees) {
        ArrayList<String> longitudes = new ArrayList<>();
        ArrayList<String> tmp = new ArrayList<>();


        //Traitement pour enlever les noms d'arrêt des Strings

        for (String s : coordonnees)
        {
            int i = 0;
            while (s.charAt(i) != '*') {
                ++i;
            }
            tmp.add(s.substring(i+2));
        }

        // Isolement des latitudes grâce à l'ArrayList Retraitée

        for (String s : tmp) {
            int i = 0;
            while (s.charAt(i) != ' ') {
                ++i;
            }
            longitudes.add(s.substring(i + 1));
        }
        return longitudes;
    }

    public ArrayList<String> getNomArret (ArrayList<String> coordonnees)
    {
        ArrayList<String> arrets = new ArrayList<>();

        for (String s : coordonnees) {
            int i = 0;
            while (s.charAt(i) != '*') {
                ++i;
            }
            arrets.add(s.substring(0, i - 1));
        }
        return arrets;
    }
}

