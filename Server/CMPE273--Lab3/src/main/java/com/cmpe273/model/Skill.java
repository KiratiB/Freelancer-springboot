package com.cmpe273.model;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Skill")
public class Skill {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}