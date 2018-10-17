package pl.coderslab.taskscheduler.model;

import javafx.beans.DefaultProperty;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min=1, max= 20)
    @NotNull
    private String name;

    @Size(min=1, max= 20)
    @NotNull
    private String surname;

    @Value("https://sported.org.uk/wp-content/uploads/2015/10/Blank-person-photo1.png")
    private String avatar_url;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Task> itemsAssigned = new HashSet<>(0);

    public long getId() {
        return id;
    }

    public void setId(long id){ this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }


    public Set<Task> getItemsAssigned() {
        return itemsAssigned;
    }

    public void setItemsAssigned(Set<Task> itemsAssigned) {
        this.itemsAssigned = itemsAssigned;
    }

}
