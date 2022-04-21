package com.example.demo.service;

import com.example.demo.entity.Extra;
import com.example.demo.repository.BaseExtraRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExtraService {
  @Autowired
  BaseExtraRepository baseExtraRepository;

  public String insertExtra(Extra extra) {
    try {
      baseExtraRepository.save(extra);
    } catch (IllegalArgumentException e) {
      return "Please provide a valid extra product";
    }
    return "Successfully inserted an extra product";
  }


  public List<Extra> loadAllExtraProducts() {
    List<Extra> extras = new ArrayList<>();
    baseExtraRepository.findAll().forEach(extras::add);
    return extras;
  }

  public Optional<Extra> loadExtraById(int extraId) {
    return baseExtraRepository.findById(extraId);
  }

  public String deleteExtraById(int extraId) {
    try {
      baseExtraRepository.deleteById(extraId);
      return "Successfully deleted extra product with id " + extraId;
    } catch (NoSuchElementException e) {
      log.info("The extra product with the id: " + extraId + " doesn't exist");
      return "The extra product with the id: " + extraId + " doesn't exist";
    }

  }

  public String updateExtra(Extra extra, int id) {
    try {
      Extra extraDB = loadExtraById(id).get();
      if (extra.getName() == null) {
        extraDB.setName(extraDB.getName());
      } else {
        extraDB.setName(extra.getName());
      }
      if (extra.getPrice() == 0) {
        extraDB.setPrice(extraDB.getPrice());
      } else {
        extraDB.setPrice(extra.getPrice());
      }
      baseExtraRepository.save(extraDB);
    } catch (NoSuchElementException e) {
      if (extra.getName() != null && extra.getPrice() != 0.0) {
        baseExtraRepository.save(extra);
      } else {
        return "Please provide a valid extra product";
      }
    } catch (IllegalArgumentException e) {
      return "Please provide a valid extra product";
    }
    return "Successfully updated the extra product";
  }
}
