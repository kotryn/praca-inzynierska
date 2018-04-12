package com.example.kotryn.web.data;

public class WebDataObtainingPeriodOfAnalysis implements IWebData {

    private final Long jobId;
    private final Action action;
    private String startDate;
    private String endDate;

    public WebDataObtainingPeriodOfAnalysis(Long jobId) {
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
