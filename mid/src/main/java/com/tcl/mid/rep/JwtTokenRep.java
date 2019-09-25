package com.tcl.mid.rep;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the JwtTokens entity.
 */
public interface JwtTokenRep extends MongoRepository<JwtToken, String> {
  /**
   * Query API to find a document by token
   * 
   * @param token jwt token
   * @return Optional JwtToken object
   */
  Optional<JwtToken> findOneByToken(String token);

  /**
   * Query API to find documents by username
   * 
   * @param username username in token
   * @return List<JwtToken> JwtToken object
   */
  List<JwtToken> findByUsername(String username);

  /**
   * Query API to find documents by creation date before date
   * 
   * @param date date to check
   * @return List<JwtToken> JwtToken object
   */
  List<JwtToken> findAllByCreatedDateBefore(Date date);
}