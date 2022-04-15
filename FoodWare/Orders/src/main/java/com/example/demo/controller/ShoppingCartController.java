package com.example.demo.controller;


import com.example.demo.entity.ShoppingCart;
import com.example.demo.service.ShoppingCartService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "shoppingCart")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ShoppingCartController {

  @Autowired
  ShoppingCartService shoppingCartService;

  @PostMapping(path = "insert")
  public Optional<ShoppingCart> addShoppingCart(@RequestBody ShoppingCart ShoppingCart) {
    return shoppingCartService.insertShoppingCart(ShoppingCart);
  }

  @GetMapping()
  public List<ShoppingCart> getAllShoppingCarts() {
    return shoppingCartService.loadAllShoppingCarts();
  }

  @GetMapping(path = "/{id}")
  public java.util.Optional<ShoppingCart> getShoppingCartById(@PathVariable Integer id) {
    return shoppingCartService.loadShoppingCartById(id);
  }

  @PostMapping(path = "update/{id}")
  public String updateShoppingCart(@PathVariable Integer id, @RequestBody ShoppingCart ShoppingCart) {
    return shoppingCartService.updateShoppingCart(ShoppingCart, id);
  }

  @PostMapping(path = "delete/{id}")
  public String deleteShoppingCart(@PathVariable Integer id) {
    return shoppingCartService.deleteShoppingCartById(id);
  }

}
