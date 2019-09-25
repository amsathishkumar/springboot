package com.tcl.mid.fil;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tcl.mid.rep.JwtTokenRep;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
  private static final long serialVersionUID = -3301605591108950415L;

  static final String CLAIM_KEY_CREATED = "created";

  static final String CLAIM_KEY_USERNAME = "sub";

  static final String CLAIM_KEY_ROLE = "role";

  static final String CLAIM_KEY_IPADDR = "ipaddr";

  private static String secret;

  private static Long expiration;

  @Autowired
  private JwtTokenRep jwtTokenRepository;


  /** variable created for the logger */
  private static Logger logger = LogManager.getLogger();

  /** method to set jwt secret **/
  @Value("${jwt.secret}")
  public void setSecret(String sec) {
    secret = sec;
  }

  /** method to set jwt expiration **/
  @Value("${jwt.expiration}")
  public void setExpiration(Long exp) {
    expiration = exp;
  }

  public static String getUsernameFromToken(String token) {
    String username = null;
    try {
      final Claims claims = getClaimsFromToken(token);
      username = claims.getSubject();
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return username;
  }

  /**
   * Method to get role from jwt token
   * 
   * @param token input token
   * @return role
   */
  public static Roles getRoleFromToken(String token) {
    Roles role = null;
    try {
      final Claims claims = getClaimsFromToken(token);
      role = Roles.valueOf(claims.get(CLAIM_KEY_ROLE).toString());
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return role;
  }

  /**
   * Method to get creation date from jwt token
   * 
   * @param token input token
   * @return creation date
   */
  public static Date getCreatedDateFromToken(String token) {
    Date created = null;
    try {
      final Claims claims = getClaimsFromToken(token);
      created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return created;
  }

  /**
   * Method to get expiration date from the jwt token
   * 
   * @param token input token
   * @return expiration date
   */
  public static Date getExpirationDateFromToken(String token) {
    Date expiration = null;
    try {
      final Claims claims = getClaimsFromToken(token);
      expiration = claims.getExpiration();
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return expiration;
  }

  /**
   * Method to get ipaddress from jwt token
   * 
   * @param token input token
   * @return ipaddress
   */
  public static String getIpaddressFromToken(String token) {
    String ipaddr = null;
    try {
      final Claims claims = getClaimsFromToken(token);
     // ipaddr = textEncryptor.decrypt(claims.get(CLAIM_KEY_IPADDR).toString());
      ipaddr =claims.get(CLAIM_KEY_IPADDR).toString();
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return ipaddr;
  }

  /**
   * Method to get claims from jwt token
   * 
   * @param token input token
   * @return claims
   */
  private static Claims getClaimsFromToken(String token) {
    Claims claims = null;
    try {
      claims =
          Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return claims;
  }

  /**
   * Method to generate jwt token from username and role
   * 
   * @param username
   * @param role
   * @return token
   */
  public static String generateToken(String username, Roles role,
      String ipAddr) {
	System.out.println(ipAddr);
    Map<String, Object> claims = new HashMap<>();
    claims.put(CLAIM_KEY_USERNAME, username);
    claims.put(CLAIM_KEY_ROLE, role);
    claims.put(CLAIM_KEY_CREATED, new Date());
    claims.put(CLAIM_KEY_IPADDR, ipAddr);

    return generateToken(claims);
  }

  /**
   * Method to generate jwt token from claims
   * 
   * @param claims
   * @return token
   */
  private static String generateToken(Map<String, Object> claims) {
    return Jwts.builder().setClaims(claims)
        .setExpiration(generateExpirationDate())
        .signWith(SignatureAlgorithm.HS512, secret).compact();
  }

  /**
   * Method to generate expiration date
   * 
   * @return date
   */
  private static Date generateExpirationDate() {
    return new Date(System.currentTimeMillis() + expiration * 1000);
  }

  /**
   * Persistent Token are used for providing automatic authentication, they
   * should be automatically deleted periodically.
   * <p>
   * This is scheduled to get fired every one hour
   * </p>
   */
  @Scheduled(cron = "0 * */1 * * ?")
  public void removeExpiredJwtTokensAndOtp() {
    Date now = new Date();
    // find all expired jwt tokens and delete them from the database
    jwtTokenRepository.findAll().forEach(token -> {
      Date expirationDate = token.getExpiryDate();
      if (expirationDate.compareTo(now) <= 0) {
        jwtTokenRepository.delete(token);
      }
    });
    
    logger.info("The time is now {}", now);
  }
}
