package com.cmpe273.repository;
import com.cmpe273.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface Skillrepository extends JpaRepository<Skill, Integer> {
    //List<Skill> findByEmailAndPassword(String email, String password);

}

