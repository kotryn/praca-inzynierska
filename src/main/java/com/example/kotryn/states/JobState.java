package com.example.kotryn.states;

import com.example.kotryn.entity.Job.Job;
import com.example.kotryn.json.Page;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

public interface JobState {
    String connect(Job job);

    Page getConnectPage(Long id);

    Page getSupplyPeriodPage(Job job);
}
