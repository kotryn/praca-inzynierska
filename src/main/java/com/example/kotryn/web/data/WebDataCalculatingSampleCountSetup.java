package com.example.kotryn.web.data;

public class WebDataCalculatingSampleCountSetup implements IWebData {

    private final Long jobId;
    private Action action;
    private String startInSampleDate;
    private String endInSampleDate;
    private String startOutOfSampleDate;
    private String endOutOfSampleDate;

    public WebDataCalculatingSampleCountSetup(Long jobId) {
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

    public String getStartInSampleDate() {
        return startInSampleDate;
    }

    public void setStartInSampleDate(String startInSampleDate) {
        this.startInSampleDate = startInSampleDate;
    }

    public String getEndInSampleDate() {
        return endInSampleDate;
    }

    public void setEndInSampleDate(String endInSampleDate) {
        this.endInSampleDate = endInSampleDate;
    }

    public String getStartOutOfSampleDate() {
        return startOutOfSampleDate;
    }

    public void setStartOutOfSampleDate(String startOutOfSampleDate) {
        this.startOutOfSampleDate = startOutOfSampleDate;
    }

    public String getEndOutOfSampleDate() {
        return endOutOfSampleDate;
    }

    public void setEndOutOfSampleDate(String endOutOfSampleDate) {
        this.endOutOfSampleDate = endOutOfSampleDate;
    }
}