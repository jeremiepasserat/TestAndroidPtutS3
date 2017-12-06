package DAO;

public interface DAOFactory {
    DAOHoraire createDAOHoraire();
    DAOLigne createDAOLigne();
    DAOArret createDAOArret();
}
