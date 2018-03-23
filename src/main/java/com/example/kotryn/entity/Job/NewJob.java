package com.example.kotryn.entity.Job;

import com.example.kotryn.states.JobState;

public class NewJob implements JobState {
    Job job;
    public NewJob(Job job){
        this.job=job;
    }

    public void connect(Job job) {
        System.out.println("Your new job ID is " + job.getId());
    }
}
