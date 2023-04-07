package view;

import javax.swing.JButton;

public class TelaFuncionario extends JanelaPadrao{
	private JButton botaoSair;
	public TelaFuncionario(String nome) {
		super(nome);
		adJbutton();
	}
	
	private void adJbutton() {
		botaoSair = new JButton("Voltar");
		botaoSair.setBounds(80, 410, 150, 30);
		
	}

}
