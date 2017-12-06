package DAO.JDBC;



import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.ConnexionUnique;
import DAO.DAOArret;
import DAO.DAOLigne;
import beans.Ligne;

import static java.sql.Types.NULL;

public class DAOLigneJDBC implements DAOLigne {
    private String reqFindAll = "SELECT * " + " FROM LIGNE";
    private String reqLigneByID = " SELECT * " + " FROM LIGNE" + " WHERE NUM = ? ";
    private String reqFindParArret = " SELECT * " + " FROM LIGNE " + " WHERE ARRET1 = ? "
            + " OR ARRET2 = ? " + " OR ARRET3 = ? " + " OR ARRET4 = ? " + " OR ARRET5 = ? " + " OR ARRET6 = ? ";
    private String reqFindLigneCommunesArrets = " SELECT * " + " FROM LIGNE " + " WHERE ARRET1 = ? "
            + " OR ARRET2 = ? " + " OR ARRET3 = ? " + " OR ARRET4 = ? " + " OR ARRET5 = ? " + " OR ARRET6 = ? "
            + " AND NUM IN " + " ( SELECT NUM " + " FROM LIGNE " + " WHERE ARRET1 = ? "
            + " OR ARRET2 = ? " + " OR ARRET3 = ? " + " OR ARRET4 = ? " + " OR ARRET5 = ? " + " OR ARRET6 = ? )";
    private String reqArretsParLigne = " SELECT * " + " FROM LIGNE " + " WHERE NUM = ? ";

    @Override
    public List<Ligne> FindAll() {
        List<Ligne> lignes = new ArrayList<>();
        try {
            Connection connexion = ConnexionUnique.getInstance().getConnection();
            PreparedStatement statement = connexion.prepareStatement(reqFindAll);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Ligne ligne = new Ligne();
                ligne.setNumLigne(resultSet.getInt("NUM"));
                int i = 1;
                DAOArret daoArret = new DAOArretJDBC();
                while (i < 7 && resultSet.getInt("ARRET" + Integer.toString(i)) != NULL) {
                    ligne.addArret(daoArret.getById(resultSet.getInt("ARRET" + Integer.toString(i))));
                    i += 1;
                }
                lignes.add(ligne);
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lignes;
    }

    @Override
    public Ligne getById(int id) {
        Ligne ligne = new Ligne();
        try {
            Connection connexion = ConnexionUnique.getInstance().getConnection();
            PreparedStatement statement = connexion.prepareStatement(reqLigneByID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            ligne.setNumLigne(resultSet.getInt("NUM"));
            int i = 1;
            DAOArret daoArret = new DAOArretJDBC();
            while (i < 7 && resultSet.getInt("ARRET" + Integer.toString(i)) != NULL) {
                ligne.addArret(daoArret.getById(resultSet.getInt("ARRET" + Integer.toString(i))));
                i += 1;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ligne;
    }

    @Override
    public List<Integer> findByArret(int numArret) {
        List<Integer> numLignes = new ArrayList<Integer>();
        try {
            Connection connexion = ConnexionUnique.getInstance().getConnection();
            PreparedStatement statement = connexion.prepareStatement(reqFindParArret);
            for (int i = 1; i <= 6; ++i) {
                statement.setInt(i, numArret);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                numLignes.add(resultSet.getInt("NUM"));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numLignes;
    }

    @Override
    public List<Integer> findLigneCommuneArrets(int numArret1, int numArret2) {
        List<Integer> numLignes = new ArrayList<Integer>();
        try {
            Connection connexion = ConnexionUnique.getInstance().getConnection();
            PreparedStatement statement = connexion.prepareStatement(reqFindLigneCommunesArrets);
            for (int i = 1; i <= 6; ++i) {
                statement.setInt(i, numArret1);
            }
            for (int i = 7; i <= 12; ++i) {
                statement.setInt(i, numArret2);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                numLignes.add(resultSet.getInt("NUM"));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numLignes;
    }

    @Override
    public List<String> findArretsParLigne(int numLigne) {
        List<String> nomArrets = new ArrayList<>();
        DAOFactoryJDBC daoFactory = new DAOFactoryJDBC();
        DAOArret daoArret = daoFactory.createDAOArret();
        try {
            Connection connexion = ConnexionUnique.getInstance().getConnection();
            PreparedStatement statement = connexion.prepareStatement(reqArretsParLigne);
            statement.setInt(1, numLigne);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int i = 1;
            while (i < 7 && resultSet.getInt("ARRET" + Integer.toString(i)) != NULL){
                nomArrets.add(daoArret.getById(resultSet.getInt("ARRET" + Integer.toString(i))).getNomArret());
                ++i;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomArrets;
    }
}