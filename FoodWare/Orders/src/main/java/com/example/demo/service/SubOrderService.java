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
      if (subOrder.getStatusId() == 0) {
        subOrderDB.setStatusId(subOrderDB.getStatusId());
      } else {
        subOrderDB.setStatusId(subOrder.getStatusId());
      }
      if (subOrder.getPaymentId() == 0) {
        subOrderDB.setPaymentId(subOrderDB.getPaymentId());
      } else {
        subOrderDB.setPaymentId(subOrder.getPaymentId());
      }
      subOrderRepository.save(subOrderDB);
    } catch (NoSuchElementException e) {
      if (subOrder.getStatusId() != 0 && subOrder.getPaymentId() != 0) {
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
