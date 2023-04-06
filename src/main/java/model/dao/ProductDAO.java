package model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import model.PcoException;
import model.dto.Product;

import java.util.List;

public class ProductDAO  extends DAO {

    public void save(Product product) throws PcoException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(product);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PcoException("Ocorreu algum erro ao tentar salvar o produto.", pe);
        } finally {
            em.close();
        }
    }

    public Product update(Product product) throws PcoException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Product resultado = product;
        try {
            resultado = em.merge(product);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PcoException("Ocorreu algum erro ao tentar atualizar o produto.", pe);
        } finally {
            em.close();
        }
        return resultado;
    }

    public void delete(Product product) throws PcoException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            product = em.find(Product.class, product.getId());
            em.remove(product);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PcoException("Ocorreu algum erro ao tentar remover o produto.", pe);
        } finally {
            em.close();
        }
    }

    public Product getByID(int productId) throws PcoException {
        EntityManager em = getEntityManager();
        Product resultado = null;
        try {
            resultado = em.find(Product.class, productId);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            throw new PcoException("Ocorreu algum erro ao tentar recuperar o produto com base no ID.", pe);
        } finally {
            em.close();
        }

        return resultado;
    }

    public List<Product> getAll() throws PcoException {
        EntityManager em = getEntityManager();
        List<Product> resultado = null;
        try {
            TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            throw new PcoException("Ocorreu algum erro ao tentar recuperar todos os produtos.", pe);
        } finally {
            em.close();
        }
        return resultado;
    }
}
