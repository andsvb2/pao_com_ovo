package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.TelaDetalhamentoCompra;
import view.TelaFuncionario;

public class OuvinteVoltarTelaInicial implements ActionListener {
	private TelaDetalhamentoCompra t;
	public OuvinteVoltarTelaInicial(TelaDetalhamentoCompra tela) {
		t = tela;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		new TelaFuncionario("Funcionário");
		t.dispose();
	}

}
