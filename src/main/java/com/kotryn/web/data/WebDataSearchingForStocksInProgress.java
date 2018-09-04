package com.kotryn.web.data;

public class WebDataSearchingForStocksInProgress implements IWebData {

    private final Long jobId;
    private Action action;

    public WebDataSearchingForStocksInProgress(Long jobId) {
        this.jobId = jobId;
        action = Action.UNKNOWN;
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
