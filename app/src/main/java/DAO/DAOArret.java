package DAO;


import java.util.List;

import beans.Arret;

public interface DAOArret extends DAO<Arret> {
    Arret findByNom(String nomArret);
}
