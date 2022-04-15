package com.example.demo.controller;


import com.example.demo.entity.Payment;
import com.example.demo.service.PaymentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "payment")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentController {

  @Autowired
  PaymentService paymentService;

  @PostMapping(path = "insert")
  public String addPayment(@RequestBody Payment payment) {
    return paymentService.insertPayment(payment);
  }

  @GetMapping()
  public List<Payment> getAllPayments() {
    return paymentService.loadAllPayments();
  }

  @GetMapping(path = "/{id}")
  public java.util.Optional<Payment> getPaymentById(@PathVariable Integer id) {
    return paymentService.loadPaymentById(id);
  }

  @PostMapping(path = "update/{id}")
  public String updatePayment(@PathVariable Integer id, @RequestBody Payment payment) {
    return paymentService.updatePayment(payment, id);
  }

  @PostMapping(path = "delete/{id}")
  public String deletePayment(@PathVariable Integer id) {
    return paymentService.deletePaymentById(id);
  }

}
