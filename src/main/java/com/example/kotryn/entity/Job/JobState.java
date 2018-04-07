package com.example.kotryn.entity.Job;

import com.example.kotryn.json.Page;

public interface JobState {
    //Page connect();

    public Page getJobId();

    Page getConnectPage(Long id);

    //Page getSupplyPeriodPage(Job job);
}
