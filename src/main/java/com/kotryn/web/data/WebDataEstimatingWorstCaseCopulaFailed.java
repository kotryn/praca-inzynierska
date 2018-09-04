package com.kotryn.web.data;

public class WebDataEstimatingWorstCaseCopulaFailed implements IWebData {

    private final Long jobId;
    private final Action action;

    public WebDataEstimatingWorstCaseCopulaFailed(Long jobId) {
        this.jobId = jobId;
        action = Action.CONTINUE;
    }

    @Override
    public Long getJobId() {
        return jobId;
    }

    @Override
    public Action getAction() {
        return action;
    }
}