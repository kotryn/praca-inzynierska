package com.example.kotryn.web.data;

import java.util.List;

public class WebDataBuildingRobustPortfolioCompleted implements IWebData {

    private final Long jobId;
    private Action action;
    private List<String> selectedRobustPortfolio;

    public WebDataBuildingRobustPortfolioCompleted(Long jobId) {
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

    public List<String> getSelectedRobustPortfolio() {
        return selectedRobustPortfolio;
    }

    public void setSelectedRobustPortfolio(List<String> selectedRobustPortfolio) {
        this.selectedRobustPortfolio = selectedRobustPortfolio;
    }
}