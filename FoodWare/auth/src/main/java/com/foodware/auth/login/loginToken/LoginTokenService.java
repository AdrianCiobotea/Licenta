package com.foodware.auth.login.loginToken;

import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class LoginTokenService {
    public String getTokenByUserId(int userId) {
        return "CLIENT";
    }
}
