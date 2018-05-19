package com.example.kotryn.web.data;

import java.util.List;

public class WebDataEstimatingGrowthStocksCompleted implements IWebData {

    private final Long jobId;
    private Action action;
    private List<String> selectedStocks;

    public WebDataEstimatingGrowthStocksCompleted(Long jobId) {
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