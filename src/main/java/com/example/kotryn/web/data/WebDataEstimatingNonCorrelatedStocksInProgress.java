package com.example.kotryn.web.data;

public class WebDataEstimatingNonCorrelatedStocksInProgress implements IWebData {

    private final Long jobId;
    private Action action;

    public WebDataEstimatingNonCorrelatedStocksInProgress(Long jobId) {
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
