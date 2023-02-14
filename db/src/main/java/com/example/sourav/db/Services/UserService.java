package com.example.sourav.db.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.sourav.db.Models.User;
import com.example.sourav.db.Dao.UserDAO;
import java.util.*;

@Component
public class UserService {
  
  @Autowired
  private UserDAO userDAO;

  public User addUser(User user) throws Exception {
    try {
      return userDAO.addUser(user);
    } catch (Exception e) {
      throw e;
    }
  }

  public List<User> getUsers() throws Exception {
    try {
      return userDAO.getUsers();
    } catch (Exception e) {
      throw e;
    }
  }

  public List<User> getUsersByName(String name) throws Exception {
    try {
      return userDAO.getUsersByName(name);
    } catch (Exception e) {
      throw e;
    }
  }

  public List<User> removeUsersById(int id) throws Exception {
    try {
      return userDAO.removeUsersById(id);
    } catch (Exception e) {
      throw e;
    }
  }

}
