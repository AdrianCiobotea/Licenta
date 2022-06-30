package com.example.demo.service;

import com.example.demo.entity.OrderItem;
import com.example.demo.repository.OrderItemRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderItemService {

  @Autowired
  OrderItemRepository orderItemRepository;

  public String insertOrderItem(OrderItem orderItem) {
    try {
      orderItemRepository.save(orderItem);
    } catch (IllegalArgumentException e) {
      return "Please provide a valid shopping item";
    }
    return "Successfully inserted an shopping item";
  }

  public Optional<OrderItem> loadOrderItemById(Integer orderItemId) {
    return orderItemRepository.findById(orderItemId);
  }

  public String updateOrderItem(OrderItem orderItem, Integer orderItemId) {
    try {
      OrderItem orderItemDB = loadOrderItemById(orderItemId).get();
      if (orderItem.getProductId() == 0) {
        orderItemDB.setProductId(orderItemDB.getProductId());
      } else {
        orderItemDB.setProductId(orderItem.getProductId());
      }
      if (orderItem.getSubOrderId() == 0) {
        orderItemDB.setSubOrderId(orderItemDB.getSubOrderId());
      } else {
        orderItemDB.setSubOrderId(orderItem.getSubOrderId());
      }
      if (orderItem.getQuantity() == 0) {
        orderItemDB.setQuantity(orderItemDB.getQuantity());
      } else {
        orderItemDB.setQuantity(orderItem.getQuantity());
      }
      if (orderItem.getStatusId() == 0) {
        orderItemDB.setStatusId(orderItemDB.getStatusId());
      } else {
        orderItemDB.setStatusId(orderItem.getStatusId());
      }
      orderItemRepository.save(orderItemDB);
    } catch (NoSuchElementException e) {
      if (orderItem.getSubOrderId() != 0
          && orderItem.getQuantity() != 0
          && orderItem.getStatusId() != 0
          && orderItem.getProductId() != 0) {
        orderItemRepository.save(orderItem);
      } else {
        return "Please provide a valid shopping item";
      }
    } catch (IllegalArgumentException e) {
      return "Please provide a valid shopping item";
    }
    return "Successfully updated the shopping item";
  }

  public String deleteOrderItemById(Integer orderItemId) {
    orderItemRepository.deleteById(orderItemId);
    return "Successfully deleted shopping item with id " + orderItemId;
  }

  public List<OrderItem> loadAllOrderItems() {
    List<OrderItem> orderItems = new ArrayList<>();
    orderItemRepository.findAll().forEach(orderItems::add);
    return orderItems;
  }
}
