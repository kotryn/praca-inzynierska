package com.example.kotryn.web.data;

public class WebDataSearchingForStocksFailed implements IWebData {

    private final long jobId;
    private final Action action;

    public WebDataSearchingForStocksFailed(long jobId) {
        this.jobId = jobId;
        action = Action.CONTINUE;
    }

    @Override
    public long getJobId() {
        return jobId;
    }

    @Override
    public Action getAction() {
        return action;
    }
}
