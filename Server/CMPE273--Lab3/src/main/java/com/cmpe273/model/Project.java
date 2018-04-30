package com.cmpe273.model;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Project")
public class Project {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer projectId;

    private String project_name;

    private String description;

    private Integer budget_range_start;

    private Integer budget_range_end;

    private String projectSkills;

    private Integer userId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProject_id(Integer project_id) {
        this.projectId = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBudget_range_start() {
        return budget_range_start;
    }

    public void setBudget_range_start(Integer budget_range_start) {
        this.budget_range_start = budget_range_start;
    }

    public Integer getBudget_range_end() {
        return budget_range_end;
    }

    public void setBudget_range_end(Integer budget_range_end) {
        this.budget_range_end = budget_range_end;
    }

    public String getProjectSkills() {
        return projectSkills;
    }

    public void setProjectSkills(String projectSkills) {
        this.projectSkills = projectSkills;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user_id) {
        this.userId = user_id;
    }

}