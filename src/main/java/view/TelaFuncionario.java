package view;

import java.awt.Color;

import javax.swing.JButton;

import controller.OuvinteVoltarParaMenu;

public class TelaFuncionario extends JanelaPadrao{
	private JButton botaoSair;
	private Color magnetta = new Color(255,250,240);
	private Color orchid = new Color(160,82,45);
	public TelaFuncionario(String nome) {
		super(nome);
		adJbutton();
		addTabela();
		setVisible(true);
	}
	
	private void addTabela() {
		
	}
	
	private void adJbutton() {
		botaoSair = new JButton("Voltar");
		botaoSair.setBounds(120, 410, 150, 30);
		this.add(botaoSair);
		botaoSair.setForeground(magnetta);
		botaoSair.setBackground(orchid);
		botaoSair.addActionListener(new OuvinteVoltarParaMenu(this));
	}

	public JButton getBotaoSair() {
		return botaoSair;
	}

}
