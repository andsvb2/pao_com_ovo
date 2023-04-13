package model.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import model.PcoException;
import model.dto.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends DAO {

    public void save(Employee employee) throws PcoException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(employee);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PcoException("Ocorreu algum erro ao tentar salvar o funcionário.", pe);
        } finally {
            em.close();
        }
    }

    public Employee update(Employee employee) throws PcoException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Employee resultado = employee;
        try {
            resultado = em.merge(employee);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PcoException("Ocorreu algum erro ao tentar atualizar o funcionário.", pe);
        } finally {
            em.close();
        }
        return resultado;
    }

    public void delete(Employee employee) throws PcoException {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            employee = em.find(Employee.class, employee.getId());
            em.remove(employee);
            transaction.commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new PcoException("Ocorreu algum erro ao tentar remover o funcionário.", pe);
        } finally {
            em.close();
        }
    }

    public Employee getByID(int employeeId) throws PcoException {
        EntityManager em = getEntityManager();
        Employee resultado = null;
        try {
            resultado = em.find(Employee.class, (long) employeeId);
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            throw new PcoException("Ocorreu algum erro ao tentar recuperar o funcionário com base no ID.", pe);
        } finally {
            em.close();
        }

        return resultado;
    }

    public List<Employee> getAll() throws PcoException {
        EntityManager em = getEntityManager();
        List<Employee> resultado = new ArrayList<>();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
            resultado = query.getResultList();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            throw new PcoException("Ocorreu algum erro ao tentar recuperar todos os funcionários.", pe);
        } finally {
            em.close();
        }
        return resultado;
    }

    public List<Employee> findEmployeesByName(String name) throws PcoException {
        EntityManager em = getEntityManager();
        List<Employee> employees = new ArrayList<>();
        try {
            em.getTransaction().begin();
            String jpql = "SELECT e FROM Employee e WHERE e.name = :name";
            TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
            query.setParameter("name", name);
            employees = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PcoException("Ocorreu algum erro ao tentar recuperar funcionários.", e);
        } finally {
            em.close();
        }
        return employees;
    }
}
