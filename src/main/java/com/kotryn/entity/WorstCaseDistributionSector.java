package com.kotryn.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class WorstCaseDistributionSector {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Job job;

    @Column(name="WORST_CASE_DISTRIBUTION_STOCK_SECTOR")
    private String worstCaseDistributionSector;

    @ElementCollection
    private Map<String,String> industriesStocks = new HashMap<>();


    public WorstCaseDistributionSector(){

    }

    public WorstCaseDistributionSector(Map<String, String> industriesStocks, String worstCaseDistributionSector, Job job){
        this.industriesStocks.putAll(industriesStocks);
        this.worstCaseDistributionSector = worstCaseDistributionSector;
        this.job = job;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getWorstCaseDistributionSector() {
        return worstCaseDistributionSector;
    }

    public void setWorstCaseDistributionSector(String worstCaseDistributionSector) {
        this.worstCaseDistributionSector = worstCaseDistributionSector;
    }

    public Map<String, String> getIndustriesStocks() {
        return industriesStocks;
    }

    public void setIndustriesStocks(Map<String, String> industriesStocks) {
        this.industriesStocks = industriesStocks;
    }
}