package com.foodware.auth.config.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class TokenProvider {

    @Value("${jwt.token.secret}")
    public String jwtTokenSecret;


    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(java.sql.Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(5,
                        ChronoUnit.HOURS)))
                .signWith(SignatureAlgorithm.HS256, jwtTokenSecret)
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
        List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

}

