package com.example.kotryn.web.data;

public class WebDataEstimatingGrowthStocksSetup implements IWebData {

    private final Long jobId;
    private Action action;
    private Integer maxNumberSector;
    private Integer maxNumberIndustry;
    private Double maxCoefficient;


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

    public Integer getMaxNumberSector() {
        return maxNumberSector;
    }

    public void setMaxNumberSector(Integer maxNumberSector) {
        this.maxNumberSector = maxNumberSector;
    }

    public Integer getMaxNumberIndustry() {
        return maxNumberIndustry;
    }

    public void setMaxNumberIndustry(Integer maxNumberIndustry) {
        this.maxNumberIndustry = maxNumberIndustry;
    }

    public Double getMaxCoefficient() {
        return maxCoefficient;
    }

    public void setMaxCoefficient(Double maxCoefficient) {
        this.maxCoefficient = maxCoefficient;
    }
}