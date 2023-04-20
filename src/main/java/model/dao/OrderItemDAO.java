package model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import model.PcoException;
import model.dto.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO extends DAO {

    public void save(OrderItem orderItem) throws PcoException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(orderItem);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PcoException("Ocorreu algum erro ao tentar salvar o item do pedido.", pe);
        } finally {
            em.close();
        }
    }

    public OrderItem update(OrderItem orderItem) throws PcoException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        OrderItem resultado = orderItem;
        try {
            resultado = em.merge(orderItem);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PcoException("Ocorreu algum erro ao tentar atualizar o item do pedido.", pe);
        } finally {
            em.close();
        }
        return resultado;
    }

    public void delete(OrderItem orderItem) throws PcoException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            orderItem = em.find(OrderItem.class, orderItem.getId());
            em.remove(orderItem);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PcoException("Ocorreu algum erro ao tentar remover o item do pedido.", pe);
        } finally {
            em.close();
        }
    }

    public OrderItem getByID(Long orderItemId) throws PcoException {
        EntityManager em = getEntityManager();
        OrderItem resultado = null;
        try {
            resultado = em.find(OrderItem.class, orderItemId);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            throw new PcoException("Ocorreu algum erro ao tentar recuperar o item do pedido com base no ID.", pe);
        } finally {
            em.close();
        }

        return resultado;
    }

    public List<OrderItem> getAll() throws PcoException {
        EntityManager em = getEntityManager();
        List<OrderItem> resultado = null;
        try {
            TypedQuery<OrderItem> query = em.createQuery("SELECT o FROM OrderItem o", OrderItem.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            throw new PcoException("Ocorreu algum erro ao tentar recuperar todos os itens dos pedidos.", pe);
        } finally {
            em.close();
        }
        return resultado;
    }

    public List<OrderItem> findItemsByOrder(Integer orderID) throws PcoException {
        EntityManager em = getEntityManager();
        List<OrderItem> orderItems = new ArrayList<>();
        Long id = (long) orderID;
        try {
            em.getTransaction().begin();
            String jpql = "SELECT o FROM OrderItem o WHERE o.order.id = :id";
            TypedQuery<OrderItem> query = em.createQuery(jpql, OrderItem.class);
            query.setParameter("id", id);
            orderItems = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PcoException("Ocorreu algum erro ao tentar recuperar itens de produtos.", e);
        } finally {
            em.close();
        }
        return orderItems;
    }

    public List<OrderItem> findItemsByOrder(long orderID) throws PcoException {
        EntityManager em = getEntityManager();
        List<OrderItem> orderItems = new ArrayList<>();
        try {
            em.getTransaction().begin();
            String jpql = "SELECT o FROM OrderItem o WHERE o.order.id = :orderID";
            TypedQuery<OrderItem> query = em.createQuery(jpql, OrderItem.class);
            query.setParameter("id", orderID);
            orderItems = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PcoException("Ocorreu algum erro ao tentar recuperar itens de produtos.", e);
        } finally {
            em.close();
        }
        return orderItems;
    }

}
