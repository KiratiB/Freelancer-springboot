package com.cmpe273.repository;

import com.cmpe273.model.ProjectBid;
import com.cmpe273.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProjectBidRepository extends JpaRepository<ProjectBid, Integer> {
    List<ProjectBid> findByProjectId(int userId);
}


