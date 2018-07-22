package com.example.kotryn.web.data;

public class WebDataCalculatingSampleCountFailed implements IWebData {

    private final Long jobId;
    private final Action action;

    public WebDataCalculatingSampleCountFailed(Long jobId) {
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
