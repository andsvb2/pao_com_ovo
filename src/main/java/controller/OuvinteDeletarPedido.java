package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import jakarta.persistence.criteria.Order;
import model.PcoException;
import model.dao.OrderDAO;
import view.TelaDetalhamentoCompra;
import view.TelaMenu;

public class OuvinteDeletarPedido implements ActionListener {
	TelaDetalhamentoCompra t;
	
	public OuvinteDeletarPedido(TelaDetalhamentoCompra tela) {
		t = tela;
	}
	public void actionPerformed(ActionEvent e) {
		OrderDAO orderDAO = new OrderDAO();
		try {
			orderDAO.delete(t.getOrder());
			
		} catch (PcoException e1) {
			e1.printStackTrace();
		}
		t.dispose();
		new TelaMenu();
	}

}
