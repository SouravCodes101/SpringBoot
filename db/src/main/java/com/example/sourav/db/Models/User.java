package com.example.sourav.db.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private int id;
  private String firstName;
  private String lastName;
  private int age;
}
