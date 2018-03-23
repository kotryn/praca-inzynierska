package com.example.kotryn.entity.Job;

import com.example.kotryn.states.JobState;

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

    @Transient
    private JobState state;

    public Job(){
        this.startDate = "not set";
        this.endDate = "not set";
        this.state = new NewJob(this);
    }

    public Job(JobState state){
        this.startDate = "not set";
        this.endDate = "not set";
        this.state = state;
    }

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

    public void setState(JobState state) {
        this.state = state;
    }

    public JobState getState() {
        return state;
    }

    public void connect() {
        if (state != null) {
            state.connect(this);
        } else {
            System.out.println("status unknown");
        }
    }
}