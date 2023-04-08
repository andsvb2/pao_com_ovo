package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.TelaFuncionario;
import view.TelaMenu;

public class OuvinteVoltarParaMenu implements ActionListener {
	TelaFuncionario t;
	public OuvinteVoltarParaMenu(TelaFuncionario tela) {
		t = tela;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		new TelaMenu();
		t.dispose();
	}

}
