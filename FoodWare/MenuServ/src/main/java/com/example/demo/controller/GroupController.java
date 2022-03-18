package com.example.demo.controller;

import com.example.demo.dto.GroupDetails;
import com.example.demo.entity.Group;
import com.example.demo.service.GroupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "group")
public class GroupController {

  @Autowired
  private GroupService groupService;

  @PostMapping(path = "insert")
  public String addGroup(@RequestBody Group group) {
    return groupService.insertGroup(group);
  }

  @GetMapping()
  public List<GroupDetails> getAllCategories() {
    return groupService.loadAllGroups();
  }
}
