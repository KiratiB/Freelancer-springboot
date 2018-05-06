package com.cmpe273.model;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "User")
public class ProjectBid {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer userId;

    private Integer projectId;

    private Integer bidValue;

    private Integer bidPeriod;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getBidValue() {
        return bidValue;
    }

    public void setBidValue(Integer bidValue) {
        this.bidValue = bidValue;
    }

    public Integer getBidPeriod() {
        return bidPeriod;
    }

    public void setBidPeriod(Integer bidPeriod) {
        this.bidPeriod = bidPeriod;
    }
}