package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.MaskFormatter;


import model.dto.Product;
import model.dto.Order;

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
    private List<Product> products;
    private JTable table;
    private JPanel painel;
	private JTextField nomeField,  qtdField;
    private JButton botaoSalvar,  botaoRemover, botaoAdicionarProd, botaoRemoveProd,botaoMais,  botaoVerSacola,  botaoQuantidade, botaoMenos;
    private JFormattedTextField textFieldData;
    MaskFormatter cepMask;
    
    public TelaCliente() {
   	
    	super("Menu");

        getContentPane().setBackground(amareloQueimado);

        painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(amareloQueimado);
        
        addLabel("Nome:", 20, 20, 100, 30);    
        nomeField = new JTextField();
        nomeField.setForeground(marrom);
        nomeField.setBounds(120, 20, 160, 20);
        add(nomeField);
        
        addLabel("Telefone:", 20, 50, 100, 20);
        
        try {			
        	phoneMask = new MaskFormatter("(##) #####-####");
        	
        	telefoneField =  new JFormattedTextField(phoneMask);;
	        telefoneField.setForeground(marrom);
	        telefoneField.setBounds(120, 50, 160, 20);
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
        botaoAdicionarProd.setBounds(80, 340, 150, 30);
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
        painel.add(botaoAdicionarProd);
        
        botaoVerSacola = new JButton("Ver Cesta");
        botaoVerSacola.setForeground(branco);
        botaoVerSacola.setBounds(80, 375, 150, 30);
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
        
        addLabel("Quantidade:", 80, 310, 100, 20);             
        qtdField = new JTextField();
        qtdField.setForeground(marrom);
        qtdField.setBounds(155, 310, 20, 20);
        add(qtdField);
        
	    botaoMais = new JButton("+");
	    botaoMais.setForeground(branco);
	    botaoMais.setBounds(180, 310, 50,10);
	    botaoMais.setBackground(marrom);
	    painel.add(botaoMais);	      
	      
	    botaoMenos = new JButton("-");
	    botaoMenos.setForeground(branco);
	    botaoMenos.setBounds(180, 320, 50, 10);
	    botaoMenos.setBackground(marrom);
	    painel.add(botaoMenos);
              
//        botaoSalvar = new JButton("Salvar");
//        botaoSalvar.setForeground(branco);
//        botaoSalvar.setBounds(10, 390, 90, 30);
//        botaoSalvar.setBackground(marrom);
//        botaoSalvar.addActionListener(this);
//        painel.add(botaoSalvar);
        
//        botaoRemover = new JButton("Remover");
//        botaoRemover.setForeground(branco);
//        botaoRemover.setBounds(110, 390, 90, 30);
//        botaoRemover.setBackground(marrom);
//        botaoRemover.addActionListener(this);
//        painel.add(botaoRemover);
//        
        JButton voltarButton = new JButton("Sair");
        voltarButton.setBounds(80, 410, 150, 30);
        voltarButton.setBackground(marrom);
        voltarButton.setForeground(branco);
        voltarButton.addActionListener(new ActionListener() {
        	
        	@Override
            public void actionPerformed(ActionEvent e) {
               
        		if(e.getSource() == voltarButton) {
        			dispose();
        			new TelaMenu();   			
        		}
        	}
              
        });
        
        painel.add(voltarButton);
        painel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(branco), ""));
        painel.setPreferredSize(new Dimension(300, 460));
       
//		tab();
        add(painel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    

	
	this.products = products;

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    // Criação do modelo de tabela personalizado
    ProductTableModel model = new ProductTableModel(products);

    // Criação da JTable com o modelo de tabela personalizado
    table = new JTable(model);

    JScrollPane scrollPane = new JScrollPane(table);
    panel.add(scrollPane, BorderLayout.CENTER);

    getContentPane().add(panel);

}

// Modelo de tabela personalizado para os produtos
private class ProductTableModel extends AbstractTableModel {
	Order order = new Order();
    private final String[] columnNames = {"ID", "Nome", "Descrição", "Preço"};
    private List<Product> products = order.getProducts() ;

    public ProductTableModel(List<Product> products) {
        this.products = products;
    }

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Product product = products.get(row);

