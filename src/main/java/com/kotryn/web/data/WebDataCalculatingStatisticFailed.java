package com.kotryn.web.data;

public class WebDataCalculatingStatisticFailed implements IWebData {

    private final Long jobId;
    private final Action action;

    public WebDataCalculatingStatisticFailed(Long jobId) {
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