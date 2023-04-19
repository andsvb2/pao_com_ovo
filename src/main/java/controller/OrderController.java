package controller;

import model.dao.OrderDAO;
import model.dao.OrderItemDAO;
import model.dao.ProductDAO;
import model.dto.Order;
import model.dto.OrderItem;
import model.dto.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderController {

    private OrderDAO orderDAO = new OrderDAO();
    private ProductDAO productDAO = new ProductDAO();
    private OrderItemDAO orderItemDAO = new OrderItemDAO();

    public List<Product> getProducts(Order order) {
        List<Product> products = new ArrayList<Product>();
        for(OrderItem orderItem : order.getOrderItems()){
            products.add(orderItem.getProduct());
        }
        return products;
    }

}
