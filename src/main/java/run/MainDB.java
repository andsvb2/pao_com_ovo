package run;

import model.PcoException;
import model.dao.OrderDAO;
import model.dao.OrderItemDAO;
import model.dao.ProductDAO;
import model.dto.Employee;
import model.dto.Order;
import model.dto.OrderItem;
import model.dto.Product;

import java.util.List;

public class MainDB {
    public static void main(String[] args) {

        PopulateProducts.main(new String[]{});
        ProductDAO productDAO = new ProductDAO();
        OrderDAO orderDAO = new OrderDAO();
        OrderItemDAO orderItemDAO = new OrderItemDAO();

        Order pedido = new Order("Anderson", "(83) 998 298 414");

        try{
            List<Product> produtos = productDAO.getAll();

            OrderItem item1 = new OrderItem(produtos.get(2), 5);
            pedido.addOrderItem(item1);

            orderDAO.save(pedido);

        } catch (PcoException pcoException){
            System.out.println(pcoException);
        } finally {
            orderDAO.close();
        }

    }
}
