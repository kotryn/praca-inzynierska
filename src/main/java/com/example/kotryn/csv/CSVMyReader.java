package com.example.kotryn.csv;

import com.example.kotryn.entity.Job;
import com.example.kotryn.entity.Sector;
import com.example.kotryn.entity.WorstCaseDistributionSector;
import com.example.kotryn.repository.SectorRepository;
import com.example.kotryn.repository.WorstCaseDistributionSectorRepository;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVMyReader {

    private String csvFile;
    private Map<String, Sector> sectorsMap;
    private Map<String, WorstCaseDistributionSector> sectorsWorstCaseDistributionsMap;
    private List<String> symbols;
    private Map<String, String> industriesStocks;
    private Job job;

    private SectorRepository sectorRepository;
    private WorstCaseDistributionSectorRepository worstCaseDistributionSectorRepository;

    public CSVMyReader(String csvFile){
        this.csvFile = csvFile;

    }

    public CSVMyReader(String csvFile, SectorRepository sectorRepository, Job job){
        this.csvFile = csvFile;
        this.sectorsMap = new HashMap<>();
        this.symbols = new ArrayList<>();
        this.industriesStocks = new HashMap<>();
        this.sectorRepository = sectorRepository;
        this.job = job;

    }

    public CSVMyReader(String csvFile, WorstCaseDistributionSectorRepository worstCaseDistributionSectorRepository, Job job){
        this.csvFile = csvFile;
        this.sectorsWorstCaseDistributionsMap = new HashMap<>();
        this.symbols = new ArrayList<>();
        this.industriesStocks = new HashMap<>();
        this.worstCaseDistributionSectorRepository = worstCaseDistributionSectorRepository;
        this.job = job;

    }

    public List<String> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<String> symbols) {
        this.symbols = symbols;
    }

    public Map<String, Sector> getStocksMap() {
        return sectorsMap;
    }

    public Map<String, WorstCaseDistributionSector> getSectorsWorstCaseDistributionsMap() {
        return sectorsWorstCaseDistributionsMap;
    }

    public void csvFirstSetStocks (){
        CSVReader reader;
        sectorsMap.clear();
        symbols.clear();
        industriesStocks.clear();

        String sectorRead = null;
        List<String> symbol = new ArrayList<>();
        Map<String, String> symbolIndustry = new HashMap<>();

        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                if(line[1].equals("#")){
                    if(sectorRead == null){
                        sectorRead = line[0];
                    }else{
                        Sector sector = new Sector(symbolIndustry, sectorRead, job);
                        sector = sectorRepository.save(sector);
                        sectorsMap.put(sectorRead, sector);
                        symbols.addAll(symbol);
                        symbol.clear();
                        industriesStocks.putAll(symbolIndustry);
                        symbolIndustry.clear();
                        sectorRead = line[0];
                    }
                }else{
                    symbol.add("["+line[0]+"] "+line[1]);
                    symbolIndustry.put("["+line[0]+"] "+line[1], line[3]);
                }

            }
            Sector sector = new Sector(symbolIndustry, sectorRead, job);
            sector = sectorRepository.save(sector);
            sectorsMap.put(sectorRead, sector);
            symbols.addAll(symbol);
            symbol.clear();
            industriesStocks.putAll(symbolIndustry);
            symbolIndustry.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  List<String> csvGetOneColumn (){
        CSVReader reader;
        List<String> symbol = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                symbol.add(line[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return symbol;
    }

    public void csvFirstSetStocks2 (){
        CSVReader reader;
        sectorsWorstCaseDistributionsMap.clear();
        symbols.clear();
        industriesStocks.clear();

        String sectorRead = null;
        List<String> symbol = new ArrayList<>();
        Map<String, String> symbolIndustry = new HashMap<>();

        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                if(line[1].equals("#")){
                    if(sectorRead == null){
                        sectorRead = line[0];
                    }else{
                        WorstCaseDistributionSector sector = new WorstCaseDistributionSector(symbolIndustry, sectorRead, job);
                        sector = worstCaseDistributionSectorRepository.save(sector);
                        sectorsWorstCaseDistributionsMap.put(sectorRead, sector);
                        symbols.addAll(symbol);
                        symbol.clear();
                        industriesStocks.putAll(symbolIndustry);
                        symbolIndustry.clear();
                        sectorRead = line[0];
                    }
                }else{
                    symbol.add("["+line[0]+"] "+line[1]);
                    symbolIndustry.put("["+line[0]+"] "+line[1], line[3]);
                }

            }
            WorstCaseDistributionSector sector = new WorstCaseDistributionSector(symbolIndustry, sectorRead, job);
            sector = worstCaseDistributionSectorRepository.save(sector);
            sectorsWorstCaseDistributionsMap.put(sectorRead, sector);
            symbols.addAll(symbol);
            symbol.clear();
            industriesStocks.putAll(symbolIndustry);
            symbolIndustry.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
