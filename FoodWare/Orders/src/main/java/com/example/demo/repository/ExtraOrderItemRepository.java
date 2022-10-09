package com.example.demo.repository;

import com.example.demo.entity.ExtraOrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraOrderItemRepository extends CrudRepository<ExtraOrderItem, Integer> {
}
