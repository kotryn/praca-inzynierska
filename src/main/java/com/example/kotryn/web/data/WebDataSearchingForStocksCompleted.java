package com.example.kotryn.web.data;

import java.util.List;

public class WebDataSearchingForStocksCompleted implements IWebData {

    private final Long jobId;
    private Action action;
    private List<String> selectedStocks;

    public WebDataSearchingForStocksCompleted(Long jobId) {
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

    public List<String> getSelectedStocks() {
        return selectedStocks;
    }

    public void setSelectedStocks(List<String> selectedStocks) {
        this.selectedStocks = selectedStocks;
    }
}
