package com.example.demo.collecitons;

import java.util.ArrayList;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.services.UserService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserCollection {

  @Autowired
  private UserService userService;

  private List<User> users = new ArrayList<User>();

  @GetMapping("/getUserById/{id}")
  public User getUser(@PathVariable(value = "id") int id) {
    User newUser = new User();
    newUser.setId(id);
    newUser.setFirstName("John");
    newUser.setLastName("Doe");

    return newUser;
  }

  @GetMapping("/getUserByName")
  public User getUser(@RequestParam(value = "firstName") String firstName,
      @RequestParam(value = "lastName") String lastName) {
    return userService.getUserByName(firstName, lastName);
  }

  @GetMapping("/getSentUsers")
  public List<User> getUser(@RequestParam(value = "firstName") String firstName) {
    // users.stream().filter(name -> name == users)
    // User u = new User();
    // if(u.getFirstName() == firstName) {
    // return users;
    // }

    List<User> tempList = new ArrayList<User>();
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getFirstName().equals(firstName)) {
        tempList.add(users.get(i));
      }
    }

    return tempList;

    // return users.stream().filter(userObj ->
    // (userObj.getFirstName().equals(firstName)))
    // .collect(Collectors.toList());

    // return users;
  }

  @PostMapping("/saveUser")
  public User saveUser(@RequestParam(value = "id") int id, @RequestBody User user) {
    user.setId(id);
    return user;
  }

  @PostMapping("/sendUser")
  public User sendUser(@RequestBody User newUser) {
    users.add(newUser);
    return newUser;
  }
}
