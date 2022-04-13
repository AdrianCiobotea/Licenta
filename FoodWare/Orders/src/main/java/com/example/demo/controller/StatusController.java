package com.example.demo.controller;


import com.example.demo.entity.Status;
import com.example.demo.service.StatusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "status")
public class StatusController {

  @Autowired
  StatusService statusService;

  @PostMapping(path = "insert")
  public String addStatus(@RequestBody Status status) {
    return statusService.insertStatus(status);
  }

  @GetMapping()
  public List<Status> getAllStatuses() {
    return statusService.loadAllStatuses();
  }

  @GetMapping(path = "/{id}")
  public java.util.Optional<Status> getStatusById(@PathVariable Integer id) {
    return statusService.loadStatusById(id);
  }

  @PostMapping(path = "update/{id}")
  public String updateStatus(@PathVariable Integer id, @RequestBody Status status) {
    return statusService.updateStatus(status, id);
  }

  @PostMapping(path = "delete/{id}")
  public String deleteStatus(@PathVariable Integer id) {
    return statusService.deleteStatusById(id);
  }

}
