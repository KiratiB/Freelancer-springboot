package com.cmpe273.repository;
import com.cmpe273.model.Project;
import com.cmpe273.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByUserId(int userId);

}
