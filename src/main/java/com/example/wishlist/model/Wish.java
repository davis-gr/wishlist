package com.example.wishlist.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "wishes")
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "wish_id")
    private Long id;
    @NotBlank
    @Column(name = "wish_name")
    private String wishName;
    @NotBlank
    @Column(name = "wish_description")
    private String wishDescription;

    public Wish(Long id, String wishName, String wishDescription) {
        this.id = id;
        this.wishName = wishName;
        this.wishDescription = wishDescription;
    }

    public Wish() {
    }

    public Long getId() {
        return id;
    }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public String getWishDescription() {
        return wishDescription;
    }

    public void setWishDescription(String wishDescription) {
        this.wishDescription = wishDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wish wish)) return false;
        return getId().equals(wish.getId()) && getWishName().equals(wish.getWishName()) && getWishDescription().equals(wish.getWishDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWishName(), getWishDescription());
    }

    @Override
    public String toString() {
        return "Wish{" +
                "id=" + id +
                ", wishName='" + wishName + '\'' +
                ", wishDescription='" + wishDescription + '\'' +
                '}';
    }
}
