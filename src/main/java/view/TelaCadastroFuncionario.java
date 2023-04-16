package view;

import utilites.CreateColors;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import controller.*;

import java.text.ParseException;

public class TelaCadastroFuncionario extends JanelaPadrao{
    private JTextField textName;
    private JFormattedTextField textPhone;
    private JButton botaoVoltar;
    private JButton botaoSalvar;
    public TelaCadastroFuncionario(String titulo) {
        super(titulo);
        add(addLabel("Nome: ", "Seriff", 120, 30, 150, 50, 30, CreateColors.MAGNETTA));
        add(addLabel("Telefone: ", "Seriff", 120, 150, 150, 50, 30, CreateColors.MAGNETTA ));
        addTextField();
        addButton();
        setVisible(true);
    }

    private void addButton() {
        botaoVoltar =new JButton("Voltar");
        botaoVoltar.setBounds(50, 400, 100, 30);
        botaoVoltar.setBackground(CreateColors.ORCHID);
        add(botaoVoltar);
        botaoSalvar = new JButton("Salvar");
        botaoSalvar.setBounds(240, 400, 100, 30);
        botaoSalvar.setBackground(CreateColors.ORCHID);
        botaoSalvar.addActionListener(new OuvinteSalvarEmpregado(this));
        add(botaoSalvar);
    }

    private void addTextField() {
        textName = new JTextField();
        textName.setBounds(120, 90, 150, 30);
        add(textName);
        MaskFormatter phoneMask;
        try {
            phoneMask = new MaskFormatter("(##) #####-####");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        textPhone = new JFormattedTextField(phoneMask);
        textPhone.setBounds(120, 210, 150, 30);
        add(textPhone);
    }

    public JButton getBotaoSalvar() {
        return botaoSalvar;
    }

    public JButton getBotaoVoltar() {
        return botaoVoltar;
    }

    public JTextField getTextName() {
        return textName;
    }

    public JTextField getTextPhone() {
        return textPhone;
    }
}
