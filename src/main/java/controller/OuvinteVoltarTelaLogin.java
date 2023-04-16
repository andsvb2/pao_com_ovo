package controller;

import view.*;

import java.awt.event.*;

public class OuvinteVoltarTelaLogin implements ActionListener {
    private TelaCadastroFuncionario t;
    public OuvinteVoltarTelaLogin(TelaCadastroFuncionario telaCadastroFuncionario) {
        t = telaCadastroFuncionario;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        new TelaLoginFuncionario("Login: Selecione uma opção");
        t.dispose();
    }
}
