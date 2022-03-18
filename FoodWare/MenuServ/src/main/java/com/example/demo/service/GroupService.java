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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GroupService {

  @Value("${spring.datasource.password}")
  private String databasePassword;
  @Value("${spring.datasource.username}")
  private String databaseUserName;
  @Value("${spring.datasource.url}")
  private String databaseUrl;

  public String insertGroup(Group group) {
    //boolean groupExists = loadGroupByName(group.getName()) != null;
//    if (groupExists) {
//      return "group already exists in the database";
//    }

    try (Connection conn = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
         PreparedStatement insertStatement = conn.prepareStatement("insert into foodware.group (name) values (?)")) {
      insertStatement.setString(1, group.getName());
      int result = insertStatement.executeUpdate();
      if (result!=0) {
        return "group successfully inserted";
      }
    } catch (SQLException throwables) {
      log.error("could not insert the group " + throwables.getMessage());
      return null;
    }
    return null;
  }

  public GroupDetails loadGroupById(int groupId) {
    try (Connection conn = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
         PreparedStatement selectStatement = conn.prepareStatement("select name,group_id from foodware.group where id=?");) {
      selectStatement.setInt(1, groupId);
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next()) {
        String groupName = rs.getString("name");

        GroupDetails group = new GroupDetails();
        group.setName(groupName);
        return group;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }
    return null;
  }

  public GroupDetails loadGroupByName(String groupName) {
    try (Connection conn = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
         PreparedStatement selectStatement = conn.prepareStatement("select name,id from foodware.group where name=?");) {
      selectStatement.setString(1, groupName);
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next()) {
        String name = rs.getString("name");
        Long groupId = rs.getLong("id");
        GroupDetails group = new GroupDetails();
        group.setName(groupName);
        group.setId(groupId);
        return group;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }
    return null;
  }
  public List<GroupDetails> loadAllGroups() {
    List<GroupDetails> groups = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
         PreparedStatement selectStatement = conn.prepareStatement("select  id,name from foodware.group");) {
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next()) {
        String name = rs.getString("name");
        Long categoryId = rs.getLong("id");

        GroupDetails group = new GroupDetails();
        group.setName(name);
        group.setId(categoryId);
        groups.add(group);
      }
      return groups;
    } catch (SQLException e) {
      log.error("error while trying to get group information from database");
      return null;
    }
  }
}
