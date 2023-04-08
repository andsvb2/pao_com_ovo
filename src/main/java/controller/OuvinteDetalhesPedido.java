package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.PcoException;
import model.dao.OrderDAO;
import model.dto.Order;
import view.TelaDetalhamentoCompra;
import view.TelaFuncionario;

public class OuvinteDetalhesPedido implements ActionListener {
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pco");
	EntityManager entityManager = entityManagerFactory.createEntityManager();	
	TelaFuncionario tela;
	
	public OuvinteDetalhesPedido(TelaFuncionario f) {
		tela = f;
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Order o = new Order();
		o = (Order) searchByID(tela.getId());		
		new TelaDetalhamentoCompra("Detalhamento", o);
		tela.dispose();
		
	}

	 public Object searchByID(long id){
	        //usar a classe que deseja procurar, nesse caso order
	       return entityManager.find(Order.class, id);
	 }
}
