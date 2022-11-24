package com.example.wishlist.dto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class User {

    @NotBlank
    private String type;
    @NotBlank
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String email;

    public User(String type, Long id, String name, String email) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getType().equals(user.getType()) && getId().equals(user.getId()) && getName().equals(user.getName()) && getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getId(), getName(), getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
