package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jakarta.persistence.PersistenceException;
import model.PcoException;
import model.dao.OrderDAO;
import model.dao.ProductDAO;
import model.dto.*;

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
		super.setSize(440, 425);
		this.order = o;	
		produtos = order.getProducts();
		try {
			tab();
		} catch (PcoException e) {
			e.printStackTrace();
		}
		
		 	botaoAdicionarProd = new JButton("Salvar");
	        botaoAdicionarProd.setForeground(branco);
	        botaoAdicionarProd.setBounds(20, 350, 150, 30);
	        botaoAdicionarProd.setBackground(marrom);
	        botaoAdicionarProd.addActionListener(new ActionListener() { 	
	        	
	       	@Override
	            public void actionPerformed(ActionEvent e) {             
	        		if(e.getSource() == botaoAdicionarProd) { 
	        			try {
							orderdao.save(order);
						} catch (PcoException e1) {
							e1.printStackTrace();
						}			
	        			JOptionPane.showMessageDialog(null, "Carrinho cadastrado!");
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
	    painelTabela.setBounds(20, 20, 400, 300);
	    add(painelTabela); 
	 }
	 
     	
		public List<Product> getProdutos() {
			return produtos;
		}
	
	
		public void setProdutos(List<Product> produtos) {
			this.produtos = produtos;
		}


	
}
