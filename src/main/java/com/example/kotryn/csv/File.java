package com.example.kotryn.csv;

public class File  {

    public static String getFile(String file) {
        switch(file) {
            case "FIRST_STOCK":
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/mock/dane2.csv";
            case "CALCULATING_SAMPLE_COUNT":
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/mock/calculatingSampleCount.csv";
            case "ESTIMATING_WORST_CASE":
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/mock/dane4.csv";
            default:
                throw new RuntimeException("Unknown file");
        }
    }
}