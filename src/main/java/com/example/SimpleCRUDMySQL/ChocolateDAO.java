package com.example.SimpleCRUDMySQL;

import javax.persistence.*;

@Entity
@Table(name = "ChocolateDB")
public class ChocolateDAO {

    @Id
    private String name;
    private Long price;
    private Long weight;
    private Long quantity;

    public ChocolateDAO() {
    }

    public ChocolateDAO(String name, Long price, Long weight, Long quantity) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("name:").append(name);
        sb.append(", price:").append(price);
        sb.append(", weight:").append(weight);
        sb.append(", quantity:").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
