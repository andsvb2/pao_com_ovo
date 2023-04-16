package controller;

import view.TelaCadastroFuncionario;
import view.TelaLoginFuncionario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OuvinteCadastroFuncionario implements ActionListener {
    TelaLoginFuncionario t;
    public OuvinteCadastroFuncionario(TelaLoginFuncionario telaLoginFuncionario) {
        t = telaLoginFuncionario;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new TelaCadastroFuncionario("Cadastro: cadastre um funcionário");
        t.dispose();
    }
}
