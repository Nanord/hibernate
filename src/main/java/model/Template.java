package model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class Template implements Serializable {

    private static final long serialVersionUID = -5449174010904674060L;

    public  Template() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generated DataBase auto_increment when insert value
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private int id;


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public abstract String toString();

}