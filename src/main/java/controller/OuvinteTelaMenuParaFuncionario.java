package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.TelaFuncionario;
import view.TelaMenu;

public class OuvinteTelaMenuParaFuncionario implements ActionListener{
	private TelaMenu tela;
	public OuvinteTelaMenuParaFuncionario(TelaMenu t) {
		tela = t;
	}
	public void actionPerformed(ActionEvent e) {
		new TelaFuncionario("Tela Funcionario");
		tela.dispose();
	}


}
