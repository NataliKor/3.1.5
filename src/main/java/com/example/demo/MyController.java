package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class MyController {
    private MyService myService = new MyServiceImp();

    //запрашиваем всех польхователь из БД
    @GetMapping()
    public List<User> getAllUsers() {
        return myService.getAllUsers();
    }

    //добавляем пользователя
    @PostMapping()
    public String add() {
        return myService.saveUser();
    }

    //изменяем пользователя
    @PutMapping
    public String updateUser() {
        return myService.updateUser();
    }

    //удаляем пользователя
    @DeleteMapping
    public String deleteUser() {
        return myService.deleteUser();
    }
}
