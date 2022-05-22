package com.foodware.auth.repository;

import com.foodware.auth.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BaseUserRepository extends CrudRepository<User, Integer> {
}