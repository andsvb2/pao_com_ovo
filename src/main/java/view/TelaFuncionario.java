package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import org.hibernate.grammars.hql.HqlParser;

public class TelaFuncionario extends JanelaPadrao{
	private JButton botaoSair;
	private JButton botaoDetalhes;
	private JButton botaoRefresh;
	private Color magnetta = new Color(255,250,240);
	private Color orchid = new Color(160,82,45);
	private DefaultTableModel modelo;
	private JTable tabela;
	private Long Id;
	private ProductDAO productDao = new ProductDAO();
	private OrderDAO orderDao = new OrderDAO();
	private List<Order>pedidos = new ArrayList<>();	
	private int linhaSelecionada;

	public TelaFuncionario(String nome) {
		super(nome);
		super.setSize(525, 440);
		adJbutton();
		try {
			pedidos = orderDao.getAll();
			addTabela();
		} catch (PcoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setVisible(true);
	}
	
	public void addTabela() throws PcoException {   	
	    //colunas da lista
		LocalDateTime localDateTime = LocalDateTime.now();
	    modelo  = new DefaultTableModel();
		modelo.addColumn("Horário");
		modelo.addColumn("Nome");
        modelo.addColumn("Telefone");
		modelo.addColumn("Pagamento");
        
        try {
	      	if(pedidos.size() > 0){
	        	for(Order pedido : pedidos){
	        		Object[] linha = new Object[4];
	        		linha[0] = pedido.getCreation_time().getHour()+":"+ pedido.getCreation_time().getMinute();
					linha[1] = pedido.getCustomer_name();
	                linha[2] = pedido.getCustomer_phone();
					linha[3] = pedido.getPaymentStatus();
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
						botaoDetalhes.setEnabled(true);
					}else {
						JOptionPane.showMessageDialog(null,"Selecione um produto");
					}
		            tabela.repaint();
				}
			});
	        
        JScrollPane painelTabela = new JScrollPane(tabela);
	    painelTabela.setBounds(20, 20, 470, 330);
	    add(painelTabela);  
     }
	private void adJbutton() {
		botaoSair = new JButton("Voltar");
		botaoSair.setBounds(20, 360, 100, 30);
		botaoSair.setForeground(magnetta);
		botaoSair.setBackground(orchid);
		botaoSair.addActionListener(new OuvinteVoltarParaMenu(this));
		this.add(botaoSair);
		
		botaoDetalhes = new JButton("Detalhes");
		botaoDetalhes.setBounds(390,360,100,30);
		botaoDetalhes.setForeground(magnetta);
		botaoDetalhes.setBackground(orchid);
		botaoDetalhes.setEnabled(false);
		botaoDetalhes.addActionListener(new OuvinteDetalhesPedido(this));
		this.add(botaoDetalhes);
		
		botaoRefresh = new JButton("Recarregar");
		botaoRefresh.setBounds(180, 360, 130, 30);
		botaoRefresh.setForeground(magnetta);
		botaoRefresh.setBackground(orchid);
		botaoRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaFuncionario("Funcionário");
				dispose();
			}
		});
		add(botaoRefresh);
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
