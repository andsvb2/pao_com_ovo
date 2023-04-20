package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import jakarta.persistence.PersistenceException;
import model.PcoException;
import model.dao.*;
import model.dto.*;

public class TelaCliente extends JFrame {

	private Color marrom = new Color(160,82,45);
	private Color amareloQueimado = new Color(205,133,63);
	private Color branco = new Color(255,250,240);
	private MaskFormatter phoneMask;
	private JFormattedTextField telefoneField;
	private JPanel painel;
	private JTextField nomeField, buscaField, qtdField;
	private JButton botaoAdicionarProd, botaoVerSacola, buscaButton, botaoMais, botaoMenos;
	private Double valorTotal = (double) 0;

	private DefaultTableModel modelo;
	private JTable tabela;
	private Long id;
	private Product produto;
	private ProductDAO productDao = new ProductDAO();
	private OrderDAO orderDao = new OrderDAO();
	private List<Product>produtos1 = new ArrayList<>();
	private int linhaSelecionada;
	private Order order = new Order();
	int quantity = 1;

	public JLabel addLabel(String nome, int x, int y, int a, int l) {
		JLabel nomeLabel = new JLabel(nome);
		nomeLabel.setForeground(branco);
		nomeLabel.setBounds(x, y, a, l);
		add(nomeLabel);
		return nomeLabel;
	}

	public TelaCliente() throws PcoException {
		super("Cliente: carrinho de compras");
		getContentPane().setBackground(amareloQueimado);
		this.setIconImage(new javax.swing.ImageIcon("src/main/java/view/assets/icon.png").getImage());
		painel = new JPanel();
		painel.setLayout(null);
		painel.setBackground(amareloQueimado);

		addLabel("Nome:", 20, 15, 100, 30);
		nomeField = new JTextField();
		nomeField.setForeground(marrom);
		nomeField.setBounds(60, 20, 140, 20);
		add(nomeField);

		try {
			addLabel("Telefone:", 215, 20, 100, 20);
			phoneMask = new MaskFormatter("(##) #####-####");
			telefoneField =  new JFormattedTextField(phoneMask);;
			telefoneField.setForeground(marrom);
			telefoneField.setBounds(280, 20, 140, 20);
			add(telefoneField);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		botaoAdicionarProd = new JButton("Adicionar à cesta");
		botaoAdicionarProd.setForeground(branco);
		botaoAdicionarProd.setBounds(20, 370, 150, 30);
		botaoAdicionarProd.setBackground(marrom);
		botaoAdicionarProd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botaoAdicionarProd) {
					try {
						OrderItem orderItem = new OrderItem();
						orderItem.setProduct(productDao.getByID(id));
						orderItem.setQuantity(quantity);
						order.addOrderItem(orderItem);
						order.setCustomerName(nomeField.getText());
						order.setCustomerPhone(telefoneField.getText());
						botaoVerSacola.setEnabled(true);
					} catch (PcoException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Produto adicionado ao carrinho.");
				}
			}

		});


		painel.add(botaoAdicionarProd);

		botaoVerSacola = new JButton("Ver cesta");
		botaoVerSacola.setForeground(branco);
		botaoVerSacola.setBounds(180, 370, 110, 30);
		botaoVerSacola.setBackground(marrom);
		botaoVerSacola.setEnabled(false);
		botaoVerSacola.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botaoVerSacola) {
					if(!nomeField.getText().trim().equals("") && !telefoneField.getText().trim().equals("()-")) {
						new TelaCarrinho(order);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Digite seu nome e seu telefone");
					}
				}

			}});
		painel.add(botaoVerSacola);

		int eixoX = 320;
		addLabel("Quantidade:", 120, eixoX, 100, 20);
		qtdField = new JTextField();
		qtdField.setForeground(marrom); qtdField.setBounds(195, eixoX, 20, 20);
		qtdField.setText(Integer.toString(quantity));

		botaoMais = new JButton("+"); botaoMais.setForeground(branco);
		botaoMais.setBounds(220, eixoX, 50,10); botaoMais.setBackground(marrom);
		botaoMais.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botaoMais) {
					qtdField.setText(Integer.toString(quantity = quantity + 1));
				}

			}
		});
		painel.add(botaoMais);

		botaoMenos = new JButton("-"); botaoMenos.setForeground(branco);
		botaoMenos.setBounds(220, eixoX +10, 50, 10); botaoMenos.setBackground(marrom);
		botaoMenos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botaoMenos) {
					qtdField.setText(Integer.toString(quantity = quantity - 1));
				}

			}
		});
		painel.add(botaoMenos);
		quantity = Integer.parseInt(qtdField.getText());
		add(qtdField);
		JButton voltarButton = new JButton("Cancelar");
		voltarButton.setBounds(320, 370, 120, 30);
		voltarButton.setBackground(marrom);
		voltarButton.setForeground(branco);
		voltarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == voltarButton) {
					dispose();
					try {
						new TelaCliente();
					} catch (PcoException e1) {
						e1.printStackTrace();
					}
				}
			}

		});

		painel.add(voltarButton);

		// Adiciona o JTextField para buscar produtos
		buscaField = new JTextField("Buscar produtos");
		buscaField.setForeground(marrom);
		buscaField.setBounds(20, 60, 280, 20);
		add(buscaField);

		// Adiciona o JButton para realizar a busca
		buscaButton = new JButton("Buscar");
		buscaButton.setForeground(branco);
		buscaButton.setBounds(310, 60, 110, 20);
		buscaButton.setBackground(marrom);
		buscaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					produtos1 = productDao.findProductsByName(buscaField.getText());
					tab();
				} catch (PcoException ex) {
					throw new RuntimeException(ex);
				}
			}
		});
		add(buscaButton);


		painel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(branco), ""));
		painel.setPreferredSize(new Dimension(440, 420));
		tab();
		add(painel);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);


	}

	public void tab() throws PcoException {
		//colunas da lista
		modelo  = new DefaultTableModel();
		modelo.addColumn("Nome");
		modelo.addColumn("Descrição");
		modelo.addColumn("Preço");

		try {
			if (produtos1 == null || produtos1.isEmpty()) {
				produtos1 = productDao.getAll();
			}
			if(produtos1.size() > 0){

				for(Product produto : produtos1){
					Object[] linha = new Object[3];

					linha[0] = produto.getName();
					linha[1] = produto.getDescription();
					linha[2] = produto.getUnit_price();

					modelo.addRow(linha);
				}
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		}

		tabela = new JTable(modelo);
		tabela.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {

				linhaSelecionada = tabela.getSelectedRow();

				if(linhaSelecionada != -1) {
					id = produtos1.get(linhaSelecionada).getId();
				}else {
					JOptionPane.showMessageDialog(null,"Selecione um produto.");
				}
				tabela.repaint();
			}
		});

		JScrollPane painelTabela = new JScrollPane(tabela);
		painelTabela.setBounds(20, 100, 400, 200);
		add(painelTabela);
	}
}