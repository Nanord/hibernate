package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Table Order for User
 * Created by Nanord on 01.03.2018.
 */

@Entity
@Table(name = "Orders")
public class Order extends  Template {

    public  Order() {
        super();
    }

    @Column(name = "cash")
    private int cash;

    @Column(name = "pickup")
    private int pickup;

    @Column(name = "date")
    private String date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_product",
            joinColumns = {@JoinColumn(name = "id_order")},
            inverseJoinColumns = {@JoinColumn(name = "id_product")})
    private Set<Product> products = new HashSet<Product>();

    public String toString() {
        return null;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }


    public int getPickup() {
        return pickup;
    }

    public void setPickup(int pickup) {
        this.pickup = pickup;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }



    public float getPriceProduct() {
        float price = 0;

        for (Product product:
             products) {
            price += product.getCost();
        }

        return price;
    }

    public String getStringProduct() {
        StringBuilder result = new StringBuilder("Список продуктов:\n");
        int i = 1;
        for (Product product:
             products) {
            result.append(i)
                    .append(": ")
                    .append(product.toStringProduct())
                    .append("\n");
        }

        return result.toString();
    }
}
