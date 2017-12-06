package DAO.JDBC;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAO.ConnexionUnique;
import DAO.DAOHoraire;
import beans.Horaire;

public class DAOHoraireJDBC implements DAOHoraire {

    final String reqFindAll = "SELECT * " + "FROM HORAIRE ";
    final String reqHorairesByLigne = " SELECT * " + " FROM HORAIRE " + " WHERE NUM_LIGNE = ? " + " AND HORAIRE_ARR1 >= ? ";
    private String reqTousHorairesLigne = " SELECT * " + " FROM HORAIRE " + " WHERE NUM_LIGNE = ? ";


    @Override
    public List<Horaire> FindAll() {

        ArrayList<Horaire> horaires = new ArrayList<>();

        try {
            Connection connexion = ConnexionUnique.getInstance().getConnection();
            PreparedStatement statement = connexion.prepareStatement(reqFindAll);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Horaire horaire = new Horaire.BuilderHoraire(1)
                        .addHoraireArr1(resultSet.getDouble("HORAIRE_ARR1"))
                        .addHoraireArr2(resultSet.getDouble("HORAIRE_ARR2"))
                        .addHoraireArr3(resultSet.getDouble("HORAIRE_ARR3"))
                        .addHoraireArr4(resultSet.getDouble("HORAIRE_ARR4"))
                        .addHoraireArr5(resultSet.getDouble("HORAIRE_ARR5"))
                        .addHoraireArr6(resultSet.getDouble("HORAIRE_ARR6"))
                        .build();
                horaires.add(horaire);
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return horaires;
    }

    @Override
    public Horaire getById(int id) {
        return null;
        //Cette méthode ne peut pas être implémentée puisque la clef primaire de notre BD, est constituée d'un couple (identificateur, heure de départ)
        //Nous utilisons alors la surcharge avec la méthode ci dessous.
    }


    public List<Double> getHoraires(int id){
        List<Double> horaires = new ArrayList<Double>();
        try {
            Connection connexion = ConnexionUnique.getInstance().getConnection();
            PreparedStatement statement = connexion.prepareStatement(reqTousHorairesLigne);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                horaires.add(resultSet.getDouble("HORAIRE_ARR1"));
                horaires.add(resultSet.getDouble("HORAIRE_ARR2"));
                horaires.add(resultSet.getDouble("HORAIRE_ARR3"));
                horaires.add(resultSet.getDouble("HORAIRE_ARR4"));
                horaires.add(resultSet.getDouble("HORAIRE_ARR5"));
                horaires.add(resultSet.getDouble("HORAIRE_ARR6"));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return horaires;
    }

    @Override
    public Horaire getById(int id, double heure) {
        Horaire horaire = new Horaire();
    //Le traitement ici, ne peut être réalisé à l'aide d'un Builder puisque ce dernier n'est pas réalisé à l'instanciation mais bien en fonction de paramètres effectifs.
        try {
            Connection connexion = ConnexionUnique.getInstance().getConnection();
            PreparedStatement statement = connexion.prepareStatement(reqHorairesByLigne);
            statement.setInt(1,id);
            statement.setDouble(2,heure);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            horaire.setNumLigne(resultSet.getInt("NUM_LIGNE"));
            horaire.setHoraireArr1(resultSet.getDouble("HORAIRE_ARR1"));
            horaire.setHoraireArr2(resultSet.getDouble("HORAIRE_ARR2"));
            horaire.setHoraireArr3(resultSet.getDouble("HORAIRE_ARR3"));
            horaire.setHoraireArr4(resultSet.getDouble("HORAIRE_ARR4"));
            horaire.setHoraireArr5(resultSet.getDouble("HORAIRE_ARR5"));
            horaire.setHoraireArr6(resultSet.getDouble("HORAIRE_ARR6"));

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return horaire;
    }


}
