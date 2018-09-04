package com.kotryn.processes;

public class AbstractProcessFactory {

    private static IProcessFactory processFactory = null;

    public static void setFactory(IProcessFactory processFactory) {
        AbstractProcessFactory.processFactory = processFactory;
    }

    public static IProcessFactory getFactory() {
        return processFactory;
    }
}
