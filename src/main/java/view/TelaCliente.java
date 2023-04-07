package view;

import java.awt.BorderLayout;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.text.MaskFormatter;

import jakarta.persistence.PersistenceException;

import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.text.SimpleDateFormat;

import model.PcoException;
import model.dao.OrderDAO;
import model.dao.ProductDAO;
import model.dto.Order;
import model.dto.Product;

public class TelaCliente extends JFrame implements ActionListener {
    
	private Color marrom = new Color(160,82,45);
	private Color branco = new Color(255,250,240);
	private Color amareloQueimado = new Color(205,133,63);
	private Color amarelo = new Color(240,230,140);
	
    private MaskFormatter phoneMask;
    private JFormattedTextField telefoneField;

	 
    public JLabel addLabel(String nome, int x, int y, int a, int l) {     
 	     JLabel nomeLabel = new JLabel(nome);
         nomeLabel.setForeground(branco);
         nomeLabel.setBounds(x, y, a, l);
         add(nomeLabel);        
 		 return nomeLabel;	
 	}

    private JPanel painel;
	private JTextField nomeField,  qtdField;
    private JButton botaoSalvar,  botaoRemover, botaoAdicionarProd, botaoRemoveProd,botaoMais,  botaoVerSacola,  botaoQuantidade, botaoMenos;
    private JFormattedTextField textFieldData;
    MaskFormatter cepMask;
    
