package com.foodware.auth.registration.token;

import com.foodware.auth.user.UserDetails;
import com.foodware.auth.user.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
private final ConfirmationTokenRepository confirmationTokenRepository;
    @Value("${spring.datasource.password}")
    private final String databasePassword;
    @Value("${spring.datasource.username}")
    private final String databaseUserName;
    @Value("${spring.datasource.url}")
    private final String databaseUrl;

public void saveConfirmationToken(ConfirmationToken token){
    confirmationTokenRepository.save(token);
}

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
    public String getTokenByUserId(int userId){
        try (Connection conn = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
             PreparedStatement selectStatement = conn.prepareStatement("select token from confirmation_token where user_id=?");) {
            selectStatement.setInt(1, userId);
            ResultSet rs = selectStatement.executeQuery();
            while (rs.next()) {
               String token = rs.getString("token");

                return user;
            }
        } catch (SQLException e) {
            log.error("error while trying to get data from database");
            return null;
        }
        return null;
    }
}
