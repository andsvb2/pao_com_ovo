package view;

import java.awt.Color;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.OuvinteDeletarPedido;
import controller.OuvinteVoltarTelaInicial;
import jakarta.persistence.PersistenceException;
import model.dto.Order;
import model.dto.Product;

public class TelaDetalhamentoCompra extends JanelaPadrao{
	private JButton botaoSair;
	private JButton botaoDeletarCompra;
	private Color magnetta = new Color(255,250,240);
	private Color orchid = new Color(160,82,45);
	private Order order;
	private DefaultTableModel modelo;
	private JTable tabela;
	private double precoFinal = 0;
	private String totalPreco= "O preço final, é: "  + precoFinal ;
	
	public TelaDetalhamentoCompra(String nome, Order o) {
		super(nome);
		super.setSize(520, 440);
		this.order = o;
		addTabela();
		addBotoes();
		addLabel(totalPreco, "Serif", 20, 490, 200, 30, 30, magnetta);
		this.setVisible(true);
	}

	private void addBotoes(){
		botaoSair = new JButton("Voltar");
		botaoSair.setBounds(20, 360, 100, 30);
		this.add(botaoSair);
		botaoSair.setForeground(magnetta);
		botaoSair.setBackground(orchid);
		botaoSair.addActionListener(new OuvinteVoltarTelaInicial(this));
		botaoDeletarCompra = new JButton("Finalizar");
		botaoDeletarCompra.setBounds(390, 360, 100, 30);
		botaoDeletarCompra.setForeground(magnetta);
		botaoDeletarCompra.setBackground(orchid);
		botaoDeletarCompra.addActionListener(new OuvinteDeletarPedido(this));
		add(botaoDeletarCompra);
	}

	
	private void addTabela(){
		modelo = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("telefone");
		modelo.addColumn("Produto");
		modelo.addColumn("Descrição");
		modelo.addColumn("Quantidade");
		try {
			if (order.getProducts().size()> 0) {
				for (Product produto : order.getProducts()) {
					Object[] linha = new Object[5];
					int quantidade = 0;
					for(Product p: order.getProducts()){
						if (produto.equals(p))
							quantidade+=1;
					}
					linha[0] = order.getCustomer_name();
					linha[1] = order.getCustomer_phone();
					linha[2] = produto.getName();
					linha[3] = produto.getDescription();
					linha[4] = quantidade;
					precoFinal += produto.getUnit_price();
					modelo.addRow(linha);
				}
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		tabela = new JTable(modelo);
		JScrollPane painelPane = new JScrollPane(tabela);
		painelPane.setBounds(20, 20, 470, 330);
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

//	public double getPrecoFinal() {
//		return precoFinal;
//	}
}
