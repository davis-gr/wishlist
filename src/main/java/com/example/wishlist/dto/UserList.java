package com.example.wishlist.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserList {

    private List<User> users = new ArrayList<>();

    public UserList(List<User> users) {
        this.users = users;
    }

    public UserList() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserList userList)) return false;
        return getUsers().equals(userList.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsers());
    }

    @Override
    public String toString() {
        return "Users{" +
                "user=" + users +
                '}';
    }
}
