package com.example.demo.services;

import org.springframework.stereotype.Component;

import com.example.demo.models.User;

@Component
public class UserService {
  public User getUserByName(String firstName, String lastName) {
    User a = new User();
    a.setId(13);
    a.setFirstName(firstName);
    a.setLastName(lastName);
    return a;
  }
}
