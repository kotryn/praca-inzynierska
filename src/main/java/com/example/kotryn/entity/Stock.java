package com.example.kotryn.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Stock {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Job job;

    @Column(name="STOCK_SECTOR")
    private String sector;

    //@ManyToMany
    @ElementCollection
    private List<String> stocks;

    public Stock(){

    }

    public Stock(List<String> stocks, String sector, Job job){
        this.stocks = stocks;
        this.sector = sector;
        this.job = job;
    }

    public List<String> getStocks() {
        return stocks;
    }

    public void setStocks(List<String> stocks) {
        this.stocks = stocks;
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
}
