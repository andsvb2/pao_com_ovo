package view;

import controller.OrderController;
import jakarta.persistence.PersistenceException;
import model.PcoException;
import model.dao.OrderDAO;
import model.dto.Order;
import model.dto.OrderItem;
import model.dto.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TelaCarrinho extends JanelaPadrao {


	private Color marrom = new Color(160,82,45);
	private Color amareloQueimado = new Color(205,133,63);
	private Color branco = new Color(255,250,240);

	private JButton botaoAdicionarProd, botaoEfetuarCompra;
	private Order order;

	private OrderController orderController = new OrderController();
	OrderDAO orderdao = new OrderDAO();
	private List<Product>produtos = new ArrayList<>();
	private List<OrderItem> orderItens = new ArrayList<>();

	public TelaCarrinho(Order o) {
		super("Carrinho");
		super.setSize(440, 420);
		this.order = o;
		produtos = orderController.getProducts(o);
		try {
			tab();
		} catch (PcoException e) {
			e.printStackTrace();
		}

		botaoAdicionarProd = new JButton("Salvar");
		botaoAdicionarProd.setForeground(branco);
		botaoAdicionarProd.setBounds(100, 330, 150, 30);
		botaoAdicionarProd.setBackground(marrom);
		botaoAdicionarProd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botaoAdicionarProd) {
					try {
						orderdao.save(order);
					} catch (PcoException e1) {
//								// TODO Auto-generated catch block
						e1.printStackTrace();
					}


					JOptionPane.showMessageDialog(null, "Pedido cadastrado!");

					try {
						new TelaCliente();
					} catch (PcoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					dispose();
				}
			}

		});
		add(botaoAdicionarProd);

		botaoEfetuarCompra = new JButton("Realizar pagamento");
		botaoEfetuarCompra.setForeground(branco);
		botaoEfetuarCompra.setBounds(265, 330, 150, 30);
		botaoEfetuarCompra.setBackground(marrom);
		botaoEfetuarCompra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botaoEfetuarCompra) {
					new TelaPagamentos(order);
					dispose();
				}
			}

		});
		add(botaoEfetuarCompra);
		setVisible(true);
	}

	private DefaultTableModel modelo;
	private JTable tabela;

	public void tab() throws PcoException {
		modelo  = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Descrição");
		modelo.addColumn("Quantidade:");
		modelo.addColumn("Preço");

		try {
			produtos = orderController.getProducts(order);
			orderItens = order.getOrderItems();
			if(produtos.size() > 0){
				for(Product produto : produtos){
					Object[] linha = new Object[4];
					linha[0] = produto.getName();
					linha[1] = produto.getDescription();
					for (OrderItem order : orderItens) {
						linha [2] = order.getQuantity();
						linha[3] = produto.getUnit_price() * order.getQuantity();
						modelo.addRow(linha);
					}
				}
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		}

		tabela = new JTable(modelo);
		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(15, 20, 400, 300);
		add(painelTabela);
	}

	public List<Product> getProdutos() {
		return produtos;
	}


	public void setProdutos(List<Product> produtos) {
		this.produtos = produtos;
	}

}
