package com.springframework.web.model;


import com.springframework.web.validation.ValidEmail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "users")
public class User implements Serializable {

    @Id
    @NotBlank
    @Size(min = 8)
    @Pattern(regexp = "^\\w{8,}")
    private String username;

    @NotBlank()
    @Pattern(regexp = "^\\S+$")
    @Size(min = 8)
    private String password;

    @ValidEmail
    private String email;

    private boolean enabled = false;

    private String authority;

    @Size(min = 2, max = 100)
    private String name;

    public User() {
    }


    public User(String username, String password, String email, boolean enabled, String authority, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.authority = authority;
        this.name = name;

    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", authority='" + authority + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
