package com.example.demo.repository;

import com.example.demo.entity.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseGroupRepository extends CrudRepository<Group, Integer> {
}
