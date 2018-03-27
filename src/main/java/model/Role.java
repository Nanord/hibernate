package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nanord on 02.03.2018.
 */

@Entity
@Table(name = "Role")
public class Role extends Template{

    public Role() {
        super();
    }

    @Column(name = "title", nullable =  false, length = 60)
    private String title;

    @OneToMany(mappedBy = "role", cascade =  CascadeType.ALL, orphanRemoval = true)
    private Set<User> users = new HashSet<User>();

    @Override
    public String toString() {
        return "Role{" +
                "title='" + title +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
