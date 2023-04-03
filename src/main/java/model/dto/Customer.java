package model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {

    @Id
    private Integer id;

    @Column(name = "customer_name")
    private String name;
}
