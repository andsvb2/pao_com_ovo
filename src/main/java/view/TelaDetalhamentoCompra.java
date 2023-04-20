package view;

import java.awt.Color;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import controller.OuvinteDeletarPedido;
import controller.OuvinteVoltarTelaInicial;
import jakarta.persistence.PersistenceException;
import model.dto.Order;
import model.dto.OrderItem;
import model.dto.Product;

public class TelaDetalhamentoCompra extends JanelaPadrao{
	private JButton botaoSair;
	private JButton botaoDeletarCompra;
	private Color magnetta = new Color(255,250,240);
	private Color orchid = new Color(160,82,45);
	private Order order = new Order();
	private OrderController orderController = new OrderController();
	private DefaultTableModel modelo;
	private JTable tabela;

	public TelaDetalhamentoCompra(String nome, Order o) {
		super(nome);
		super.setSize(520, 440);
		this.order = o;
		addTabela();
        addBotoes();
        String descricao = "Cliente: "+ o.getCustomerName()+" Telefone: " + o.getCustomerPhone();
        JLabel label = addLabel(descricao, "Serif", 40, 0, 450, 20, 20, magnetta);
		label.setVerticalAlignment(SwingConstants.CENTER);
		add(label);
		this.setVisible(true);
	}

	private void addBotoes(){
		botaoSair = new JButton("Voltar");
		botaoSair.setBounds(20, 370, 100, 30);
		this.add(botaoSair);
		botaoSair.setForeground(magnetta);
		botaoSair.setBackground(orchid);
		botaoSair.addActionListener(new OuvinteVoltarTelaInicial(this));
		botaoDeletarCompra = new JButton("Finalizar");
		botaoDeletarCompra.setBounds(390, 370, 100, 30);
		botaoDeletarCompra.setForeground(magnetta);
		botaoDeletarCompra.setBackground(orchid);
		botaoDeletarCompra.addActionListener(new OuvinteDeletarPedido(this));
		add(botaoDeletarCompra);
	}


	private void addTabela(){
		modelo = new DefaultTableModel();
//		modelo.addColumn("Nome");
//		modelo.addColumn("Telefone");
		modelo.addColumn("Produto");
		modelo.addColumn("Descrição");
		modelo.addColumn("Quantidade");
		modelo.addColumn("Preço");
		modelo.addColumn("Subtotal");
		try {
			if (order.getOrderItems().size()> 0) {
				List<OrderItem> orderItems = order.getOrderItems();
				List<Product> products = orderController.getProducts(order);
				for (OrderItem orderItem : orderItems) {
					Object[] linha = new Object[5];
					int quantidade = 0;
					linha[0] = orderItem.getProduct().getName();
					linha[1] = orderItem.getProduct().getDescription();
					linha[2] = orderItem.getQuantity();
					linha[3] = orderItem.getProduct().getUnit_price();
					linha[4] = orderItem.getQuantity() * orderItem.getProduct().getUnit_price();
					modelo.addRow(linha);
				}
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		tabela = new JTable(modelo);
		JScrollPane painelPane = new JScrollPane(tabela);
		painelPane.setBounds(40, 25, 400, 330);
		add(painelPane);
	}

	public JButton getBotaoSair() {
		return botaoSair;
	}

	public JButton getBotaoDeletarCompra() {
		return botaoDeletarCompra;
	}

	public Color getMagnetta() {
		return magnetta;
	}

	public Color getOrchid() {
		return orchid;
	}

	public Order getOrder() {
		return order;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public JTable getTabela() {
		return tabela;
	}

}
