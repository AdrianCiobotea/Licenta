package com.example.demo.service;

import com.example.demo.entity.SubOrder;
import com.example.demo.repository.SubOrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubOrderService {

  @Autowired
  SubOrderRepository subOrderRepository;


  public String insertSubOrder(SubOrder subOrder) {
    try {
      subOrderRepository.save(subOrder);
    } catch (IllegalArgumentException e) {
      return "Please provide a valid sub-order";
    }
    return "Successfully inserted a sub-order";
  }

  public List<SubOrder> loadAllSubOrders() {
    List<SubOrder> subOrders = new ArrayList<>();
    subOrderRepository.findAll().forEach(subOrders::add);
    return subOrders;
  }

  public Optional<SubOrder> loadSubOrderById(Integer id) {
    return subOrderRepository.findById(id);
  }

  public String updateSubOrder(SubOrder subOrder, Integer id) {
    try {
      SubOrder subOrderDB = loadSubOrderById(id).get();
      if (subOrder.getPayment_id() == 0) {
        subOrderDB.setPayment_id(subOrderDB.getPayment_id());
      } else {
        subOrderDB.setPayment_id(subOrder.getPayment_id());
      }
      if (subOrder.getUser_id() == 0) {
        subOrderDB.setUser_id(subOrderDB.getUser_id());
      } else {
        subOrderDB.setUser_id(subOrder.getUser_id());
      }

      subOrderRepository.save(subOrderDB);
    } catch (NoSuchElementException e) {
      if (subOrder.getPayment_id() != 0
          && subOrder.getUser_id() != 0) {
        subOrderRepository.save(subOrder);
      } else {
        return "Please provide a valid sub-order";
      }
    } catch (IllegalArgumentException e) {
      return "Please provide a valid sub-order";
    }
    return "Successfully updated the sub-order";
  }

  public String deleteSubOrderById(Integer id) {
    subOrderRepository.deleteById(id);
    return "Successfully deleted sub-order with id " + id;
  }
}
