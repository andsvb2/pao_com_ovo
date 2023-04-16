package view;

import jakarta.persistence.PersistenceException;
import model.PcoException;
import model.dao.OrderDAO;
import model.dto.Order;
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
	
	private JButton botaoAdicionarProd;
	private Order order;	
	OrderDAO orderdao = new OrderDAO();
	private List<Product>produtos = new ArrayList<>();	
	
	public TelaCarrinho(Order o) {
		super("Carrinho");
		super.setSize(440, 420);
		this.order = o;	
		produtos = order.getProducts();
		try {
			tab();
		} catch (PcoException e) {
			e.printStackTrace();
		}
		
		 	botaoAdicionarProd = new JButton("Salvar");
	        botaoAdicionarProd.setForeground(branco);
	        botaoAdicionarProd.setBounds(265, 330, 150, 30);
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
	        setVisible(true);
	}
	
	private DefaultTableModel modelo;
	private JTable tabela;	
	
	 public void tab() throws PcoException {   	
	    modelo  = new DefaultTableModel();
	    modelo.addColumn("Nome");
	    modelo.addColumn("Descrição");
	    modelo.addColumn("Preço");	    
	    try {
	      	produtos = order.getProducts();
	      	if(produtos.size() > 0){
	            
	        	for(Product produto : produtos){             
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

