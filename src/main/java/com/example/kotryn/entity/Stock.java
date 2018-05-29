package com.example.kotryn.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Stock {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Job job;

    @Column(name="STOCK_SECTOR")
    private String sector;

    @ElementCollection
    private List<String> symbols;
    @ElementCollection
    private List<String> companies;

    public Stock(){

    }

    public Stock(List<String> symbols, List<String> companies, String sector, Job job){
        this.symbols = symbols;
        this.companies = companies;
        this.sector = sector;
        this.job = job;
    }

    public List<String> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<String> stocks) {
        this.symbols = stocks;
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

    public List<String> getCompanies() {
        return companies;
    }

    public void setCompanies(List<String> companies) {
        this.companies = companies;
    }
}
