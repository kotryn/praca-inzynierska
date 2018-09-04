package com.kotryn.web.data;

public class WebDataEstimatingWorstCaseCopulaSetup implements IWebData {

    private final Long jobId;
    private Action action;
    private Integer copulaWindowSize;
    private String copulaType;


    public WebDataEstimatingWorstCaseCopulaSetup(Long jobId) {
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

    public Integer getCopulaWindowSize() {
        return copulaWindowSize;
    }

    public void setCopulaWindowSize(Integer copulaWindowSize) {
        this.copulaWindowSize = copulaWindowSize;
    }

    public String getCopulaType() {
        return copulaType;
    }

    public void setCopulaType(String copulaType) {
        this.copulaType = copulaType;
    }
}