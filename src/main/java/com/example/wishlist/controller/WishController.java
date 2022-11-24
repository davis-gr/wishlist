package com.example.wishlist.controller;

import com.example.wishlist.dto.UserList;
import com.example.wishlist.model.Wish;
import com.example.wishlist.service.WishService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class WishController {

    private WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @PostMapping("/wish/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Wish addWish(@Valid @RequestBody Wish wish) {
        return wishService.addWish(wish);
    }

    @GetMapping("/wish")
    public List<Wish> getWishList() {
        return wishService.getWishList();
    }

    @GetMapping("/wish/{wishId}")
    public Wish getWish(@PathVariable Long wishId) {
        return wishService.getWish(wishId);
    }

    @PutMapping("/wish/{wishId}")
    public Wish updateWish(@Valid @RequestBody Wish wish, @PathVariable Long wishId) {
        return wishService.updateWish(wish, wishId);
    }

    @DeleteMapping("/wish/{wishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWish(@PathVariable Long wishId) {
        wishService.deleteWish(wishId);
    }

    @PostMapping("/users")
    public String transformUsers(@Valid @RequestBody UserList users) {
        return wishService.transformUsers(users);
    }


}