    public TelaCliente() throws PcoException {
   	
    	super("Menu");

        getContentPane().setBackground(amareloQueimado);

        painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(amareloQueimado);
        
        addLabel("Nome:", 20, 15, 100, 30);    
        nomeField = new JTextField();
        nomeField.setForeground(marrom);
        nomeField.setBounds(60, 20, 140, 20);
        add(nomeField);
        
        addLabel("Telefone:", 215, 20, 100, 20);
        
        try {			
        	phoneMask = new MaskFormatter("(##)#####-####");
        	
        	telefoneField =  new JFormattedTextField(phoneMask);;
	        telefoneField.setForeground(marrom);
	        telefoneField.setBounds(280, 20, 140, 20);
	        add(telefoneField);

		
        } catch (ParseException e) {
			e.printStackTrace();
		}
        
//Adicinar remoção e adição de produtos a "Sou funcionario"
        
//        botaoNovoProd = new JButton("+");
//        botaoNovoProd.setForeground(magnetta);
//        botaoNovoProd.setBounds(30, 310, 50, 20);
//        botaoNovoProd.setBackground(orchid);
//        botaoNovoProd.addActionListener(new ActionListener() {
//        	
//        	@Override
//            public void actionPerformed(ActionEvent e) {
//               
//        		if(e.getSource() == botaoNovoProd) {
//        			dispose();
//        			//new CadastroProduto();   			
//        		}
//        	}
//              
//        });
//        
//        painel.add(botaoNovoProd);
//        
//        botaoRemoveProd = new JButton("-");
//        botaoRemoveProd.setForeground(magnetta);
//        botaoRemoveProd.setBounds(90, 310, 50, 20);
//        botaoRemoveProd.setBackground(orchid);
//        botaoRemoveProd.addActionListener(new ActionListener() {
//        	
//        	@Override
//            public void actionPerformed(ActionEvent e) {
//               
//        		if(e.getSource() == botaoRemoveProd) {
//        			
//        			try {
//        				Produto pE = obterProduto();
//						produtoDao.remove(pE);						
//						textFieldValorTotal.setText(Double.toString(0.0));					
//						modelo.removeRow(linhaSelecionada);
//		    	    	tabela.repaint();						
//						JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
//        				
//        			}catch (PersistenciaDacException e1) {
//        				e1.printStackTrace();
//        			}
//        		}
//        	}  
//        });
//        painel.add(botaoRemoveProd);
        
        botaoAdicionarProd = new JButton("Adicionar à cesta");
        botaoAdicionarProd.setForeground(branco);
        botaoAdicionarProd.setBounds(20, 370, 150, 30);
        botaoAdicionarProd.setBackground(marrom);
//        botaoAdicionarProd.addActionListener(new ActionListener() {
        	
//       	@Override
//            public void actionPerformed(ActionEvent e) {             
//        		if(e.getSource() == botaoAdicionarProd) {      			
//        			Produto pE = obterProduto();       			
//        			valorTotal += pE.getValor();      			
//        			textFieldValorTotal.setText(Double.toString(valorTotal));       	
//           			produtos.add(pE);          			
//           			JOptionPane.showMessageDialog(null, "Produto adicioando no carrinho");
//        		}
//        	}
//              
//        });
        
        
//        public Produto obterProduto() {   		
//       	 try {
//   				
//       		 Produto pE = produtoDao.obterID(Long.parseLong(id));			 
//       		 produtos1 = produtoDao.getProdutos();
//   			
//   				boolean achou = false;		 
//   				if(produtos1.size() > 0){
//              
//   					for(Produto p : produtos1){
//   					 
//   						if(p.equals(pE)){
//   							achou = true;
//   							break;
//   						}
//   					}
//   					if(achou) {
//   						return pE;
//   					}
//   				}
//   			} catch (PersistenciaDacException e) {
//   				e.printStackTrace();
//   			}
//   			 return null;
//   	}
//    
//   }
//   	


        painel.add(botaoAdicionarProd);
        
        botaoVerSacola = new JButton("Ver Cesta");
        botaoVerSacola.setForeground(branco);
        botaoVerSacola.setBounds(180, 370, 110, 30);
        botaoVerSacola.setBackground(marrom);
//      botaoVerSacola.addActionListener(new ActionListener() {    	
//     	@Override
//          public void actionPerformed(ActionEvent e) {
//             
//      		if(e.getSource() == botaoVerSacola) {      			
//      			
//      		}
//      	}
//            
//      });
        painel.add(botaoVerSacola);
        
//        addLabel("Quantidade:", 80, 310, 100, 20);             
//        qtdField = new JTextField();
//        qtdField.setForeground(marrom);
//        qtdField.setBounds(155, 310, 20, 20);
//        add(qtdField);
//        
//	    botaoMais = new JButton("+");
//	    botaoMais.setForeground(branco);
//	    botaoMais.setBounds(180, 310, 50,10);
//	    botaoMais.setBackground(marrom);
//	    painel.add(botaoMais);	      
//	      
//	    botaoMenos = new JButton("-");
//	    botaoMenos.setForeground(branco);
//	    botaoMenos.setBounds(180, 320, 50, 10);
//	    botaoMenos.setBackground(marrom);
//	    painel.add(botaoMenos);
                   
        JButton voltarButton = new JButton("Cancelar");
        voltarButton.setBounds(300, 370, 120, 30);
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
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 			
        		}
        	}
              
        });
        
        painel.add(voltarButton);
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
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
 
    	private DefaultTableModel modelo;
		private JTable tabela;

		private String id;
		private Product produto;
		private ProductDAO productDao = new ProductDAO();
		private OrderDAO orderDao = new OrderDAO();
		
		private List<Product>produtos1 = new ArrayList<>();
		private List<Product> produtos2 = new ArrayList<>();
		
		private int linhaSelecionada;
		
     public void tab() throws PcoException {   	
	    //colunas da lista 
	    modelo  = new DefaultTableModel();
	    modelo.addColumn("Nome");
        modelo.addColumn("Descrição");
        modelo.addColumn("Preço");
        
        try {
	      	produtos1 = productDao.getAll();	
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
						id = tabela.getValueAt(linhaSelecionada, 0).toString();
					}else {
						JOptionPane.showMessageDialog(null,"Selecione um produto");
					}
		            tabela.repaint();
				}
			});
	        
        JScrollPane painelTabela = new JScrollPane(tabela);
	    painelTabela.setBounds(20, 60, 400, 300);
	    add(painelTabela);  
     }

     
}