package com.kotryn.processes;

public interface IProcessFactory {
    IProcess getProcess(Long jobId);
}
