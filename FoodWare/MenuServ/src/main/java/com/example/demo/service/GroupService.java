package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Group;
import com.example.demo.repository.GroupRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GroupService {
  @Autowired
  GroupRepository groupRepository;

  @Autowired
  private CategoryService categoryService;

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

  public Optional<Group> loadGroupById(int groupId) {
    return groupRepository.findById(groupId);
  }

  public String deleteGroupById(int groupId) {
    boolean existCategoryForGroupId = false;
    List<Category> categories = categoryService.loadAllCategories();
    for (Category category : categories) {
      if (category.getGroupId() == groupId) {
        existCategoryForGroupId = true;
        break;
      }
    }
    if (existCategoryForGroupId) {
      return "Cannot delete group while categories reference it";
    } else {
      groupRepository.deleteById(groupId);
      return "Successfully deleted the group: " + groupId;
    }
  }

  public String updateGroup(Group group, int id) {
    try {
      Optional<Group> groupDB = loadGroupById(id);
      if (groupDB.isPresent()) {
        groupDB.get().setName(group.getName());
      }
      groupRepository.save(groupDB.get());
    } catch (IllegalArgumentException e) {
      return "Please provide a valid group";
    }
    return "Successfully updated the group";
  }
}
