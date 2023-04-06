package run;

import model.PcoException;
//import model.dao.OrderDAO;
import model.dao.ProductDAO;
//import model.dto.Order;
import model.dto.Product;

import java.util.ArrayList;
import java.util.List;

public class MainDB {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = new ArrayList<>();
        try{
            products = productDAO.getAll();

            for(Product product : products){
                System.out.println(product);
            }
        } catch (PcoException pcoException){
            System.out.println(pcoException);
        } finally {
            productDAO.close();
        }

    }
}
