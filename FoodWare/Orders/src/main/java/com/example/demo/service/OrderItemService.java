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
      if (orderItem.getProduct_id() == 0) {
        orderItemDB.setProduct_id(orderItemDB.getProduct_id());
      } else {
        orderItemDB.setProduct_id(orderItem.getProduct_id());
      }
      if (orderItem.getSub_order_id() == 0) {
        orderItemDB.setSub_order_id(orderItemDB.getSub_order_id());
      } else {
        orderItemDB.setSub_order_id(orderItem.getSub_order_id());
      }
      if (orderItem.getQuantity() == 0) {
        orderItemDB.setQuantity(orderItemDB.getQuantity());
      } else {
        orderItemDB.setQuantity(orderItem.getQuantity());
      }
      if (orderItem.getStatus_id() == 0) {
        orderItemDB.setStatus_id(orderItemDB.getStatus_id());
      } else {
        orderItemDB.setStatus_id(orderItem.getStatus_id());
      }
      orderItemRepository.save(orderItemDB);
    } catch (NoSuchElementException e) {
      if (orderItem.getSub_order_id() != 0
          && orderItem.getQuantity() != 0
          && orderItem.getStatus_id() != 0
          && orderItem.getProduct_id() != 0) {
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
