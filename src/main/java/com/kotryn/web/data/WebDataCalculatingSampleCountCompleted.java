package com.kotryn.web.data;

public class WebDataCalculatingSampleCountCompleted implements IWebData {

    private final Long jobId;
    private Action action;

    public WebDataCalculatingSampleCountCompleted(Long jobId) {
        this.jobId = jobId;
        action = Action.NEXT;
    }

    @Override
    public Long getJobId() {
        return jobId;
    }

    @Override
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
