package com.example.demo.repository;

import com.example.demo.entity.Extra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseExtraRepository extends CrudRepository<Extra, Integer> {
}
