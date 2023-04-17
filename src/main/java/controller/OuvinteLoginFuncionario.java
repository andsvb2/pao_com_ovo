package controller;

import model.PcoException;
import model.dao.EmployeeDAO;
import model.dao.OrderDAO;
import model.dto.Employee;
import view.TelaFuncionario;
import view.TelaLoginFuncionario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class OuvinteLoginFuncionario implements ActionListener {
    TelaLoginFuncionario tela;
    public OuvinteLoginFuncionario(TelaLoginFuncionario t){
        tela = t;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Employee> emploList = new ArrayList<>();
        try {
            emploList =  (ArrayList<Employee>) new EmployeeDAO().getAll();
        } catch (PcoException ex) {
            throw new RuntimeException(ex);
        }
       Employee employee = (Employee) JOptionPane.showInputDialog(null, "Escolha o Funcionário", "Funcionários",
                JOptionPane.INFORMATION_MESSAGE,null, emploList.toArray(), emploList.get(0));
       if (Objects.isNull(employee)){
           JOptionPane.showConfirmDialog(null, "Selecione um funcionário", "Confirme",JOptionPane.OK_OPTION);
       }
       else{
           new TelaFuncionario("Funcionário");
           tela.dispose();
       }
    }
}
