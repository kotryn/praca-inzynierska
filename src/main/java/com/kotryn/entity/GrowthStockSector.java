package com.kotryn.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class GrowthStockSector {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Job job;

    @Column(name="GROWTH_STOCK_SECTOR")
    private String growthStockSector;

    @ElementCollection
    private Map<String,String> industriesStocks = new HashMap<>();


    public GrowthStockSector(){

    }

    public GrowthStockSector(Map<String, String> industriesStocks, String growthStockSector, Job job){
        this.industriesStocks.putAll(industriesStocks);
        this.growthStockSector = growthStockSector;
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

    public String getGrowthStockSector() {
        return growthStockSector;
    }

    public void setGrowthStockSector(String growthStockSector) {
        this.growthStockSector = growthStockSector;
    }

    public Map<String, String> getIndustriesStocks() {
        return industriesStocks;
    }

    public void setIndustriesStocks(Map<String, String> industriesStocks) {
        this.industriesStocks = industriesStocks;
    }
}
