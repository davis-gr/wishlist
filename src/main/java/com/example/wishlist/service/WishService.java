package com.example.wishlist.service;

import com.example.wishlist.dto.User;
import com.example.wishlist.dto.UserList;
import com.example.wishlist.model.Wish;
import com.example.wishlist.repository.WishRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WishService {

    private WishRepository wishRepository;

    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public Wish addWish(Wish wish) {
        wishRepository.save(wish);
        return wish;
    }

    public List<Wish> getWishList() {
        return wishRepository.findAll();
    }

    public Wish getWish(Long wishId) {
        return wishRepository.findById(wishId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Wish updateWish(Wish wish, Long wishId) {
        Wish updatedWish = wishRepository.findById(wishId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updatedWish.setWishName(wish.getWishName());
        updatedWish.setWishDescription(wish.getWishDescription());
        wishRepository.save(updatedWish);
        return updatedWish;
    }

    public void deleteWish(Long wishId) {
        if (wishRepository.existsById(wishId)) {
            wishRepository.deleteById(wishId);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public String transformUsers(UserList users) {
        return users.getUsers().stream().map(User::getName).collect(Collectors.joining(", "));
    }

}
