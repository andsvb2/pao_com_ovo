package model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import model.PcoException;
import model.dto.Order;
import model.dto.Product;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;

public class OrderDAO extends DAO {

    public void save(Order order) throws PcoException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            order.setCreation_time(LocalDateTime.now());
            em.persist(order);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PcoException("Ocorreu algum erro ao tentar salvar o pedido.", pe);
        } finally {
            em.close();
        }
    }

    public Order update(Order order) throws PcoException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        order.setCreation_time(LocalDateTime.now());
        Order resultado = order;
        try {
            resultado = em.merge(order);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PcoException("Ocorreu algum erro ao tentar atualizar o pedido.", pe);
        } finally {
            em.close();
        }
        return resultado;
    }

    public void delete(Order order) throws PcoException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            order = em.find(Order.class, order.getId());
            em.remove(order);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PcoException("Ocorreu algum erro ao tentar remover o pedido.", pe);
        } finally {
            em.close();
        }
    }

    public Order getByID(Long orderId) throws PcoException {
        EntityManager em = getEntityManager();
        Order resultado = null;
        try {
            resultado = em.find(Order.class, orderId);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            throw new PcoException("Ocorreu algum erro ao tentar recuperar o pedido com base no ID.", pe);
        } finally {
            em.close();
        }

        return resultado;
    }

    public List<Order> getAll() throws PcoException {
        EntityManager em = getEntityManager();
        List<Order> resultado = null;
        try {
            TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o", Order.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            throw new PcoException("Ocorreu algum erro ao tentar recuperar todos os pedidos.", pe);
        } finally {
            em.close();
        }
        return resultado;
    }

}
