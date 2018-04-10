package com.example.kotryn.entity.Job;

import javax.persistence.*;
import java.util.List;

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

    public Job(){
        this.startDate = "not set";
        this.endDate = "not set";
        this.availableStocks = null;
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
}