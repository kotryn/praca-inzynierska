package com.example.kotryn.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Sector {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Job job;

    @Column(name="STOCK_SECTOR")
    private String sector;

    @ElementCollection
    private Map<String,String> industriesStocks = new HashMap<> ();


    public Sector(){

    }

    public Sector(Map<String, String> industriesStocks, String sector, Job job){
        this.industriesStocks.putAll(industriesStocks);
        this.sector = sector;
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

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Map<String, String> getIndustriesStocks() {
        return industriesStocks;
    }

    public void setIndustriesStocks(Map<String, String> industriesStocks) {
        this.industriesStocks = industriesStocks;
    }
}
