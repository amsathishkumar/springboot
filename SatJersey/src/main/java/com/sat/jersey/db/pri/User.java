package com.sat.jersey.db.pri;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "users")
public class User implements Serializable {
  @Id
  /** variable to store document id */
  private String id;

  @Indexed
  @Field("username")
  /** variable to store IT username */
  private String username;

  @JsonIgnore
  @Field("password")
  /** variable to store IT password */
  private String password;

  /** Default constructor for User class */
  public User() {
  }

  /**
   * Constructor with the initial values to set for the variables
   * 
   * @param id document id
   * @param username name of user
   * @param password of user
   */
  public User(String id, String username, String password) {
    super();
    this.id = id;
    this.username = username;
    this.password = password;
  }

  /**
   * get method for id
   * 
   * @return id document id
   */
  public String getId() {
    return id;
  }

  /**
   * set method for id
   * 
   * @param id document id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Method to get the username
   * 
   * @return username name of user
   */
  public String getUsername() {
    return username;
  }

  /**
   * Mthod to set the username
   * 
   * @param username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Method to get the password associated with user
   * 
   * @return password of user
   */
  public String getPassword() {
    return password;
  }

  /**
   * Method to set the password for user
   * 
   * @param password of user
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Overriding toString method to return object information
   * 
   * @return string of variables and their values
   */
  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + ", password="
        + password + "]";
  }
}
