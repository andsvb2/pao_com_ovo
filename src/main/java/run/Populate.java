package run;

import model.PcoException;
import model.dao.ProductDAO;
import model.dto.Product;

import java.util.ArrayList;
import java.util.List;

public class Populate {

    public static void main(String[] args) {

        ProductDAO productDAO = new ProductDAO();
        List<Product> products = new ArrayList<>();

        try {
            products = productDAO.getAll();
        } catch (Exception e) {
            System.out.println(e);
        }

        if (products.size() == 0) {

            Product vodka = new Product();
            vodka.setName("Vodka");
            vodka.setUnit_price(25.00);
            vodka.setDescription("Vodka Slova Morango");
            vodka.setQuantityPerUnit(750.0f);
            products.add(vodka);

            Product banana = new Product();
            banana.setName("Banana");
            banana.setUnit_price(10.00);
            banana.setDescription("Banana maçã");
            banana.setQuantityPerUnit(1.0f);
            products.add(banana);

            Product coentro = new Product();
            coentro.setName("Coentro");
            coentro.setUnit_price(1.00);
            coentro.setDescription("Coentro");
            coentro.setQuantityPerUnit(1.0f);
            products.add(coentro);

            Product paoFrances = new Product();
            paoFrances.setName("Pão Francês");
            paoFrances.setUnit_price(1.00);
            paoFrances.setDescription("Pão Francês");
            paoFrances.setQuantityPerUnit(0.75f);
            products.add(paoFrances);

            Product limpadorMultiUso = new Product();
            limpadorMultiUso.setName("Limpador Multi Uso");
            limpadorMultiUso.setUnit_price(500.0);
            limpadorMultiUso.setDescription("Limpador Multi Uso");
            limpadorMultiUso.setQuantityPerUnit(5.5f);
            products.add(limpadorMultiUso);

            Product shampoo = new Product();
            shampoo.setName("Shampoo");
            shampoo.setUnit_price(15.00);
            shampoo.setDescription("Shampoo Palmolive");
            shampoo.setQuantityPerUnit(635.0f);
            products.add(shampoo);

            try {
                for (Product product : products) {
                    productDAO.save(product);
                }
            } catch (PcoException pcoe) {
                System.out.println(pcoe);
            }
        }

    }

}
