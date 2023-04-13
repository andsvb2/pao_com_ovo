package run;

import model.PcoException;
import model.dao.EmployeeDAO;
import model.dto.Employee;

import java.util.ArrayList;
import java.util.List;

public class PopulateEmployees {

    public static void main(String[] args) {

        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<Employee> employees = new ArrayList<>();

        try {
            employees = employeeDAO.getAll();
        } catch (Exception e) {
            System.out.println(e);
        }

        if (employees.size() == 0) {

            Employee emp1 = new Employee("João", "+55 (83) 998 275 432");
            employees.add(emp1);

            Employee emp2 = new Employee("Maria", "+55 (83) 998 275 433");
            employees.add(emp2);

            Employee emp3 = new Employee("Pedro", "+55 (83) 998 275 434");
            employees.add(emp3);

            Employee emp4 = new Employee("Ana", "+55 (83) 998 275 435");
            employees.add(emp4);

            Employee emp5 = new Employee("Maria", "+55 (83) 998 275 436");
            employees.add(emp5);

            Employee emp6 = new Employee("Pedro", "+55 (83) 998 275 437");
            employees.add(emp6);

            Employee emp7 = new Employee("Ana", "+55 (83) 998 275 438");
            employees.add(emp7);


            try {
                for (Employee employee : employees) {
                    employeeDAO.save(employee);
                }
            } catch (PcoException pcoe) {
                System.out.println(pcoe);
            }
        }

    }

}
