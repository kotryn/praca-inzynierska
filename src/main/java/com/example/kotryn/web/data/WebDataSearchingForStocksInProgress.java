package com.example.kotryn.web.data;

public class WebDataSearchingForStocksInProgress implements IWebData {

    private final long jobId;
    private Action action;

    public WebDataSearchingForStocksInProgress(long jobId) {
        this.jobId = jobId;
        action = Action.UNKNOWN;
    }

    @Override
    public long getJobId() {
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
