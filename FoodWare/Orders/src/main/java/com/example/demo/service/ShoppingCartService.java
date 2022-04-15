package com.example.demo.service;

import com.example.demo.entity.ShoppingCart;
import com.example.demo.repository.ShoppingCartRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShoppingCartService {
  @Autowired
  ShoppingCartRepository shoppingCartRepository;

  public Optional<ShoppingCart> insertShoppingCart(ShoppingCart shoppingCart) {
    ShoppingCart shoppingCartDB;
    try {
      shoppingCartDB = shoppingCartRepository.save(shoppingCart);
    } catch (IllegalArgumentException e) {
      return Optional.empty();
    }
    return Optional.of(shoppingCartDB);
  }

  public List<ShoppingCart> loadAllShoppingCarts() {
    List<ShoppingCart> shoppingCarts = new ArrayList<>();
    shoppingCartRepository.findAll().forEach(shoppingCarts::add);
    return shoppingCarts;
  }

  public Optional<ShoppingCart> loadShoppingCartById(Integer shoppingCartId) {
    return shoppingCartRepository.findById(shoppingCartId);
  }

  public String updateShoppingCart(ShoppingCart shoppingCart, Integer shoppingCartId) {

    try {
      ShoppingCart shoppingCartDB = loadShoppingCartById(shoppingCartId).get();
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
        return "Please provide a valid ShoppingCart";
      }
    } catch (IllegalArgumentException e) {
      return "Please provide a valid ShoppingCart";
    }
    return "Successfully updated the ShoppingCart";
  }

  public String deleteShoppingCartById(int shoppingCartId) {
    shoppingCartRepository.deleteById(shoppingCartId);
    return "Successfully deleted ShoppingCart with id " + shoppingCartId;
  }

}
