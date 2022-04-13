package com.example.demo.service;

import com.example.demo.entity.Payment;
import com.example.demo.repository.PaymentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {
  @Autowired
  PaymentRepository paymentRepository;

  public String deletePaymentById(Integer paymentId) {
    paymentRepository.deleteById(paymentId);
    return "Successfully deleted payment with id " + paymentId;
  }

  public String updatePayment(Payment payment, Integer paymentId) {
    return "";
  }

  public Optional<Payment> loadPaymentById(Integer paymentId) {
    return paymentRepository.findById(paymentId);
  }

  public List<Payment> loadAllPayments() {
    List<Payment> payments = new ArrayList<>();
    paymentRepository.findAll().forEach(payments::add);
    return payments;
  }

  public String insertPayment(Payment payment) {
    try {
      paymentRepository.save(payment);
    } catch (IllegalArgumentException e) {
      return "Please provide a valid payment";
    }
    return "Successfully inserted a payment";
  }
}
