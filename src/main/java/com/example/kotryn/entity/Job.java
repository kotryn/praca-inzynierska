package com.example.kotryn.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Job {

    @Id
    @GeneratedValue
    private Long id;
    private String startDate;
    private String endDate;
    private String startInSampleDate;
    private String endInSampleDate;
    private String startOutOfSampleDate;
    private String endOutOfSampleDate;
    private String periodicity;
    private Integer windowSize;
    private Integer growthRate;
    private String inSample;
    private String outOfSample;
    private Integer maxNumberSector;
    private Integer maxNumberIndustry;
    private Double maxCoefficient;
    private Integer copulaWindowSize;
    private String copulaType;
    private Double correlationMatrix;
    private Integer numberOfSamples;
    private Double yearRateOfReturn;
    private Double toleranceLevel;
    private Double maxShare;
    private Double theta;

    @ElementCollection
    private List<String> selectedStocks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="job")
    @MapKey(name="sector")
    private Map<String, Sector> stocks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="job")
    @MapKey(name="worstCaseDistributionSector")
    private Map<String, WorstCaseDistributionSector> worstCaseDistributionStocks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="job")
    @MapKey(name="growthStockSector")
    private Map<String, GrowthStockSector> growthStock;

    @ElementCollection
    private List<String> portfolioCompany;
    @ElementCollection
    private List<String> portfolioShare;

    @ElementCollection
    private List<String> x;
    @ElementCollection
    private List<Double> y;


    public Job(){
        this.startDate = "not set";
        this.endDate = "not set";
        this.startInSampleDate = "not set";
        this.endInSampleDate = "not set";
        this.startOutOfSampleDate = "not set";
        this.endOutOfSampleDate = "not set";
        this.periodicity = "not set";
        this.windowSize = null; 
        this.growthRate = null;
        this.inSample = "not set";
        this.outOfSample = "not set";
        this.maxNumberSector = null; 
        this.maxNumberIndustry = null; 
        this.maxCoefficient = null;
        this.copulaWindowSize = null;
        this.copulaType = "not set";
        this.correlationMatrix = null; 
        this.numberOfSamples = null; 
        this.yearRateOfReturn = null; 
        this.toleranceLevel = null; 
        this.maxShare = null; 
        this.theta = null;

        this.selectedStocks = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getWindowSize() {
        return windowSize;
    }

    public void setWindowSize(Integer windowSize) {
        this.windowSize = windowSize;
    }

    public Integer getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(Integer growthRate) {
        this.growthRate = growthRate;
    }

    public List<String> getSelectedStocks() {
        return selectedStocks;
    }

    public void setSelectedStocks(List<String> selectedStocks) {
        this.selectedStocks = selectedStocks;
    }

    public Map<String, Sector> getStocks() {
        return stocks;
    }

    public void setStocks(Map<String, Sector> stocks) {
        this.stocks = stocks;
    }

    public Map<String, WorstCaseDistributionSector> getWorstCaseDistributionStocks() {
        return worstCaseDistributionStocks;
    }

    public void setWorstCaseDistributionStocks(Map<String, WorstCaseDistributionSector> worstCaseDistributionStocks) {
        this.worstCaseDistributionStocks = worstCaseDistributionStocks;
    }

    public Map<String, GrowthStockSector> getGrowthStock() {
        return growthStock;
    }

    public void setGrowthStock(Map<String, GrowthStockSector> growthStock) {
        this.growthStock = growthStock;
    }

    public String getStartInSampleDate() {
        return startInSampleDate;
    }

    public void setStartInSampleDate(String startInSampleDate) {
        this.startInSampleDate = startInSampleDate;
    }

    public String getEndInSampleDate() {
        return endInSampleDate;
    }

    public void setEndInSampleDate(String endInSampleDate) {
        this.endInSampleDate = endInSampleDate;
    }

    public String getStartOutOfSampleDate() {
        return startOutOfSampleDate;
    }

    public void setStartOutOfSampleDate(String startOutOfSampleDate) {
        this.startOutOfSampleDate = startOutOfSampleDate;
    }

    public String getEndOutOfSampleDate() {
        return endOutOfSampleDate;
    }

    public void setEndOutOfSampleDate(String endOutOfSampleDate) {
        this.endOutOfSampleDate = endOutOfSampleDate;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public String getInSample() {
        return inSample;
    }

    public void setInSample(String inSample) {
        this.inSample = inSample;
    }

    public String getOutOfSample() {
        return outOfSample;
    }

    public void setOutOfSample(String outOfSample) {
        this.outOfSample = outOfSample;
    }

    public Integer getMaxNumberSector() {
        return maxNumberSector;
    }

    public void setMaxNumberSector(Integer maxNumberSector) {
        this.maxNumberSector = maxNumberSector;
    }

    public Integer getMaxNumberIndustry() {
        return maxNumberIndustry;
    }

    public void setMaxNumberIndustry(Integer maxNumberIndustry) {
        this.maxNumberIndustry = maxNumberIndustry;
    }

    public Double getMaxCoefficient() {
        return maxCoefficient;
    }

    public void setMaxCoefficient(Double maxCoefficient) {
        this.maxCoefficient = maxCoefficient;
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

    public Double getCorrelationMatrix() {
        return correlationMatrix;
    }

    public void setCorrelationMatrix(Double correlationMatrix) {
        this.correlationMatrix = correlationMatrix;
    }

    public Integer getNumberOfSamples() {
        return numberOfSamples;
    }

    public void setNumberOfSamples(Integer numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }

    public Double getYearRateOfReturn() {
        return yearRateOfReturn;
    }

    public void setYearRateOfReturn(Double yearRateOfReturn) {
        this.yearRateOfReturn = yearRateOfReturn;
    }

    public Double getToleranceLevel() {
        return toleranceLevel;
    }

    public void setToleranceLevel(Double toleranceLevel) {
        this.toleranceLevel = toleranceLevel;
    }

    public Double getMaxShare() {
        return maxShare;
    }

    public void setMaxShare(Double maxShare) {
        this.maxShare = maxShare;
    }

    public Double getTheta() {
        return theta;
    }

    public void setTheta(Double theta) {
        this.theta = theta;
    }

    public List<String> getPortfolioCompany() {
        return portfolioCompany;
    }

    public void setPortfolioCompany(List<String> portfolioCompany) {
        this.portfolioCompany = portfolioCompany;
    }

    public List<String> getPortfolioShare() {
        return portfolioShare;
    }

    public void setPortfolioShare(List<String> portfolioShare) {
        this.portfolioShare = portfolioShare;
    }

    public List<String> getX() {
        return x;
    }

    public void setX(List<String> x) {
        this.x = x;
    }

    public List<Double> getY() {
        return y;
    }

    public void setY(List<Double> y) {
        this.y = y;
    }

    public static <K, V> List<K> getAllKeysForValue(Map<K, V> mapOfWords, V value) {
        List<K> listOfKeys = null;
        if(mapOfWords.containsValue(value)) {
            listOfKeys = new ArrayList<>();
            for (Map.Entry<K, V> entry : mapOfWords.entrySet()) {
                if (entry.getValue().equals(value)) {
                    listOfKeys.add(entry.getKey());
                }
            }
        }
        return listOfKeys;
    }
}