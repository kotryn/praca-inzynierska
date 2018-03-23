package com.example.kotryn.states;

import com.example.kotryn.entity.Job.Job;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

public interface JobState {
    void connect(Job job);
}
