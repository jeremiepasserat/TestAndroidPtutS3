package DAO.JDBC;

import DAO.DAOArret;
import DAO.DAOFactoryProducer;

/**
 * Created by chrx on 05/12/17.
 */

public class TestDAOArretJDBC {

    public static void main (String[] args){
        DAOArret daoArrettest = DAOFactoryProducer.getFactory("JDBC").createDAOArret();
//    daoArrettest.FindAll();
        if (daoArrettest instanceof DAOArret)
            System.out.println("DAOArret is an instance of DAOArret");
    }


}
