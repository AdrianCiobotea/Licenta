package com.example.demo.controller;

import com.example.demo.entity.Group;
import com.example.demo.service.GroupService;
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
@RequestMapping(path = "group")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @PostMapping(path = "update/{id}")
    public String updateGroup(@PathVariable Integer id, @RequestBody Group group) {
        return groupService.updateGroup(group, id);
    }

    @PostMapping(path = "delete/{id}")
    public void deleteGroup(@PathVariable Integer id) {
        groupService.deleteGroupById(id);
    }
}
