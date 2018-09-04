package com.example.kotryn.csv;

public class FileFactory  {

    private static final String PATH = "/home/kotryn/IdeaProjects/praca-inzynierska";

    public static String getFile(File file) {
        switch(file) {
            case FIRST_STOCK:
                return PATH+"/src/main/resources/static/mock/dane2.csv";
            case CALCULATING_SAMPLE_COUNT:
                return PATH+"/src/main/resources/static/mock/calculatingSampleCount.csv";
            case ESTIMATING_WORST_CASE_DISTRIBUTION:
                return PATH+"/src/main/resources/static/mock/estimatingWorstCaseDistributions.csv";
            case ESTIMATING_GROWTH_STOCKS:
                return PATH+"/src/main/resources/static/mock/estimatingGrowthStocks.csv";
            case ESTIMATING_WORST_CASE_T_COPULA:
                return PATH+"/src/main/resources/static/mock/estimatingWorstCaseTCopula.csv";
            case ESTIMATING_WORST_CASE_CLAYTON_COPULA:
                return PATH+"/src/main/resources/static/mock/estimatingWorstCaseClaytonCopula.csv";
            case BUILDING_ROBUST_PORTFOLIO:
                return PATH+"/src/main/resources/static/mock/buildingRobustPortfolio.csv";
            case CALCULATING_STATISTIC:
                return PATH+"/src/main/resources/static/mock/calculatingStatistic.csv";
            default:
                throw new RuntimeException("Unknown file");
        }
    }
}
