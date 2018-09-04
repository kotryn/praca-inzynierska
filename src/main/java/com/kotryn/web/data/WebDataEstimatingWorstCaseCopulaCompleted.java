package com.kotryn.web.data;

import java.util.List;

public class WebDataEstimatingWorstCaseCopulaCompleted implements IWebData {

    private final Long jobId;
    private Action action;
    private List<String> selectedWorstCaseCopula;

    public WebDataEstimatingWorstCaseCopulaCompleted(Long jobId) {
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

    public List<String> getSelectedWorstCaseCopula() {
        return selectedWorstCaseCopula;
    }

    public void setSelectedWorstCaseCopula(List<String> selectedWorstCaseCopula) {
        this.selectedWorstCaseCopula = selectedWorstCaseCopula;
    }
}