package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.PcoException;
import model.dao.OrderDAO;
import model.dto.Order;
import view.TelaDetalhamentoCompra;
import view.TelaFuncionario;

public class OuvinteDetalhesPedido implements ActionListener {
	TelaFuncionario tela;
	public OuvinteDetalhesPedido(TelaFuncionario f) {
		tela = f;
	}
	
	public void actionPerformed(ActionEvent e) {
		Order o = null;
		try {
			o =tela.getOrderDao().getByID(tela.getId());
		} catch (PcoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		new TelaDetalhamentoCompra("Detalhamento", o);
	}

}
