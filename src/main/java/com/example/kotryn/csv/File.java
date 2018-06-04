package com.example.kotryn.csv;

public class File  {

    public static String getFile(String file) {
        switch(file) {
            case "FIRST_STOCK":
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/mock/dane2.csv";
            case "CALCULATING_SAMPLE_COUNT":
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/mock/calculatingSampleCount.csv";
            case "ESTIMATING_WORST_CASE_DISTRIBUTION":
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/mock/estimatingWorstCaseDistributions.csv";
            case "ESTIMATING_GROWTH_STOCKS":
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/mock/estimatingGrowthStocks.csv";
            case "ESTIMATING_WORST_CASE_COPULA":
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/mock/estimatingWorstCaseCopula.csv";
            case "BUILDING_ROBUST_PORTFOLIO":
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/mock/buildingRobustPortfolio.csv";
            case "CALCULATING_STATISTIC":
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/mock/calculatingStatistic.csv";
            default:
                throw new RuntimeException("Unknown file");
        }
    }
}