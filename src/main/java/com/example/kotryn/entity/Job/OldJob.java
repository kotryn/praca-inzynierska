package com.example.kotryn.entity.Job;

import com.example.kotryn.states.JobState;

public class OldJob implements JobState{
    Job job;

    public OldJob(){
    }

    public OldJob(Job job){
        this.job=job;
    }

    public void connect(Job job) {
        System.out.println("Your job ID is " + job.getId());
    }
}
