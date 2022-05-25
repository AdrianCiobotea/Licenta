package com.foodware.auth.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {

  @Value("${jwt.token.secret}")
  public String jwtTokenSecret;

  public String generateToken(Authentication authentication) {
    final String authorities = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));
    return Jwts.builder()
        .setSubject(authentication.getName())
        .claim("scopes", authorities)
        .signWith(SignatureAlgorithm.HS256, jwtTokenSecret)
        .setIssuedAt(java.sql.Date.from(Instant.now()))
        .setExpiration(Date.from(Instant.now().plus(5,
            ChronoUnit.HOURS)))
        .compact();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    String phoneNumber = getPhoneNumberFromToken(token);
    return (phoneNumber.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  public String getPhoneNumberFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }


  private boolean isTokenExpired(String token) {
    final java.util.Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new java.util.Date());
  }

  public java.util.Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  public Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(jwtTokenSecret).parseClaimsJws(token).getBody();
  }

  public UsernamePasswordAuthenticationToken getAuthentication(String token, UserDetails userDetails) {
    JwtParser jwtParser = Jwts.parser().setSigningKey(jwtTokenSecret);
    Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
    Claims claims = claimsJws.getBody();
    Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("scopes").toString().split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
    return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
  }

}

