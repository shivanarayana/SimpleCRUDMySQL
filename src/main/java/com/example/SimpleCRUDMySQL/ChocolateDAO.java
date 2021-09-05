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
        final StringBuilder sb = new StringBuilder("ChocolateDAO{");
        sb.append("name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append(", weight=").append(weight);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
