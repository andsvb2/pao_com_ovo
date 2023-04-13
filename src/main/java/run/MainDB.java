package run;

import model.PcoException;
import model.dao.EmployeeDAO;
import model.dto.Employee;

import java.util.List;

public class MainDB {
    public static void main(String[] args) {

        PopulateEmployees.main(new String[]{});
        EmployeeDAO employeeDAO = new EmployeeDAO();

        try{
            Employee emp1;
            emp1 = employeeDAO.getByID(1);
            System.out.println(emp1);
            System.out.println("Fim da busca de funcionário por id.\n");

            List<Employee> employees;
            employees = employeeDAO.getAll();
            for(Employee employee : employees){
                System.out.println(employee);
            }
            System.out.println("Fim da listagem de funcionários.\n");

            List<Employee> employeeList;
            employeeList = employeeDAO.findEmployeesByName("Maria");
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
            System.out.println("Fim da listagem dos funcionários de nome 'Maria'.\n");

        } catch (PcoException pcoException){
            System.out.println(pcoException);
        } finally {
            employeeDAO.close();
        }

    }
}
