package com.example.wishlist.repository;

import com.example.wishlist.model.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {


}
