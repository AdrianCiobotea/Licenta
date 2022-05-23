package com.foodware.auth.helper;

import com.foodware.auth.user.UserRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.UUID;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;

public class JwtHelper {

  @Value("${jwt.token.secret}")
  private static String secret;

  static Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
      SignatureAlgorithm.HS256.getJcaName());

  public static String buildJwtToken(String phoneNumber, UserRole userRole) {
    return Jwts.builder().claim("phoneNumber", phoneNumber)
        .claim("role", userRole).setSubject("login token").setId(UUID.randomUUID().toString()).setIssuedAt(Date.from(Instant.now()))
        .setExpiration(Date.from(Instant.now().plus(5,
            ChronoUnit.HOURS))).signWith(hmacKey).compact();
  }
}