        switch (col) {
            case 0:
                return product.getId();
            case 1:
                return product.getName();
            case 2:
                return product.getDescription();
            case 3:
                return product.getUnit_price();
            default:
                return null;
        }
    }
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}


    
//    @SuppressWarnings("deprecation")
//	public void actionPerformed(ActionEvent e) {
//    	
//    	Cliente cliente = new Cliente();
//        Compra compra = new Compra();
//        Funcionario funcionario = new Funcionario(); 
//        CompraDAO compradao = new CompraDAO();
//
//        if (e.getSource() == botaoSalvar) {
//        	     	
//        	try {
//        		
//	            String idFuncionario = comboBoxFunc.getSelectedItem().toString();
//	            String idCliente = comboBoxCliente.getSelectedItem().toString();
//	            String data = textFieldData.getText();
//	            
//	            Date date = new Date(data);
//	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//	            String dataFormatada = sdf.format(date);
//	        
//		           
//	    //      	Setagem dos identificadores:
//		
//		    		funcionario.setId(Long.parseLong(idFuncionario));
//		        	cliente.setId(Long.parseLong(idCliente));
//		
//		//        	Setagem de produto:
//		        		
//		        	compra.setValorTotal(valorTotal);
//		        	compra.setDate(new Date(dataFormatada));
//		        	compra.setFuncionario(funcionario);
//		        	compra.setCliente(cliente);
//		        
//					compra.setProdutos(produtos);
//					
//					compradao.salvar(compra);
//					
//					JOptionPane.showMessageDialog(null, "Compra adicionada com sucesso!");
//		
//	          
//	          
//        	} catch (PersistenciaDacException e1) {
//				e1.printStackTrace();
//			}
//        } else if (e.getSource() == botaoRemover) {
//        	dispose();
//        	new RemoverCompra();
//        }
//    }
//    
//    	private DefaultTableModel modelo;
//		private JTable tabela;
//		private String id;
//		private List<Produto> produtos1;
//		private List<Funcionario> funcionarios;
//		private List<Cliente> clientes;
//		private ProdutoDAO produtoDao;
//		private FuncionarioDAO funcDao;
//		private ClienteDAO clienteDao;
//		private int linhaSelecionada;
//		private List<Produto> produtos = new ArrayList<>();
//		private Double valorTotal = (double) 0;
//		
//     public void tab() {   	
//	    //colunas da lista 
//	    modelo  = new DefaultTableModel();
//	    modelo.addColumn("name");
//        modelo.addColumn("description");
//        modelo.addColumn("unit_price");
//
//        produtoDao = new ProdutoDAO();
//        
//        try {
//      	produtos1 = produtoDao.getProdutos();    	
//      	if(produtos1.size() > 0){
//            
//        	for(Produto produto : produtos1){             
//        		Object[] linha = new Object[5];
//                
//                linha[0] = produto.getId();
//                linha[1] = produto.getTipoProduto().name();
//                linha[2] = produto.getTipoTamanho().name();
//                linha[3] = produto.getCor();
//                linha[4] = produto.getValor();
//                
//                modelo.addRow(linha);
//            }
//        }    
//	        
//		} catch (PersistenciaDacException e) {
//			e.printStackTrace();
//		}
//        
//        tabela = new JTable(modelo);
//        
//        tabela.addMouseListener(new MouseListener() {
//			
//				public void mouseReleased(MouseEvent e) {}
//				public void mousePressed(MouseEvent e) {}
//				public void mouseExited(MouseEvent e) {}
//				public void mouseEntered(MouseEvent e) {}
//				
//				public void mouseClicked(MouseEvent e) {
//				
//					linhaSelecionada = tabela.getSelectedRow();
//		
//					if(linhaSelecionada != -1) {
//						id = tabela.getValueAt(linhaSelecionada, 0).toString();
//					}else {
//						JOptionPane.showMessageDialog(null,"Selecione um produto");
//					}
//		            tabela.repaint();
//				}
//			});
//	        
//        JScrollPane painelTabela = new JScrollPane(tabela);
//	    painelTabela.setBounds(10, 150, 250, 153);
//	    add(painelTabela);  
//	}
//     
//     public Produto obterProduto() {
//		
//    	 try {
//				
//    		 Produto pE = produtoDao.obterID(Long.parseLong(id));
//			 
//    		 produtos1 = produtoDao.getProdutos();
//			
//				boolean achou = false;
//			 
//				if(produtos1.size() > 0){
//           
//					for(Produto p : produtos1){
//					 
//						if(p.equals(pE)){
//							achou = true;
//							break;
//						}
//					}
//					if(achou) {
//						return pE;
//					}
//				}
//			} catch (PersistenciaDacException e) {
//				e.printStackTrace();
//			}
//			 return null;
//	}
// 
//}
	


