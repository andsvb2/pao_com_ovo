package run;

import model.PcoException;
import model.dao.OrderDAO;
import model.dao.ProductDAO;
import model.dto.Order;
import model.dto.Product;

import java.util.ArrayList;
import java.util.List;

public class MainDB {
    public static void main(String[] args) {

        OrderDAO orderDAO = new OrderDAO();
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = new ArrayList<>();
        try{
            Product p1 = new Product();
//            p1 = productDAO.getByID(3);
            p1.setName("vodka");
            p1.setDescription("vodka slova morango");
            p1.setUnit_price(32.0);
            p1.setQuantityPerUnit(700.00f);

            Order pedido = new Order();
            pedido.setCustomer_name("seu João");
            pedido.setCustomer_phone("123");
            pedido.addProduct(p1);

            System.out.println(pedido);


            for(Product product : products){
                System.out.println(product);
            }
            orderDAO.save(pedido);
        } catch (PcoException pcoException){
            System.out.println(pcoException);
        } finally {
            orderDAO.close();
        }

    }
}
