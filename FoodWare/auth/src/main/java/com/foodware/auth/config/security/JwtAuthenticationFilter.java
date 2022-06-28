package com.foodware.auth.config.security;

import com.foodware.auth.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private TokenProvider tokenProvider;
  @Autowired
  private UserService userService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    String header = request.getHeader("Authorization");
    String phoneNumber = null;
    String token;
    if (header != null && header.startsWith("Bearer ")) {
      token = header.replace("Bearer ", "");
      try {
        phoneNumber = tokenProvider.getPhoneNumberFromToken(token);
      } catch (IllegalArgumentException e) {
        logger.error("An error occurred while getting Phone number from token: ", e);
      } catch (ExpiredJwtException e) {
        logger.warn("The token is expired.", e);
      } catch (SignatureException e) {
        logger.error("Authentication Failed. Phone number or Password are not valid.");
      }
    } else {
      logger.debug("Could not find Authorization header. Allowed request.");
      chain.doFilter(request, response);
      return;
    }
    if (phoneNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userService.loadUserByUsername(phoneNumber);
      if (userDetails == null) {
        logger.debug("Could not find user in database");
        chain.doFilter(request, response);
        return;
      }
      if (tokenProvider.validateToken(token, userDetails)) {
        UsernamePasswordAuthenticationToken authentication = tokenProvider.getAuthentication(token,
            userDetails);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        logger.debug("User " + phoneNumber + " was authenticated.");
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    chain.doFilter(request, response);
  }
}
