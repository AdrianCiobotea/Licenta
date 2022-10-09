package com.example.demo.service;

import com.example.demo.entity.ExtraOrderItem;
import com.example.demo.repository.ExtraOrderItemRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtraOrderItemService {

  @Autowired
  ExtraOrderItemRepository extraOrderItemRepository;

  public Optional<ExtraOrderItem> insertExtraOrderItem(ExtraOrderItem extraOrderItem) {
    ExtraOrderItem extraOrderItemDB;
    try {
      extraOrderItemDB = extraOrderItemRepository.save(extraOrderItem);
    } catch (IllegalArgumentException e) {
      return Optional.empty();
    }
    return Optional.of(extraOrderItemDB);
  }

  public List<ExtraOrderItem> loadAllExtraOrderItems() {
    List<ExtraOrderItem> extraOrderItems = new ArrayList<>();
    extraOrderItemRepository.findAll().forEach(extraOrderItems::add);
    return extraOrderItems;
  }

  public Optional<ExtraOrderItem> loadExtraOrderItemById(Integer extraOrderItemId) {
    return extraOrderItemRepository.findById(extraOrderItemId);
  }

  public String updateExtraOrderItem(ExtraOrderItem extraOrderItem, Integer extraOrderItemId) {

    try {
      ExtraOrderItem extraOrderItemDB = loadExtraOrderItemById(extraOrderItemId).get();
      if (extraOrderItem.getOrderItemId() == 0) {
        extraOrderItemDB.setOrderItemId(extraOrderItemDB.getOrderItemId());
      } else {
        extraOrderItemDB.setOrderItemId(extraOrderItem.getOrderItemId());
      }

      extraOrderItemRepository.save(extraOrderItemDB);
    } catch (NoSuchElementException e) {
      if (extraOrderItem.getOrderItemId() != 0) {
        extraOrderItemRepository.save(extraOrderItem);
      } else {
        return "Please provide a valid extra order item";
      }
    } catch (IllegalArgumentException e) {
      return "Please provide a valid extra order item";
    }
    return "Successfully updated the extra order item";
  }

  public String deleteExtraOrderItemById(int extraOrderItemId) {
    extraOrderItemRepository.deleteById(extraOrderItemId);
    return "Successfully deleted extra order item with id " + extraOrderItemId;
  }

}
