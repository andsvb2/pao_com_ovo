package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.JanelaPadrao;
import view.TelaFuncionario;
import view.TelaMenu;

public class OuvinteVoltarParaMenu implements ActionListener {
	JanelaPadrao t;
	public OuvinteVoltarParaMenu(JanelaPadrao tela) {
		t = tela;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		new TelaMenu();
		t.dispose();
	}

}
