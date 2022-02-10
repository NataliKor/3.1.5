package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyServiceImp implements MyService {
    //@Autowired
    private RestTemplate restTemplate;

    private final static String URL = "http://91.241.64.178:7081/api/users";
    private List<String> sessionId;
    private HttpHeaders httpHeaders;

    //метод посылает http-запрос и получает от нашего rest-server список всех пользователей
    @Override
    public List<User> getAllUsers() {
        //этим кодом мы отправляем запрос и его результат получаем в responseEntity
        restTemplate = new RestTemplateBuilder().build();
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {
                });
        sessionId = responseEntity.getHeaders().get("Set-Cookie");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId.stream().collect(Collectors.joining(";")));
        httpHeaders = headers;
        //из тела responseEntity получаем полезную нагрузку
        return responseEntity.getBody();
    }

    //сохраняем пользователя
    @Override
    public String saveUser() {
        Byte age = 30;
        User user = new User(3L, "James", "Brown", age);
        //HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, entity, String.class);
        //ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        return responseEntity.getBody();
    }

    //изменяем пользователя
    @Override
    public String updateUser() {
        User user = new User(3L, "Thomas", "Shelby", (byte) 30);
        //HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);
        //   ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, user, String.class);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        return responseEntity.getBody();
    }

    //удаляем пользователя
    @Override
    public String deleteUser() {
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + 3, HttpMethod.DELETE, entity, String.class);
        return responseEntity.getBody();
    }
}
