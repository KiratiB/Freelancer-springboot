package com.cmpe273.model;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "User")
public class User {
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getProf_headline() {
        return prof_headline;
    }

    public void setProf_headline(String prof_headline) {
        this.prof_headline = prof_headline;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getUserskills() {
        return userskills;
    }

    public void setUserskills(String userskills) {
        this.userskills = userskills;
    }

    public String getProfilepicpath() {
        return profilepicpath;
    }

    public void setProfilepicpath(String profilepicpath) {
        this.profilepicpath = profilepicpath;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer userId;

    private String username;

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private String prof_headline;

    private Integer phone;

    private String userskills;

    private String profilepicpath;


}