package com.example.demo.service;

import com.example.demo.repository.TableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TableService {
  @Autowired
  TableRepository tableRepository;
}
