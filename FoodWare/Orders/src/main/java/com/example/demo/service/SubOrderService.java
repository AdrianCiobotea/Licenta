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

  public Optional<SubOrder> insertSubOrder(SubOrder subOrder) {
    SubOrder subOrderDB;
    try {
      subOrderDB = subOrderRepository.save(subOrder);
    } catch (IllegalArgumentException e) {
      return Optional.empty();
    }
    return Optional.of(subOrderDB);
  }

  public List<SubOrder> loadAllSubOrders() {
    List<SubOrder> subOrders = new ArrayList<>();
    subOrderRepository.findAll().forEach(subOrders::add);
    return subOrders;
  }

  public Optional<SubOrder> loadSubOrderById(Integer subOrderId) {
    return subOrderRepository.findById(subOrderId);
  }

  public String updateSubOrder(SubOrder subOrder, Integer subOrderId) {

    try {
      SubOrder subOrderDB = loadSubOrderById(subOrderId).get();
      if (subOrder.getStatus_id() == 0) {
        subOrderDB.setStatus_id(subOrderDB.getStatus_id());
      } else {
        subOrderDB.setStatus_id(subOrder.getStatus_id());
      }
      if (subOrder.getPayment_id() == 0) {
        subOrderDB.setPayment_id(subOrderDB.getPayment_id());
      } else {
        subOrderDB.setPayment_id(subOrder.getPayment_id());
      }
      subOrderRepository.save(subOrderDB);
    } catch (NoSuchElementException e) {
      if (subOrder.getStatus_id() != 0 && subOrder.getPayment_id() != 0) {
        subOrderRepository.save(subOrder);
      } else {
        return "Please provide a valid SubOrder";
      }
    } catch (IllegalArgumentException e) {
      return "Please provide a valid SubOrder";
    }
    return "Successfully updated the SubOrder";
  }

  public String deleteSubOrderById(int subOrderId) {
    subOrderRepository.deleteById(subOrderId);
    return "Successfully deleted subOrder with id " + subOrderId;
  }

}
