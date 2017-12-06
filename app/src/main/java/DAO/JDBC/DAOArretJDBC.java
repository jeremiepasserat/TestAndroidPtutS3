package DAO.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.ConnexionUnique;
import DAO.DAOArret;
import beans.Arret;

public class DAOArretJDBC implements DAOArret {
    private String reqFindAll = "SELECT * " + " FROM ARRET";
    private String reqArretByID = " SELECT * " + " FROM ARRET" + " WHERE NUM_ARRET = ? ";
    private String reqArretParNom = " SELECT * " + " FROM ARRET" + " WHERE NOM_ARRET = ? ";

    @Override
    public List<Arret> FindAll() {
        ArrayList<Arret> arrets = new ArrayList<>();
       // try {
            Connection connexion = ConnexionUnique.getInstance().getConnection();
            if (connexion instanceof Connection)
                System.out.println("connexion instance de Connection");
            /*
            PreparedStatement statement = connexion.prepareStatement(reqFindAll);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Arret arret = new Arret();
                arret.setNumArret(resultSet.getInt("NUM_ARRET"));
                arret.setNomArret(resultSet.getString("NOM_ARRET"));
                arret.setAccessible(resultSet.getBoolean("IS_ACCESSIBLE"));
                arrets.add(arret);
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return arrets;
    }

    @Override
    public Arret getById(int id) {
        Arret arret = new Arret();
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch(Exception e){
            System.err.println("Cannot create connection");
        }
        try {
            Connection connexion = ConnexionUnique.getInstance().getConnection();
            PreparedStatement statement = connexion.prepareStatement(reqArretByID);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            arret.setNumArret(resultSet.getInt("NUM_ARRET"));
            arret.setNomArret(resultSet.getString("NOM_ARRET"));
            arret.setAccessible(resultSet.getBoolean("IS_ACCESSIBLE"));
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arret;
    }

    @Override
    public Arret findByNom(String nomArret) {
        Arret arret = new Arret();
        try {
            Connection connexion = ConnexionUnique.getInstance().getConnection();
            PreparedStatement statement = connexion.prepareStatement(reqArretParNom);
            statement.setString(1, nomArret);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            arret.setNumArret(resultSet.getInt("NUM_ARRET"));
            arret.setNomArret(resultSet.getString("NOM_ARRET"));
            arret.setAccessible(resultSet.getBoolean("IS_ACCESSIBLE"));
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arret;
    }
}
