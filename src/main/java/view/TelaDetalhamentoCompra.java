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
		addLabel(totalPreco, "Serif", 40, 90, 400, 390, 30, magnetta);
	}

	private void addBotoes(){
		botaoSair = new JButton("Voltar");
		botaoSair.setBounds(430, 250, 80, 30);
		this.add(botaoSair);
		botaoSair.setForeground(magnetta);
		botaoSair.setBackground(orchid);
		botaoSair.addActionListener(new OuvinteVoltarTelaInicial(this));
		botaoDeletarCompra = new JButton("Finalizar");
		botaoDeletarCompra.setBounds(430,200,80,30);
		botaoDeletarCompra.setForeground(magnetta);
		botaoDeletarCompra.setBackground(orchid);
		botaoDeletarCompra.addActionListener(new OuvinteDeletarPedido(this));
	}

	
	private void addTabela(){
		modelo = new DefaultTableModel();
		modelo.addColumn("Produto");
		modelo.addColumn("Descrição");
		modelo.addColumn("Quantidade");
		try {
			if (order.getProducts().size()> 0) {
				for (Product produto : order.getProducts()) {
					Object[] linha = new Object[3];
					linha[0] = produto.getName();
					linha[1] = produto.getDescription();
					linha[2] = produto.getQuantityPerUnit();
					precoFinal += produto.getQuantityPerUnit() * produto.getUnit_price();
					modelo.addRow(linha);
				}
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		tabela = new JTable(modelo);
		JScrollPane painelPane = new JScrollPane(tabela);
		painelPane.setBounds(20, 60, 400, 300);
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

	public double getPrecoFinal() {
		return precoFinal;
	}
}
