package com.example.kotryn.web.data;

import java.util.List;

public class WebDataEstimatingWorstCaseDistributionsCompleted implements IWebData {

    private final Long jobId;
    private Action action;
    private List<String> selectedWorstCaseDistributions;

    public WebDataEstimatingWorstCaseDistributionsCompleted(Long jobId) {
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

    public List<String> getSelectedWorstCaseDistributions() {
        return selectedWorstCaseDistributions;
    }

    public void setSelectedWorstCaseDistributions(List<String> selectedWorstCaseDistributions) {
        this.selectedWorstCaseDistributions = selectedWorstCaseDistributions;
    }
}