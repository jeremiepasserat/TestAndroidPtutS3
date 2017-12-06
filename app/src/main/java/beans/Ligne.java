package beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Baptiste on 02/12/2017.
 */
public class Ligne {
    private int numLigne;
    private List<Arret> arrets;

    public Ligne() {
        arrets = new ArrayList<>();
    }

    public Ligne(int numLigne) {
        this.numLigne = numLigne;
        arrets = new ArrayList<>();
    }

    public void addArret (Arret arret) {
        arrets.add(arret);
    }

    public List<Arret> getArrets() {
        return arrets;
    }

    public void setNumLigne(int numLigne) {
        this.numLigne = numLigne;
    }

    public int getNumLigne() {
        return numLigne;
    }
}