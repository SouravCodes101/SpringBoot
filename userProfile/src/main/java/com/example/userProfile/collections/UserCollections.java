package com.example.userProfile.collections;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.stream.Collectors;

import com.example.userProfile.models.User;

@RestController
@RequestMapping("/user")
public class UserCollections {
  
  List<User> users = new ArrayList<User>();

  @GetMapping("/getUsers")
  public List<User> getUsers(){
    return users;
  }

  @GetMapping("/getUserById/{id}")
  public List<User> getUsers(@PathVariable(value = "id") int id){
    return users.stream().filter(obj1 -> (obj1.getId() == id)).collect(Collectors.toList());
  }

  //ToDo: Remove User By Name
  @GetMapping("/removeUserByName")
  public List<User> removeUsersByName(@RequestParam (value = "firstName") String firstName) {
      List<User> tempList = users.stream().filter(newObj -> (newObj.getFirstName()).equals(firstName)).collect(Collectors.toList());
      users.removeAll(tempList);
      return users;
  }
//ToDo: Remove User By ID
  @GetMapping("/removeUserById")
  public List<User> removeUsersById(@RequestParam (value = "id") int id) {
    List<User> newList = users.stream().filter(userObj -> (userObj.getId()) == id).collect(Collectors.toList());
    users.removeAll(newList);
    return users;
  }

  //ToDo: Sort Users by Name---------------------------
  public class sortByName implements Comparator<User> {
    public int compare(User u1, User u2) {
      int NameCompare = u1.getFirstName().compareTo(u2.getFirstName());
      int AgeCompare = u1.getAge().compareTo(u2.getAge());
      return (NameCompare == 0 ) ? AgeCompare:NameCompare;
    }
  }

  @GetMapping("/sortUsersByName")
  public List<User> sortUserByName(){
    List<User> sortedList = new ArrayList<User>();
    for(int i=0; i<users.size(); i++) {
      sortedList.add(users.get(i));
    } 

    Collections.sort(sortedList,new sortByName());
    return sortedList;
  }
  //ToDo: --------------------------------------------


  //ToDo: Sort Users By ID ---------------------------
  public class sortById implements Comparator<User> {
    public int compare(User user1, User user2) {
      return user1.getId() - user2.getId();
    }
  }

 @GetMapping("/sortUsersById")
 public List<User> sortUsersById(){
  List<User> newUser = new ArrayList<User>();
  for(int i=0; i<users.size(); i++) {
    newUser.add(users.get(i));
  }

  Collections.sort(newUser, new sortById());
  return newUser;
 }
 //ToDo: --------------------------------------------

 
  @PostMapping("/sendUsers")
  public User sendUsers(@RequestBody User newUser) {
    // for(int i =0 ; i< users.size(); i++) {
    //   try {
    //   } catch(Exception e) {
    //     System.out.print(e);
    //   }
    // }
    
    users.add(newUser);
    return newUser;
  }
}
