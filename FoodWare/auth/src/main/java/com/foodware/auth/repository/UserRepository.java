package com.foodware.auth.repository;

import com.foodware.auth.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends org.springframework.data.repository.Repository<User, Integer> {
    User findUserByPhoneNumber(String phoneNumber);
}
