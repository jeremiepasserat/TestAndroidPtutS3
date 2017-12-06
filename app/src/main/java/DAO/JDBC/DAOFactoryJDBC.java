package DAO.JDBC;


import DAO.DAOArret;
import DAO.DAOFactory;
import DAO.DAOHoraire;
import DAO.DAOLigne;

public class DAOFactoryJDBC implements DAOFactory {
    @Override
    public DAOHoraire createDAOHoraire() {
        return new DAOHoraireJDBC();
    }

    @Override
    public DAOLigne createDAOLigne() {
        return new DAOLigneJDBC();
    }

    @Override
    public DAOArret createDAOArret() {
        return new DAOArretJDBC();
    }
}