package com.springframework.web.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 10, max = 100)
    private String text;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public Offer() {
        this.user = new User();
    }

    public Offer(String text) {
        this.text = text;
    }

    public Offer(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public Offer(int id,String text, User user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String toString() {
        return "Offer{" +
                "text='" + text + '\'' +
                ", name='" + user.getName() + '\'' +
                ", username='" + user.getUsername() + '\'' +
                ", email='" + user.getEmail() + '\'' +
                '}';
    }
}
