package view;

import model.dto.Order;

public class TelaDetalhamentoCompra extends JanelaPadrao{
	private Order order;
	
	public TelaDetalhamentoCompra(String nome, Order o) {
		super(nome);
		this.order = o;
	}
	

}
