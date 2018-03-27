package model;

import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Nanord on 01.03.2018.
 */

@Entity
@Table(name = "Product")
public class Product extends  Template{

    public Product() {
        super();
    }

    @Column(name = "name", nullable =  false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "cost", nullable =  false)
    private float cost;

    @Column(name = "count", nullable =  false)
    private int count;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "products", cascade =  CascadeType.ALL)
    private Set<Order> orders = new HashSet<Order>();

    @Override
    public String toString() {
        return "Название -'" + name + "', Описание -'" + description + '\'' +
                ", Цена -" + cost +
                category.toStingName();
    }

    public String toStringProduct() {
        return new StringBuilder()
                .append("Название - '")
                .append(name)
                .append("', Описание - '")
                .append(description).append('\'')
                .append(", Цена -")
                .append(cost)
                .append("\nКатегория - ")
                .append(category.getName()).toString();
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

    public double getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public double getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
