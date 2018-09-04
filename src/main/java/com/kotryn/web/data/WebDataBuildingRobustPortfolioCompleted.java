package com.kotryn.web.data;

import java.util.List;

public class WebDataBuildingRobustPortfolioCompleted implements IWebData {

    private final Long jobId;
    private Action action;
    private List<String> portfolioCompany;
    private List<String> portfolioShare;

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

    public List<String> getPortfolioCompany() {
        return portfolioCompany;
    }

    public void setPortfolioCompany(List<String> portfolioCompany) {
        this.portfolioCompany = portfolioCompany;
    }

    public List<String> getPortfolioShare() {
        return portfolioShare;
    }

    public void setPortfolioShare(List<String> portfolioShare) {
        this.portfolioShare = portfolioShare;
    }
}