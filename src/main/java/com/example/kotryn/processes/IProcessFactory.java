package com.example.kotryn.processes;

public interface IProcessFactory {
    IProcess getProcess(Long jobId);
}
