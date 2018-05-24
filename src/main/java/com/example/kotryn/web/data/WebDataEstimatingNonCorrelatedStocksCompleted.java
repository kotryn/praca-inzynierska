package com.example.kotryn.web.data;

import java.util.List;

public class WebDataEstimatingNonCorrelatedStocksCompleted implements IWebData {

    private final Long jobId;
    private Action action;
    private List<String> selectedNonCorrelatedStocks;

    public WebDataEstimatingNonCorrelatedStocksCompleted(Long jobId) {
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

    public List<String> getSelectedNonCorrelatedStocks() {
        return selectedNonCorrelatedStocks;
    }

    public void setSelectedNonCorrelatedStocks(List<String> selectedNonCorrelatedStocks) {
        this.selectedNonCorrelatedStocks = selectedNonCorrelatedStocks;
    }
}