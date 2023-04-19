package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.OrderController;
import model.PcoException;
import model.dto.Order;
import model.enums.PaymentStatus;

public class TelaPagamentos extends JanelaPadrao{
	
	private Color marrom = new Color(160,82,45);
	private Color branco = new Color(255,250,240);
	private Color amarelo = new Color(240,230,140);
	private JButton botaoFuncinario;
	MaskFormatter numeroMask, expedicaoMask, cvvMask; 
	JTextField numeroField, expedicaoField, cvvField;
	private Order order;
	
	public TelaPagamentos(Order order) {
		super("Dados do pagamento");
		super.setSize(300,455); 
		this.order = order;
		conf();
		setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void conf() {	
		int font = 16;
		int eixoX = 12;
		super.addLabel("Cartão de crédito/débito", "Serif", 50,1,490,60,20,amarelo);
		super.addLabel("Número:","Serif", eixoX,60,110,20,font,branco);
		super.addLabel("Data de expedição:","Serif", eixoX,90,140,20,font,branco);
		super.addLabel("CVV: ","Serif", eixoX,120,100,20,font,branco);
		super.addLabel("Tranferência pix (QR-CODE)", "Serif", 25,140,490,60,20,amarelo);

		addButton("Realizar pagamento", 110,380,165,30);
		addButton("Sair", eixoX,380,90, 30);	
		
		try {	
        	numeroMask = new MaskFormatter("#### #### #### ####");      	
        	numeroField =  new JFormattedTextField(numeroMask);;
	        numeroField.setForeground(marrom);
	        numeroField.setBounds(75, 62, 190, 20);
	        add(numeroField);
	        
	        expedicaoMask = new MaskFormatter("##/####");      	
        	expedicaoField =  new JFormattedTextField(expedicaoMask);;
	        expedicaoField.setForeground(marrom);
	        expedicaoField.setBounds(140, 92, 125, 20);
	        add(expedicaoField);
	        
	        cvvMask = new MaskFormatter("###");      	
        	cvvField = new JFormattedTextField(cvvMask);;
	        cvvField.setForeground(marrom);
	        cvvField.setBounds(60, 122, 205, 20);
	        add(cvvField);
		
        } catch (ParseException e) {
			e.printStackTrace();
		}

	        // Cria um JLabel com uma imagem
	        ImageIcon imagem = new ImageIcon("main/java/view/assets/qr_code_padaria_pao_com_ovo.png");
	        JLabel label = new JLabel();
	        label.setIcon(imagem);
	        label.setBounds(60, 122, 205, 20);
	        add(label);
	}
	
	
	private JButton addButton (String nome, int x, int y, int a, int l) {
		JButton button = new JButton(nome);
		button.setForeground(branco);
		button.setBounds(x, y, a,l);
		button.setBackground(marrom);
		add(button);
		button.addActionListener(new Ouvinte());
		return button;
	}

	public class Ouvinte implements ActionListener{		
		public void actionPerformed(ActionEvent e) {
			OrderController orderController = new OrderController();
			String botao = e.getActionCommand();	
			
		switch (botao) { 			
			case "Realizar pagamento":
				JOptionPane.showMessageDialog(null, "Pagamento realizado com sucesso!");
				orderController.payOrder(order, PaymentStatus.APROVADO);
				try {
				new TelaCliente();
			} catch (PcoException e1) {
				e1.printStackTrace();
			}
				dispose();
				break;
			case "Sair":
				dispose();
		}		
		
	}
		
	}
}