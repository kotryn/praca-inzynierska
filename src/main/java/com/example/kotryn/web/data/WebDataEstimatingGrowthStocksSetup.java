package com.example.kotryn.web.data;

public class WebDataEstimatingGrowthStocksSetup implements IWebData {

    private final Long jobId;
    private Action action;

    public WebDataEstimatingGrowthStocksSetup(Long jobId) {
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