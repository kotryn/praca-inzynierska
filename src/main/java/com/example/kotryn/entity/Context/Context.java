package com.example.kotryn.entity.Context;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.states.IState;
import com.example.kotryn.states.State;
import com.example.kotryn.states.StateFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Context {
    @Id
    @GeneratedValue
    private Long id;

    private final long jobId;
    private State state;

    public Context() {
        this.jobId = 0;
        this.state = State.UNKNOWN;
    }

    public Context(long jobId) {
        this.jobId = jobId;
        this.state = State.UNKNOWN;
    }

    public long getJobId() {
        return jobId;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public String redirectToWebPage(MainController controller) {
        IState stateObject = StateFactory.getState(state);
        return stateObject.redirectToWebPage(this, controller);
    }

    /*public void handle(IWebData webData) {
        IState stateObject = StateFactory.getState(state);
        stateObject.handle(this, webData);
    }*/
}
