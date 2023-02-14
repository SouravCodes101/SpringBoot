package com.example.sourav.db.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sourav.db.Models.User;
import com.example.sourav.db.Services.UserService;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserCollections {
  
  @Autowired
  private UserService userService;

  @PostMapping("/addUser")
  public User addUser(@RequestBody User user) throws Exception {
    try {
      if(user.getFirstName() == null || user.getFirstName().isEmpty()) {
        throw new Exception("First name is required");
      }
      if(user.getLastName() == null || user.getLastName().isEmpty()) {
        throw new Exception("Last name is required");
      }
      if(user.getAge()<0) {
        throw new Exception("Age cannot be set to 0");
      }

      return userService.addUser(user);
    } catch (Exception e) {
      throw new WebServerException(e.getLocalizedMessage(),e);
    }
  }

  @GetMapping("/getUsers")
  public List<User> getUsers() throws Exception {
    try {
       return userService.getUsers();
    } catch(Exception e) {
      throw e;
    }
  }

  @GetMapping("/getUsersByName")
  public List<User> getUsersByName(@RequestParam (value="firstName") String firstName) throws Exception {
    try {
      return userService.getUsersByName(firstName);
    } catch (Exception e) {
      throw e;
    }
  }

  @GetMapping("/removeUsersById")
  public List<User> removeUsersById(@RequestParam (value = "id") int id) throws Exception {
    try {
      return userService.removeUsersById(id);
    }catch (Exception e) {
      throw e;
    }
  }
}
