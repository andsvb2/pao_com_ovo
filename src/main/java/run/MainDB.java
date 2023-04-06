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
        ProductDAO productDAO = new ProductDAO();
        try {
            Order pedido = new Order();
            Product p1 = new Product();
            p1.setName("Arroz Tio João");
            p1.setDescription("Arroz parboilizado rico em sais minerais.");
            p1.setUnit_price(5.40);
            System.out.println(p1);
            productDAO.save(p1);

            Product p2 = new Product();
            p2.setName("Macarrão Fininho");
            p2.setDescription("Macarrão para espaguete");
            p2.setUnit_price(3.25);
            System.out.println(p2);
            productDAO.save(p2);

            Product p3 = new Product();
            p3.setName("Pão francês");
            p3.setDescription("Pão francês vendido por unidade");
            p3.setUnit_price(0.75);
            System.out.println(p3);
            productDAO.save(p3);

        } catch (PcoException pcoException) {
            System.out.println(pcoException);
        } finally {
//            productDAO.close();
        }

        OrderDAO orderDAO = new OrderDAO();
        try {
            Order pedido = new Order();
            List<Product> produtos = new ArrayList<>();

            Product p1 = new Product();
            p1 = productDAO.getByID(1);
            System.out.println(p1);

            produtos.add(p1);

            pedido.setCustomer_name("Antônio");
            pedido.setCustomer_phone("(83) 999 888 777");
            pedido.setProducts(produtos);

            System.out.println(pedido);
            orderDAO.save(pedido);

        } catch (PcoException pcoException) {
            System.out.println(pcoException);
        } finally {
            orderDAO.close();
        }
    }
}
