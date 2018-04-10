package com.example.kotryn.web.data;

import java.util.List;

public class WebDataSearchingForStocksCompleted implements IWebData {

    private final long jobId;
    private Action action;
    private List<String> selectedStocks;

    public WebDataSearchingForStocksCompleted(long jobId) {
        this.jobId = jobId;
        action = Action.UNKNOWN;
    }

    @Override
    public long getJobId() {
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
