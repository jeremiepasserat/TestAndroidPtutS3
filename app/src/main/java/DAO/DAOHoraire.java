package DAO;


import java.util.List;

import beans.Horaire;

public interface DAOHoraire extends DAO<Horaire> {

    Horaire getById(int id, double heure);
    List<Double> getHoraires(int id);
}
