package com.kotryn.web.data;

public class WebDataBuildingRobustPortfolioSetup implements IWebData {

    private final Long jobId;
    private Action action;
    private Integer numberOfSamples;
    private Double yearRateOfReturn;
    private Double toleranceLevel;
    private Double maxShare;

    public WebDataBuildingRobustPortfolioSetup(Long jobId) {
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

    public Integer getNumberOfSamples() {
        return numberOfSamples;
    }

    public void setNumberOfSamples(Integer numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }

    public Double getYearRateOfReturn() {
        return yearRateOfReturn;
    }

    public void setYearRateOfReturn(Double yearRateOfReturn) {
        this.yearRateOfReturn = yearRateOfReturn;
    }

    public Double getToleranceLevel() {
        return toleranceLevel;
    }

    public void setToleranceLevel(Double toleranceLevel) {
        this.toleranceLevel = toleranceLevel;
    }

    public Double getMaxShare() {
        return maxShare;
    }

    public void setMaxShare(Double maxShare) {
        this.maxShare = maxShare;
    }
}