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
    private Integer windowSize;
    private Integer growthRate;
    private String inSample;
    private String outOfSample;
    private Integer maxNumberSector;
    private Integer maxNumberIndustry;
    private Double maxCoefficient;

    @ElementCollection
    private List<String> availableStocks;
    @ElementCollection
    private List<String> selectedStocks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="job")
    @MapKey(name="worstCaseDistributionSector")
    private Map<String, WorstCaseDistributionSector> worstCaseDistributionStocks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="job")
    @MapKey(name="sector")
    private Map<String, Sector> stocks;

    @ElementCollection
    private List<String> availableWorstCaseDistributionsStocks;

    @ElementCollection
    private List<String> growthStocks;
    @ElementCollection
    private List<String> selectedGrowthStocks;

    @ElementCollection
    private List<String> nonCorrelatedStocks;
    @ElementCollection
    private List<String> selectedNonCorrelatedStocks;

    @ElementCollection
    private List<String> worstCaseCopula;
    @ElementCollection
    private List<String> selectedWorstCaseCopula;

    @ElementCollection
    private List<String> robustPortfolio;
    @ElementCollection
    private List<String> selectedRobustPortfolio;

    @ElementCollection
    private List<String> statistic;


    public Job(){
        this.startDate = "not set";
        this.endDate = "not set";
        this.availableStocks = null;
        this.selectedStocks = null;
        this.stocks = null;
        this.windowSize = null;
        this.growthRate = null;
        this.growthStocks = null;
        this.selectedGrowthStocks = null;
        this.nonCorrelatedStocks = null;
        this.selectedNonCorrelatedStocks = null;
        this.worstCaseCopula = null;
        this.selectedWorstCaseCopula = null;
        this.robustPortfolio = null;
        this.selectedRobustPortfolio = null;
        this.statistic = null;
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

    public List<String> getAvailableStocks() {
        return availableStocks;
    }

    public void setAvailableStocks(List<String> availableStocks) {
        this.availableStocks = availableStocks;
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

    public List<String> getAvailableWorstCaseDistributionsStocks() {
        return availableWorstCaseDistributionsStocks;
    }

    public void setAvailableWorstCaseDistributionsStocks(List<String> availableWorstCaseDistributionsStocks) {
        this.availableWorstCaseDistributionsStocks = availableWorstCaseDistributionsStocks;
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

    public List<String> getGrowthStocks() {
        return growthStocks;
    }

    public void setGrowthStocks(List<String> growthStocks) {
        this.growthStocks = growthStocks;
    }

    public List<String> getSelectedGrowthStocks() {
        return selectedGrowthStocks;
    }

    public void setSelectedGrowthStocks(List<String> selectedGrowthStocks) {
        this.selectedGrowthStocks = selectedGrowthStocks;
    }

    public List<String> getNonCorrelatedStocks() {
        return nonCorrelatedStocks;
    }

    public void setNonCorrelatedStocks(List<String> nonCorrelatedStocks) {
        this.nonCorrelatedStocks = nonCorrelatedStocks;
    }

    public List<String> getSelectedNonCorrelatedStocks() {
        return selectedNonCorrelatedStocks;
    }

    public void setSelectedNonCorrelatedStocks(List<String> selectedNonCorrelatedStocks) {
        this.selectedNonCorrelatedStocks = selectedNonCorrelatedStocks;
    }

    public List<String> getWorstCaseCopula() {
        return worstCaseCopula;
    }

    public void setWorstCaseCopula(List<String> worstCaseCopula) {
        this.worstCaseCopula = worstCaseCopula;
    }

    public List<String> getSelectedWorstCaseCopula() {
        return selectedWorstCaseCopula;
    }

    public void setSelectedWorstCaseCopula(List<String> selectedWorstCaseCopula) {
        this.selectedWorstCaseCopula = selectedWorstCaseCopula;
    }

    public List<String> getRobustPortfolio() {
        return robustPortfolio;
    }

    public void setRobustPortfolio(List<String> robustPortfolio) {
        this.robustPortfolio = robustPortfolio;
    }

    public List<String> getSelectedRobustPortfolio() {
        return selectedRobustPortfolio;
    }

    public void setSelectedRobustPortfolio(List<String> selectedRobustPortfolio) {
        this.selectedRobustPortfolio = selectedRobustPortfolio;
    }

    public List<String> getStatistic() {
        return statistic;
    }

    public void setStatistic(List<String> statistic) {
        this.statistic = statistic;
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

    public static <K, V> List<K> getAllKeysForValue(Map<K, V> mapOfWords, V value)
    {
        List<K> listOfKeys = null;

        //Check if Map contains the given value
        if(mapOfWords.containsValue(value))
        {
            // Create an Empty List
            listOfKeys = new ArrayList<>();

            // Iterate over each entry of map using entrySet
            for (Map.Entry<K, V> entry : mapOfWords.entrySet())
            {
                // Check if value matches with given value
                if (entry.getValue().equals(value))
                {
                    // Store the key from entry to the list
                    listOfKeys.add(entry.getKey());
                }
            }
        }
        // Return the list of keys whose value matches with given value.
        return listOfKeys;
    }
}