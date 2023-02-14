package com.example.sourav.db.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import java.util.*;

import com.example.sourav.db.Models.User;

@Component
public class UserDAO {

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public User addUser(User user) throws Exception {
    try {
      String query = "INSERT into users (first_name,last_name,age) VALUES(:firstName,:lastName,:age)";

      KeyHolder holder = new GeneratedKeyHolder();

      MapSqlParameterSource param = new MapSqlParameterSource();
      param.addValue("lastName", user.getLastName());
      param.addValue("age", user.getAge());
      param.addValue("firstName", user.getFirstName());

      namedParameterJdbcTemplate.update(query, param, holder);

      user.setId(holder.getKey().intValue());

      return user;

    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  public User addUserNormalJDBC(User user) throws Exception {
    try {
      String query = "INSERT into users (first_name,last_name,age) VALUES (?,?,?)";

      KeyHolder holder = new GeneratedKeyHolder();

      jdbcTemplate.update(query, new Object[] { user.getFirstName(), user.getLastName(), user.getAge() }, holder);

      user.setId(holder.getKey().intValue());

      return user;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  public List<User> getUsers() throws Exception {
    try {
      Object[] obj = new Object[]{};
      String query = "SELECT * FROM users";
      List<User> user = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(User.class), obj);
      return user;
    } catch(Exception e) {
      throw e;
    }
  }

  public List<User> getUsersByName(String firstName) throws Exception {
    try {
      Object[] obj = new Object[]{firstName};
      String query = "SELECT * FROM users WHERE first_name like ? order by id DESC";
      List<User> user = (jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(User.class),obj));
      return user;
    } catch (Exception e) {
      throw e;
    }
  }

  public List<User> removeUsersById(int id) throws Exception {
    try{
      Object[] obj2 = new Object[]{id};
      String query = "DELETE FROM users WHERE id like ?";
      List<User> user2 = (jdbcTemplate.query(query,BeanPropertyRowMapper.newInstance(User.class),obj2));
      return user2;

    } catch(Exception e) {
      throw e;
    }
  }

}
