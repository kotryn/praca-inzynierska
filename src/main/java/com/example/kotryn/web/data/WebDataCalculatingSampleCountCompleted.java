package com.example.kotryn.web.data;

import java.util.List;

public class WebDataCalculatingSampleCountCompleted implements IWebData {

    private final Long jobId;
    private Action action;
    private List<String> selectedCalculatingSample;

    public WebDataCalculatingSampleCountCompleted(Long jobId) {
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

    public List<String> getSelectedCalculatingSample() {
        return selectedCalculatingSample;
    }

    public void setSelectedCalculatingSample(List<String> selectedCalculatingSample) {
        this.selectedCalculatingSample = selectedCalculatingSample;
    }
}
