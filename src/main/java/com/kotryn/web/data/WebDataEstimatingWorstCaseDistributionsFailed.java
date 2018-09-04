package com.kotryn.web.data;

public class WebDataEstimatingWorstCaseDistributionsFailed implements IWebData {

    private final Long jobId;
    private final Action action;

    public WebDataEstimatingWorstCaseDistributionsFailed(Long jobId) {
        this.jobId = jobId;
        action = Action.CONTINUE;
    }

    @Override
    public Long getJobId() {
        return jobId;
    }

    @Override
    public Action getAction() {
        return action;
    }
}

