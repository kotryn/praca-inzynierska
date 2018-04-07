package com.example.kotryn.entity.Job;

import javax.persistence.*;
import java.util.List;

@Entity
//@Table
//(name = "app_user")
public class Job {

    @Id
    @GeneratedValue
    private Long id;
    private String startDate;
    private String endDate;
    private String state;

    public Job(){
        this.startDate = "not set";
        this.endDate = "not set";
        this.state = "new";
    }

    public Job(String state){
        this.startDate = "not set";
        this.endDate = "not set";
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}