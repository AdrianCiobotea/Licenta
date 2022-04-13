package com.example.demo.service;

import com.example.demo.entity.Status;
import com.example.demo.repository.StatusRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StatusService {
  @Autowired
  StatusRepository statusRepository;

  public String insertStatus(Status status) {
    try {
      statusRepository.save(status);
    } catch (IllegalArgumentException e) {
      return "Please provide a valid status";
    }
    return "Successfully inserted a status";
  }

  public List<Status> loadAllStatuses() {
    List<Status> statuses = new ArrayList<>();
    statusRepository.findAll().forEach(statuses::add);
    return statuses;
  }

  public Optional<Status> loadStatusById(Integer statusId) {
    return statusRepository.findById(statusId);
  }

  public String updateStatus(Status status, Integer statusId) {
    try {
      Status statusDB = loadStatusById(statusId).get();
      if (status.getStatus() == null) {
        statusDB.setStatus(statusDB.getStatus());
      } else {
        statusDB.setStatus(status.getStatus());
      }
      statusRepository.save(statusDB);
    } catch (NoSuchElementException e) {
      if (status.getStatus() != null) {
        statusRepository.save(status);
      } else {
        return "Please provide a valid status";
      }
    } catch (IllegalArgumentException e) {
      return "Please provide a valid status";
    }
    return "Successfully updated the status";
  }

  public String deleteStatusById(Integer statusId) {
    statusRepository.deleteById(statusId);
    return "Successfully deleted status with id " + statusId;
  }
}
