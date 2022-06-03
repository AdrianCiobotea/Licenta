package com.foodware.auth.service;

import com.foodware.auth.config.security.TokenProvider;
import com.foodware.auth.entity.AuthToken;
import com.foodware.auth.entity.user.Role;
import com.foodware.auth.entity.user.User;
import com.foodware.auth.repository.UserRepository;
import java.util.Collections;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements UserDetailsService {

  @Value("${spring.datasource.password}")
  private String databasePassword;
  @Value("${spring.datasource.username}")
  private String databaseUserName;
  @Value("${spring.datasource.url}")
  private String databaseUrl;

  private BCryptPasswordEncoder bCryptPasswordEncoder;
  private AuthenticationManager authenticationManager;
  private UserRepository userRepository;
  private TokenProvider tokenProvider;

  @Autowired
  public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                     AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.authenticationManager = authenticationManager;
    this.tokenProvider = tokenProvider;
  }

  @Override
  public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
    User user = userRepository.findUserByPhoneNumber(phoneNumber);
    if (user == null) {
      return null;
    }
    return new org.springframework.security.core.userdetails.User(user.getPhoneNumber(), user.getPassword(),
        getAuthority(user));
  }

  public ResponseEntity<String> registerUser(String phoneNumber, String password) {
    if (loadUserByUsername(phoneNumber) != null) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Phone number already exists");
    }
    User user = new User();
    user.setPhoneNumber(phoneNumber);
    user.setPassword(bCryptPasswordEncoder.encode(password));
    user.setUserRole(Role.CLIENT);
    userRepository.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).body("Account successfully created");
  }

  private Set<SimpleGrantedAuthority> getAuthority(User user) {
    return Collections.singleton(new SimpleGrantedAuthority(user.getUserRole().authority()));
  }

  public AuthToken login(String phoneNumber, String password) {
    Authentication authentication =
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phoneNumber, password));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = tokenProvider.generateToken(authentication);
    return new AuthToken(token);
  }
}
