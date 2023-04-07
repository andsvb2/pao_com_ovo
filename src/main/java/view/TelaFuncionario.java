package view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.OuvinteDetalhesPedido;
import controller.OuvinteVoltarParaMenu;
import jakarta.persistence.PersistenceException;
import model.PcoException;
import model.dao.OrderDAO;
import model.dao.ProductDAO;
import model.dto.Order;
import model.dto.Product;

public class TelaFuncionario extends JanelaPadrao{
	private JButton botaoSair;
	private JButton botaoDetalhes;
	private Color magnetta = new Color(255,250,240);
	private Color orchid = new Color(160,82,45);
	private DefaultTableModel modelo;
	private JTable tabela;
	private Long Id;
	private List<Order> pedidos = new ArrayList<>();
	private ProductDAO productDao = new ProductDAO();
	private OrderDAO orderDao = new OrderDAO();
	
	private int linhaSelecionada;

	public TelaFuncionario(String nome) {
		super(nome);
		super.setSize(500, 440);
		adJbutton();
		try {
			addTabela();
			pedidos = orderDao.getAll();
		} catch (PcoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setVisible(true);
	}
	
	public void addTabela() throws PcoException {   	
	    //colunas da lista 
	    modelo  = new DefaultTableModel();
	    modelo.addColumn("Nome e telefone");
        modelo.addColumn("Produtos");
        
        try {
	      	if(pedidos.size() > 0){
	        	for(Order pedido : pedidos){       
	        		String produtosTxt = "";
	        		Object[] linha = new Object[2];
	        		linha[0] = pedido.getCustomer_name()+ ", " + pedido.getCustomer_phone();
	                for(Product p : pedido.getProducts()) {
		               produtosTxt +=  p.getName();
	                }
	                linha[1] = produtosTxt;
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
						Id = pedidos.get(linhaSelecionada).getId();
					}else {
						JOptionPane.showMessageDialog(null,"Selecione um produto");
					}
		            tabela.repaint();
				}
			});
	        
        JScrollPane painelTabela = new JScrollPane(tabela);
	    painelTabela.setBounds(20, 60, 300, 300);
	    add(painelTabela);  
     }
	
	private void adJbutton() {
		botaoSair = new JButton("Voltar");
		botaoSair.setBounds(120, 410, 150, 30);
		this.add(botaoSair);
		botaoSair.setForeground(magnetta);
		botaoSair.setBackground(orchid);
		botaoSair.addActionListener(new OuvinteVoltarParaMenu(this));
		botaoDetalhes = new JButton("Detalhes");
		botaoDetalhes.setBounds(70,360,150,30);
		botaoDetalhes.setForeground(magnetta);
		botaoDetalhes.setBackground(orchid);
		botaoDetalhes.addActionListener(new OuvinteDetalhesPedido(this));
	}

	public JButton getBotaoDetalhes() {
		return botaoDetalhes;
	}

	public Color getMagnetta() {
		return magnetta;
	}

	public Color getOrchid() {
		return orchid;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public JTable getTabela() {
		return tabela;
	}

	public Long getId() {
		return Id;
	}

	public List<Order> getPedidos() {
		return pedidos;
	}

	public ProductDAO getProductDao() {
		return productDao;
	}

	public OrderDAO getOrderDao() {
		return orderDao;
	}

	public int getLinhaSelecionada() {
		return linhaSelecionada;
	}

	public JButton getBotaoSair() {
		return botaoSair;
	}

}
