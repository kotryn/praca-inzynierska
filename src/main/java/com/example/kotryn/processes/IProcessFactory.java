package com.example.kotryn.processes;

public interface IProcessFactory {
    IProcess getProcess(long jobId);
}
