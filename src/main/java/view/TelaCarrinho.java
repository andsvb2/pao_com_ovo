package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import jakarta.persistence.PersistenceException;
import model.PcoException;
import model.dao.OrderDAO;
import model.dao.ProductDAO;
import model.dto.*;

public class TelaCarrinho extends JanelaPadrao {

	private Order order;	

	
	public TelaCarrinho(Order o) {
		super("Carrinho");
		this.order = o;		
	}
	
//	private DefaultTableModel modelo;
//	private JTable tabela;
//	private String id;
//	private Product produto;
//	private ProductDAO productDao = new ProductDAO();
//	private OrderDAO orderDao = new OrderDAO();		
//	private List<Product>produtos1 = new ArrayList<>();		
//	private int linhaSelecionada;
//	
//	 public void tab() throws PcoException {   	
//
//	    modelo  = new DefaultTableModel();
//	    modelo.addColumn("Nome");
//	    modelo.addColumn("Descrição");
//	    modelo.addColumn("Preço");
//	    
//	    try {
//	      	produtos1 = productDao.getAll();	
//	      	if(produtos1.size() > 0){
//	            
//	        	for(Product produto : produtos1){             
//	        		Object[] linha = new Object[3];
//	                
//	                linha[0] = produto.getName();
//	                linha[1] = produto.getDescription();
//	                linha[2] = produto.getUnit_price();
//	                
//	                modelo.addRow(linha);
//	            }
//	       }    	        
//		} catch (PersistenceException e) {
//			e.printStackTrace();
//		}
//	    
//	    tabela = new JTable(modelo); 
//	
//		public List<Product> getProdutos() {
//			return produtos;
//		}
//	
//	
//		public void setProdutos(List<Product> produtos) {
//			this.produtos = produtos;
//		}


	
}
