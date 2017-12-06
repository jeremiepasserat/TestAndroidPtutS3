package DAO;


import java.util.List;

import beans.Ligne;

public interface DAOLigne extends DAO<Ligne> {
    List<Integer> findByArret(int numArret);
    List<Integer> findLigneCommuneArrets(int numArret1, int numArret2);
    List<String> findArretsParLigne(int numLigne);
}
