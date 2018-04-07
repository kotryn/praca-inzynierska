package com.example.kotryn.entity.Job;

import com.example.kotryn.json.Page;

public interface JobState {

    Page getJobId();

    Page getConnectPage(Long id);
}
