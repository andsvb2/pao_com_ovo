package controller;

import model.PcoException;
import model.dao.EmployeeDAO;
import model.dto.Employee;
import view.TelaCadastroFuncionario;
import view.TelaLoginFuncionario;

import javax.swing.*;
import java.awt.event.*;

public class OuvinteSalvarEmpregado implements ActionListener {

    private TelaCadastroFuncionario t;
    public OuvinteSalvarEmpregado(TelaCadastroFuncionario t){
        this.t = t;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        if(t.getTextName().getText().trim().equals("") && t.getTextPhone().getText().equals("()-")){
            JOptionPane.showMessageDialog(null, "Insira os seus dados");
        }else{
            Employee e = new Employee();
            e.setName(t.getTextName().getText());
            e.setPhone(t.getTextPhone().getText());
            try {
                employeeDAO.save(e);
            } catch (PcoException ex) {
                throw new RuntimeException(ex);
            }
            new TelaLoginFuncionario("Login: Selecione a opção que deseja");
            t.dispose();
        }

    }
}
