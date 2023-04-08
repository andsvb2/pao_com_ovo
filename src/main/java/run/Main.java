package run;

import java.util.ArrayList;
import java.util.List;

import model.PcoException;
import model.dao.OrderDAO;
import model.dao.ProductDAO;
import model.dto.Order;
import model.dto.Product;
import view.*;

public class Main {
    public static void main(String[] args) {
        Populate.main(new String[] {});
    	  new TelaMenu();
          new TelaMenu();

//        new TelaFuncionario("Super");
    }
}