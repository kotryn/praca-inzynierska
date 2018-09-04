package com.example.kotryn.csv;

public class FileFactory  {

    public static String getFile(File file) {
        switch(file) {
            case FIRST_STOCK:
                return "../praca-inzynierska/src/main/resources/static/mock/dane2.csv";
            case CALCULATING_SAMPLE_COUNT:
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/mock/calculatingSampleCount.csv";
            case ESTIMATING_WORST_CASE_DISTRIBUTION:
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/mock/estimatingWorstCaseDistributions.csv";
            case ESTIMATING_GROWTH_STOCKS:
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/mock/estimatingGrowthStocks.csv";
            case ESTIMATING_WORST_CASE_T_COPULA:
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/mock/estimatingWorstCaseTCopula.csv";
            case ESTIMATING_WORST_CASE_CLAYTON_COPULA:
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/mock/estimatingWorstCaseClaytonCopula.csv";
            case BUILDING_ROBUST_PORTFOLIO:
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/mock/buildingRobustPortfolio.csv";
            case CALCULATING_STATISTIC:
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/mock/calculatingStatistic.csv";
            default:
                throw new RuntimeException("Unknown file");
        }
    }
}
