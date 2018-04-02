package com.example.kotryn.entity.Job;

import com.example.kotryn.json.Page;
import com.example.kotryn.states.JobState;

import javax.persistence.*;

@Entity
//@Table
//(name = "app_user")
public class Job {

    @Id
    @GeneratedValue
    private Long id;
    private String startDate;
    private String endDate;

    @Transient
    private JobState state;

    public Job(){
        this.startDate = "not set";
        this.endDate = "not set";
        this.state = new NewJob();
    }

    public Job(JobState state){
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

    @Transient
    public void setState(JobState state) {
        this.state = state;
    }

    @Transient
    public JobState getState() {
        return state;
    }

    @Transient
    public String connect() {
        if (state != null) {
            return state.connect(this);
        } else {
            System.out.println("status unknown");
            return null;
        }
    }

    @Transient
    public Page getConnectPage(){
        if (state != null) {
            return state.getConnectPage(this.id);
        } else {
            System.out.println("status unknown");
            return null;
        }
    }

    @Transient
    public Page getSupplyPeriodPage(){
        if (state != null) {
            return state.getSupplyPeriodPage(this);
        } else {
            System.out.println("status unknown");
            return null;
        }
    }
}