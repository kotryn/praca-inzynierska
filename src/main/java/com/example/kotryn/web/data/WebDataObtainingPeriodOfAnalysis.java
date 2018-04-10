package com.example.kotryn.web.data;

public class WebDataObtainingPeriodOfAnalysis implements IWebData {

    private final long jobId;
    private final Action action;
    private String startDate;
    private String endDate;

    public WebDataObtainingPeriodOfAnalysis(long jobId) {
        this.jobId = jobId;
        action = Action.NEXT;
    }

    @Override
    public long getJobId() {
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
