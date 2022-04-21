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
  OrderRepository shoppingCartRepository;

  public Optional<Order> insertOrder(Order shoppingCart) {
    Order shoppingCartDB;
    try {
      shoppingCartDB = shoppingCartRepository.save(shoppingCart);
    } catch (IllegalArgumentException e) {
      return Optional.empty();
    }
    return Optional.of(shoppingCartDB);
  }

  public List<Order> loadAllOrders() {
    List<Order> shoppingCarts = new ArrayList<>();
    shoppingCartRepository.findAll().forEach(shoppingCarts::add);
    return shoppingCarts;
  }

  public Optional<Order> loadOrderById(Integer shoppingCartId) {
    return shoppingCartRepository.findById(shoppingCartId);
  }

  public String updateOrder(Order shoppingCart, Integer shoppingCartId) {

    try {
      Order shoppingCartDB = loadOrderById(shoppingCartId).get();
      if (shoppingCart.getInitiator_id() == 0) {
        shoppingCartDB.setInitiator_id(shoppingCartDB.getInitiator_id());
      } else {
        shoppingCartDB.setInitiator_id(shoppingCart.getInitiator_id());
      }
//      if (shoppingCart.getPayment_id() == 0) {
//        shoppingCartDB.setPayment_id(shoppingCartDB.getPayment_id());
//      } else {
//        shoppingCartDB.setPayment_id(shoppingCart.getPayment_id());
//      }

      shoppingCartRepository.save(shoppingCartDB);
    } catch (NoSuchElementException e) {
      if (shoppingCart.getInitiator_id() != 0) {
        shoppingCartRepository.save(shoppingCart);
      } else {
        return "Please provide a valid Order";
      }
    } catch (IllegalArgumentException e) {
      return "Please provide a valid Order";
    }
    return "Successfully updated the Order";
  }

  public String deleteOrderById(int shoppingCartId) {
    shoppingCartRepository.deleteById(shoppingCartId);
    return "Successfully deleted Order with id " + shoppingCartId;
  }

}
