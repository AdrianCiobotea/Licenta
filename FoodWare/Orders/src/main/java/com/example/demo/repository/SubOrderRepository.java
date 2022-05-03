package com.example.demo.repository;

import com.example.demo.entity.SubOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubOrderRepository extends CrudRepository<SubOrder, Integer> {
}
