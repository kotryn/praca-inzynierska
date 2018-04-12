package com.example.kotryn.entity.Context;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.states.IState;
import com.example.kotryn.states.State;
import com.example.kotryn.states.StateFactory;
import com.example.kotryn.web.data.IWebData;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Context {
    @Id
    @GeneratedValue
    private Long id;

    private final Long jobId;
    private State state;

    public Context() {
        this.jobId = 0L;
        this.state = State.UNKNOWN;
    }

    public Context(Long jobId) {
        this.jobId = jobId;
        this.state = State.UNKNOWN;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public String redirectToWebPage(MainController controller, JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        IState stateObject = StateFactory.getState(state, jobRepository, contextRepository, processDescriptorRepository);
        return stateObject.redirectToWebPage(this, controller);
    }

    public void handle(IWebData webData, JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        IState stateObject = StateFactory.getState(state, jobRepository, contextRepository, processDescriptorRepository);
        stateObject.handle(this, webData);
    }
}
