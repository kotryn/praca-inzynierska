package com.example.kotryn.entity.Job;

import com.example.kotryn.json.Page;
import com.example.kotryn.states.JobState;

public class OldJob implements JobState{
    Job job;

    public OldJob(){
    }

    public OldJob(Job job){
        this.job=job;
    }

    public Page getConnectPage(Long id) {
        return null;
    }

    public Page getSupplyPeriodPage(Job job){
        return null;
    }

    public String connect(Job job) {
        System.out.println("Your job ID is " + job.getId());
        return "/page/1";
    }
}
