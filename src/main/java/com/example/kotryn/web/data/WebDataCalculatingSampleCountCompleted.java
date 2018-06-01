package com.example.kotryn.web.data;

import java.util.List;

public class WebDataCalculatingSampleCountCompleted implements IWebData {

    private final Long jobId;
    private Action action;
    private List<String> selectedCalculatingSample;
    /*private String startInSampleDate;
    private String endInSampleDate;
    private String startOutOfSampleDate;
    private String endOutOfSampleDate;*/

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

    /*public String getStartInSampleDate() {
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
    }*/
}
