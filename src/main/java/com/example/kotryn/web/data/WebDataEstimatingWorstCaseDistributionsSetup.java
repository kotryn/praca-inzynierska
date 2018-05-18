package com.example.kotryn.web.data;

public class WebDataEstimatingWorstCaseDistributionsSetup implements IWebData {

    private final Long jobId;
    private Action action;
    /*private String startDate;
    private String endDate;*/

    public WebDataEstimatingWorstCaseDistributionsSetup(Long jobId) {
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
}