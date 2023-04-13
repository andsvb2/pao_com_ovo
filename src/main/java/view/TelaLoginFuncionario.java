package view;

import controller.OuvinteLoginFuncionario;
import utilites.CreateColors;

import javax.swing.*;

public class TelaLoginFuncionario extends JanelaPadrao{
    private JButton botaoLoginUsuario;
    private JButton cadastroUsuario;
    public TelaLoginFuncionario(String nome) {
        super(nome);
        this.add(addLabel("Bem vindo", "Serif", 20, 30 , 30, 100, 30, CreateColors.ORCHID));
        addBotao();
        setVisible(true);
    }
    private void addBotao(){
        botaoLoginUsuario = new JButton("Login");
        botaoLoginUsuario.setBounds(100, 80, 30,100);
        botaoLoginUsuario.setForeground(CreateColors.MAGNETTA);
        botaoLoginUsuario.setBackground(CreateColors.ORCHID);
        botaoLoginUsuario.addActionListener(new OuvinteLoginFuncionario(this));
        cadastroUsuario = new JButton("Cadastrar");
        cadastroUsuario.setBounds(100, 180, 30, 100);
        cadastroUsuario.setForeground(CreateColors.MAGNETTA);
        cadastroUsuario.setBackground(CreateColors.ORCHID);
    }

    public JButton getCadastroUsuario() {
        return cadastroUsuario;
    }

    public JButton getBotaoLoginUsuario() {
        return botaoLoginUsuario;
    }
}
