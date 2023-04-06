package model.dto;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Integer unit_price;

    public Product() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Integer unit_price) {
        this.unit_price = unit_price;
    }


    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + ((name == null) ? 0 : name.hashCode());
        result = 31 * result + ((description == null) ? 0 : description.hashCode());
        result = 31 * result + ((unit_price == null) ? 0 : unit_price.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Product that = (Product) obj;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!Objects.equals(unit_price, that.unit_price)) return false;

        return true;
    }
}
