package com.example.demo.controller;

import com.example.demo.entity.Group;
import com.example.demo.entity.Product;
import com.example.demo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Group> getAllGroups() {
        return groupService.loadAllGroups();
    }

    @GetMapping(path = "/{id}")
    public java.util.Optional<Group> getGroupById(@PathVariable Integer id) {
        return groupService.loadGroupById(id);
    }

    @PostMapping(path = "delete/{id}")
    public void deleteGroup(@PathVariable Integer id) {
        groupService.deleteGroupById(id);
    }
}
