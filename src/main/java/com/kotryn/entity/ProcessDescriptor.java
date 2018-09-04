package com.kotryn.entity;

import com.kotryn.processes.ProcessState;
import com.kotryn.processes.ProcessType;
import com.kotryn.processes.SystemType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Duration;

@Entity
public class ProcessDescriptor {
    @Id
    @GeneratedValue
    private Long id;

    private final Long jobId;
    private ProcessType processType;
    private String host;
    private SystemType systemType;
    private ProcessState state;
    private int pid;
    private Duration duration;
    private String errorMessage;

    public ProcessDescriptor() {
        this.jobId = 0L;
        processType = ProcessType.UNKNOWN;
        host = "unknown";
        systemType = SystemType.UNKNOWN;
        state = ProcessState.UNKNOWN;
        pid = -1;
        duration = Duration.ofSeconds(0);
        errorMessage = "unset";
    }

    public ProcessDescriptor(Long jobId) {
        this.jobId = jobId;
        processType = ProcessType.UNKNOWN;
        host = "unknown";
        systemType = SystemType.UNKNOWN;
        state = ProcessState.UNKNOWN;
        pid = -1;
        duration = Duration.ofSeconds(0);
        errorMessage = "unset";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public SystemType getSystemType() {
        return systemType;
    }

    public void setSystemType(SystemType systemType) {
        this.systemType = systemType;
    }

    public ProcessState getProcessState() {
        return state;
    }

    public void setProcessState(ProcessState state) {
        this.state = state;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
