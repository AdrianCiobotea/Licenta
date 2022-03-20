package com.example.demo.service;

import com.example.demo.dto.GroupDetails;
import com.example.demo.entity.Group;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.Product;
import com.example.demo.repository.GroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Value("${spring.datasource.password}")
    private String databasePassword;
    @Value("${spring.datasource.username}")
    private String databaseUserName;
    @Value("${spring.datasource.url}")
    private String databaseUrl;

    public String insertGroup(Group group) {
        try {
            groupRepository.save(group);
        } catch (IllegalArgumentException e) {
            return "Please provide a valid group";
        }
        return "Successfully inserted a group";
    }

    public List<Group> loadAllGroups() {
        List<Group> groups = new ArrayList<>();
        groupRepository.findAll().forEach(groups::add);
        return groups;
    }

    public void deleteGroupById(int groupId) {
        groupRepository.deleteById(groupId);
    }

    public String updateGroup(Group group) {
        try {
            groupRepository.save(group);
        } catch (IllegalArgumentException e) {
            return "Please provide a valid group";
        }
        return "Successfully updated the group";
    }
}
