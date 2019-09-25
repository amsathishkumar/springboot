package com.tcl.mid.rep;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcl.mid.fil.Roles;

@SuppressWarnings("serial")
@Document(collection = "JwtToken")
public class JwtToken implements Serializable {
  @Id
  /** variable to store document id */
  private String id;

  @Indexed
  @Field("token")
  /** variable to store the token */
  private String token;

  @Indexed
  @Field("username")
  /** variable to store the username associated with the token */
  private String username;

  @Indexed
  @Field("role")
  /**
   * variable to store the role of the user
   */
  private Roles role;

  @CreatedDate
  @JsonIgnore
  @Field("created_date")
  /** variable to store the created date of the token */
  private Date createdDate;

  @JsonIgnore
  @Field("expiry_date")
  /** variable to store the expiry date of the token */
  private Date expiryDate;

  /** Default constructor for JwtToken class */
  public JwtToken() {
  }

  /**
   * Constructor with the initial values to set for the variables
   * 
   * @param id document id
   * @param token Jwt token
   * @param username name of user
   * @param role user's role
   * @param created_date date of token creation
   * @param expiry_date token expiration date
   */
  public JwtToken(String id, String token, String username, Roles role,
      Date createdDate, Date expiryDate) {
    super();
    this.id = id;
    this.token = token;
    this.username = username;
    this.role = role;
    this.createdDate = createdDate;
    this.expiryDate = expiryDate;
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
   * get method for token
   * 
   * @return token
   */
  public String getToken() {
    return token;
  }

  /**
   * set method for token
   * 
   * @param token
   */
  public void setToken(String token) {
    this.token = token;
  }

  /**
   * get method for username
   * 
   * @return username
   */
  public String getUsername() {
    return username;
  }

  /**
   * set method for username
   * 
   * @param username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * get method for role
   * 
   * @return role
   */
  public Roles getRole() {
    return role;
  }

  /**
   * set method for role
   * 
   * @param role
   */
  public void setRole(Roles role) {
    this.role = role;
  }

  /**
   * Method to get token created date
   * 
   * @return createdDate
   */
  public Date getCreatedDate() {
    return createdDate;
  }

  /**
   * Method to set created date of token
   * 
   * @param createdDate token creation date
   */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  /**
   * Method to get token expiration date
   * 
   * @return expiryDate
   */
  public Date getExpiryDate() {
    return expiryDate;
  }

  /**
   * Method to set token expiration date
   * 
   * @param expiryDate token expiry date
   */
  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  /**
   * Overriding toString method to return object information
   * 
   * @return string of variables and their values
   */
  @Override
  public String toString() {
    return "User [id=" + id + ", token=" + token + ", username=" + username
        + ", role=" + role + ", createdDate=" + createdDate + ", expiryDate="
        + expiryDate + "]";
  }
}
