package com.kotryn.web.data;

import java.util.List;

public class WebDataObtainingStocks implements IWebData {

    private final Long jobId;
    private final Action action;
    private List<String> selectedStocks;

    public WebDataObtainingStocks(Long jobId) {
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

    public List<String> getSelectedStocks() {
        return selectedStocks;
    }

    public void setSelectedStocks(List<String> selectedStocks) {
        this.selectedStocks = selectedStocks;
    }
}
