package com.example.kotryn.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
public class Job {

    @Id
    @GeneratedValue
    private Long id;
    private String startDate;
    private String endDate;

    @ElementCollection
    private List<String> availableStocks;
    @ElementCollection
    private List<String> selectedStocks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="job")
    @MapKey(name="sector")
    private Map<String, Stock> stocks;

    @ElementCollection
    private List<String> calculatingSample;
    @ElementCollection
    private List<String> selectedCalculatingSample;

    @ElementCollection
    private List<String> worstCaseDistributions;
    @ElementCollection
    private List<String> selectedWorstCaseDistributions;

    @ElementCollection
    private List<String> growthStocks;
    @ElementCollection
    private List<String> selectedGrowthStocks;


    public Job(){
        this.startDate = "not set";
        this.endDate = "not set";
        this.availableStocks = null;
        this.selectedStocks = null;
        this.stocks = null;
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

    public Map<String, Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Map<String, Stock> stocks) {
        this.stocks = stocks;
    }

    /**/
    public List<String> getCalculatingSample() {
        return calculatingSample;
    }

    public void setCalculatingSample(List<String> calculatingSample) {
        this.calculatingSample = calculatingSample;
    }

    public List<String> getSelectedCalculatingSample() {
        return selectedCalculatingSample;
    }

    public void setSelectedCalculatingSample(List<String> selectedCalculatingSample) {
        this.selectedCalculatingSample = selectedCalculatingSample;
    }
    /**/
    public List<String> getWorstCaseDistributions() {
        return worstCaseDistributions;
    }

    public void setWorstCaseDistributions(List<String> worstCaseDistributions) {
        this.worstCaseDistributions = worstCaseDistributions;
    }

    public List<String> getSelectedWorstCaseDistributions() {
        return selectedWorstCaseDistributions;
    }

    public void setSelectedWorstCaseDistributions(List<String> selectedWorstCaseDistributions) {
        this.selectedWorstCaseDistributions = selectedWorstCaseDistributions;
    }
    /**/

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
    /**/
}