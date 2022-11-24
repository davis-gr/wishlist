package com.example.wishlist;

import com.example.wishlist.dto.User;
import com.example.wishlist.dto.UserList;
import com.example.wishlist.model.Wish;
import com.example.wishlist.repository.WishRepository;
import com.example.wishlist.service.WishService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class WishServiceTest {

    @Mock
    WishRepository wishRepository;

    @InjectMocks
    WishService wishService;

    @Test
    public void wishCanBeAdded() {
        Wish testWish = createWish();
        wishService.addWish(testWish);
        Mockito.verify(wishRepository).save(testWish);
    }

    @Test
    public void wishCanBeRetrieved() {
        Wish testWish = createWish();
        Mockito.doAnswer(invocation -> Optional.of(testWish)).when(wishRepository).findById(1L);
        Wish retrievedWish = wishService.getWish(1L);
        Assertions.assertEquals("Test wish", retrievedWish.getWishName());
    }

    @Test
    public void nonExistingWishCannotBeRetrieved() {
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> wishService.getWish(999L));
        Assertions.assertEquals(404, exception.getRawStatusCode());
    }

    @Test
    public void wishCanBeUpdated() {
        Wish testWish = createWish();
        Wish updatedWish = createUpdatedWish();
        Mockito.doAnswer(invocation -> Optional.of(testWish)).when(wishRepository).findById(1L);
        wishService.updateWish(updatedWish, 1L);
        Assertions.assertEquals(updatedWish.getWishName(), testWish.getWishName());
        Mockito.verify(wishRepository).save(testWish);
    }

    @Test
    public void nonExistingWishCannotBeUpdated() {
        Wish updatedWish = createUpdatedWish();
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> wishService.updateWish(updatedWish, 2L));
        Assertions.assertEquals(404, exception.getRawStatusCode());
    }

    @Test
    public void wishCanBeDeleted() {
        Mockito.doAnswer(invocation -> true).when(wishRepository).existsById(1L);
        wishService.deleteWish(1L);
        Mockito.verify(wishRepository).deleteById(1L);
    }

    @Test
    public void nonExistentWishCannotBeDeleted() {
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> wishService.deleteWish(9999L));
        Assertions.assertEquals(404, exception.getRawStatusCode());
    }

    @Test
    public void wishListCanBeRetrieved() {
        List<Wish> wishlist = new ArrayList<>();
        wishlist.add(createWish());
        wishlist.add(createUpdatedWish());
        Mockito.doAnswer(invocation -> wishlist).when(wishRepository).findAll();
        List<Wish> retrievedWishlist = wishService.getWishList();
        Assertions.assertEquals(wishlist, retrievedWishlist);
        Assertions.assertEquals(wishlist.size(), retrievedWishlist.size());
    }

    @Test
    public void transformUsersTest() {
        UserList userList = new UserList();
        userList.addUser(new User("user", 1L, "lukeskywalker", "luke@jedi.com"));
        userList.addUser(new User("user", 2L, "padmeamidala", "padme@republic.gov"));
        userList.addUser(new User("admin", 3L, "anakinskywalker", "vader@empire.gov"));
        String result = wishService.transformUsers(userList);
        Assertions.assertEquals("lukeskywalker, padmeamidala, anakinskywalker", result);
    }

    @Test
    public void transformUsersEmptyListTest() {
        UserList userList = new UserList();
        String result = wishService.transformUsers(userList);
        Assertions.assertEquals("", result);
    }

    private Wish createWish() {
        return new Wish(1L, "Test wish", "Test description for a test wish");
    }

    private Wish createUpdatedWish() {
        return new Wish(2L, "Updated test wish", "Updated test description for an updated test wish");
    }

}
