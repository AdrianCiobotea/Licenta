package com.foodware.auth.user;

import com.foodware.auth.registration.token.ConfirmationToken;
import com.foodware.auth.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    @Value("${spring.datasource.password}")
    private String databasePassword;
    @Value("${spring.datasource.username}")
    private String databaseUserName;
    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    public UserService(){

    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        try (Connection conn = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
             PreparedStatement selectStatement = conn.prepareStatement("select first_name,last_name,user_role,password from user where email=?");) {
            selectStatement.setString(1, email);
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String userRole = rs.getString("user_role");
                String password = rs.getString("password");
                UserRole role = null;
                if (userRole.equals(UserRole.CLIENT.name())) {
                    role = UserRole.CLIENT;
                } else {
                    role = UserRole.ADMIN;
                }
                UserDetails user = new UserDetails();
                user.setFirstName(name);
                user.setLastName(lastName);
                user.setUserRole(role);
                user.setPassword(password);
                user.setEmail(email);

                return user;
            }
        } catch (SQLException e) {
            log.error("error while trying to get data from database");
            return null;
        }
        return null;
    }

    public UserDetails verifyLogin(String email, String password){
       UserDetails user = loadUserByEmail(email);
       if(bCryptPasswordEncoder.matches(password,user.getPassword())){
           return user;
       }else{
           return null;
       }
    }


    public String signUpUser(User user) {
        boolean userExists = loadUserByEmail(user.getEmail()) != null;
        if (userExists) {
            return "email already taken";
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        try (Connection conn = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
             PreparedStatement insertStatement = conn.prepareStatement("insert into user (email,first_name,last_name,password,user_role) values (?,?,?,?,?)");) {
            insertStatement.setString(1, user.getEmail());
            insertStatement.setString(2, user.getFirstName());
            insertStatement.setString(3, user.getLastName());
            insertStatement.setString(4, user.getPassword());
            insertStatement.setString(5, user.getUserRole().toString());
            ResultSet rs = insertStatement.executeQuery();
            String token = UUID.randomUUID().toString();
            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    user
            );

            confirmationTokenService.saveConfirmationToken(confirmationToken);
            //TODO: SEND EMAIL
            return token;
        } catch (SQLException throwables) {
            log.error("could not sign up user " + throwables.getMessage());
        }
        return null;
    }
}
