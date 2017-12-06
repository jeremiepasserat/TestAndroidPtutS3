package DAO;

import java.util.List;

/**
 * Created by admin on 02/12/2017.
 */
public interface DAO<T> {
    /**
     * Permet de récupérer tous les objets d'une table
     *
     * @return
     */
    List<T> FindAll();

    /**
     * Permet de récupérer un objet via son ID
     *
     * @param id
     * @return
     */
    T getById(int id);
}
