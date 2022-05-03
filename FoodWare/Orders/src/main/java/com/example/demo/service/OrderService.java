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

  public Optional<Order> insertOrder(Order shoppingCart) {
    Order shoppingCartDB;
    try {
      shoppingCartDB = orderRepository.save(shoppingCart);
    } catch (IllegalArgumentException e) {
      return Optional.empty();
    }
    return Optional.of(shoppingCartDB);
  }

  public List<Order> loadAllOrders() {
    List<Order> shoppingCarts = new ArrayList<>();
    orderRepository.findAll().forEach(shoppingCarts::add);
    return shoppingCarts;
  }

  public Optional<Order> loadOrderById(Integer shoppingCartId) {
    return orderRepository.findById(shoppingCartId);
  }

  public String updateOrder(Order order, Integer orderId) {

    try {
      Order orderDB = loadOrderById(orderId).get();
      if (order.getInitiator_id() == 0) {
        orderDB.setInitiator_id(orderDB.getInitiator_id());
      } else {
        orderDB.setInitiator_id(order.getInitiator_id());
      }

      orderRepository.save(orderDB);
    } catch (NoSuchElementException e) {
      if (order.getInitiator_id() != 0) {
        orderRepository.save(order);
      } else {
        return "Please provide a valid Order";
      }
    } catch (IllegalArgumentException e) {
      return "Please provide a valid Order";
    }
    return "Successfully updated the Order";
  }

  public String deleteOrderById(int shoppingCartId) {
    orderRepository.deleteById(shoppingCartId);
    return "Successfully deleted Order with id " + shoppingCartId;
  }

}
