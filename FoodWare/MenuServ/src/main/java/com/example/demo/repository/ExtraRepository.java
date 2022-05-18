package com.example.demo.repository;

import com.example.demo.entity.Extra;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraRepository extends org.springframework.data.repository.Repository<Extra, Integer> {
  List<Extra> findExtrasByCategoryId(int categoryId);
}