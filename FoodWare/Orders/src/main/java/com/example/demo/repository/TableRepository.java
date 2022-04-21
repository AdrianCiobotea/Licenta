package com.example.demo.repository;

import com.example.demo.entity.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends CrudRepository<Table, Integer> {
}
