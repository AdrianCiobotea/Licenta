package com.foodware.auth.service;

import com.foodware.auth.entity.User;
import com.foodware.auth.exception.InvalidCredentialsException;
import com.foodware.auth.exception.UnknownIdentifierException;
import com.foodware.auth.exception.UserAlreadyExistException;
import com.foodware.auth.repository.BaseUserRepository;
import com.foodware.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Value("${spring.datasource.password}")
    private String databasePassword;
    @Value("${spring.datasource.username}")
    private String databaseUserName;
    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;
    @Autowired
    BaseUserRepository baseUserRepository;

    public String register(User user) throws UserAlreadyExistException {
        if (checkIfUserExist(user.getPhoneNumber())) {
            throw new UserAlreadyExistException("User already exists for this email");
        }
        User userEntity = new User();
        BeanUtils.copyProperties(user, userEntity);
        encodePassword(userEntity);
        baseUserRepository.save(userEntity);
        return "Successfully registered user";
    }

    public boolean checkIfUserExist(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber) != null;
    }

    public Optional<User> getUserById(Integer id) throws UnknownIdentifierException {
        return baseUserRepository.findById(id);
    }

    private void encodePassword(User userEntity) {
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
    }


    public String login(User user) throws InvalidCredentialsException {
        User userDB = userRepository.findUserByPhoneNumber(user.getPhoneNumber());
        if (userDB != null) {
            if (bCryptPasswordEncoder.matches(user.getPassword(), userDB.getPassword())) {
                return "authenticated";
            } else {
                throw new InvalidCredentialsException("invalid credentials");
            }
        } else {
            return "no such user in the database";
        }
    }
}
