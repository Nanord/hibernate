package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nanord on 01.03.2018.
 */

@Entity
@Table(name = "Category")
public class Category extends Template{

    public Category() {
        super();
    }

    @Column(name = "description", length = 60)
    private String description;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade =  CascadeType.REMOVE, orphanRemoval = true)
    private Set<Product> products = new HashSet<Product>();


    public String toStingName() {
        return "Категория - " + name;
    }

    public String toStringProducts() {
        String result = "Список продуктов:\n";
        int i = 1;
        for (Product product:
                products) {
            result += i + ":\n" + "id - " + product.getId() + ", name - " + product.getName() + "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
