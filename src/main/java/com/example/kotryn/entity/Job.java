package com.example.kotryn.entity;

import javax.persistence.*;

@Entity
//@Table
//(name = "app_user")
public class Job {

    @Id
    @GeneratedValue
    private long id;
    private String startDate;
    private String endDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}