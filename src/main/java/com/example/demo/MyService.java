package com.example.demo;

import java.util.List;

public interface MyService {
    List<User> getAllUsers();
    String saveUser();
    String updateUser();
    String deleteUser();
}
