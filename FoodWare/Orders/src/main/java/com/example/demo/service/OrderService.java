package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {
  @Autowired
  OrderRepository orderRepository;

  public String insertOrder(Order order) {
    try {
      orderRepository.save(order);
    } catch (IllegalArgumentException e) {
      return "Please provide a valid order";
    }
    return "Successfully inserted an order";
  }

  public List<Order> loadAllOrders() {
    List<Order> orders = new ArrayList<>();
    orderRepository.findAll().forEach(orders::add);
    return orders;
  }

  public Optional<Order> loadOrderById(Integer orderId) {
    return orderRepository.findById(orderId);
  }

  public String updateOrder(Order order, Integer orderId) {

    try {
      Order orderDB = loadOrderById(orderId).get();
      if (order.getInitiator_id() == 0) {
        orderDB.setInitiator_id(orderDB.getInitiator_id());
      } else {
        orderDB.setInitiator_id(order.getInitiator_id());
      }
      if (order.getPayment_id() == 0) {
        orderDB.setPayment_id(orderDB.getPayment_id());
      } else {
        orderDB.setPayment_id(order.getPayment_id());
      }

      orderRepository.save(orderDB);
    } catch (NoSuchElementException e) {
      if (order.getPayment_id() != 0 && order.getInitiator_id() != 0) {
        orderRepository.save(order);
      } else {
        return "Please provide a valid order";
      }
    } catch (IllegalArgumentException e) {
      return "Please provide a valid order";
    }
    return "Successfully updated the order";
  }

  public String deleteOrderById(int orderId) {
    orderRepository.deleteById(orderId);
    return "Successfully deleted order with id " + orderId;
  }

}
