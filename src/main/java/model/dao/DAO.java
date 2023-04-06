package model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DAO {

    static EntityManagerFactory emf;

    static  {
        emf = Persistence.createEntityManagerFactory("pco");
    }

    protected EntityManager entityManager() {
        return emf.createEntityManager();
    }

    public void close() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
