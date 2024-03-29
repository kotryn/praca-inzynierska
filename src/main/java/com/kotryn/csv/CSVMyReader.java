package com.kotryn.csv;

import com.kotryn.entity.GrowthStockSector;
import com.kotryn.entity.Job;
import com.kotryn.entity.Sector;
import com.kotryn.entity.WorstCaseDistributionSector;
import com.kotryn.repository.GrowthStockSectorRepository;
import com.kotryn.repository.SectorRepository;
import com.kotryn.repository.WorstCaseDistributionSectorRepository;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVMyReader {

    private String csvFile;
    private Map<String, Sector> sectorsMap;
    private Map<String, WorstCaseDistributionSector> sectorsWorstCaseDistributionsMap;
    private Map<String, GrowthStockSector> sectorsGrowthStockMap;
    private Map<String, String> industriesStocks;
    private Job job;

    private SectorRepository sectorRepository;
    private WorstCaseDistributionSectorRepository worstCaseDistributionSectorRepository;
    private GrowthStockSectorRepository growthStockSectorRepository;

    public CSVMyReader(String csvFile){
        this.csvFile = csvFile;

    }

    public CSVMyReader(String csvFile, SectorRepository sectorRepository, Job job){
        this.csvFile = csvFile;
        this.sectorsMap = new HashMap<>();
        this.industriesStocks = new HashMap<>();
        this.sectorRepository = sectorRepository;
        this.job = job;
    }

    public CSVMyReader(String csvFile, WorstCaseDistributionSectorRepository worstCaseDistributionSectorRepository, Job job){
        this.csvFile = csvFile;
        this.sectorsWorstCaseDistributionsMap = new HashMap<>();
        this.industriesStocks = new HashMap<>();
        this.worstCaseDistributionSectorRepository = worstCaseDistributionSectorRepository;
        this.job = job;
    }

    public CSVMyReader(String csvFile, GrowthStockSectorRepository growthStockSectorRepository, Job job){
        this.csvFile = csvFile;
        this.sectorsGrowthStockMap = new HashMap<>();
        this.industriesStocks = new HashMap<>();
        this.growthStockSectorRepository = growthStockSectorRepository;
        this.job = job;
    }

    public Map<String, Sector> getStocksMap() {
        return sectorsMap;
    }

    public Map<String, WorstCaseDistributionSector> getSectorsWorstCaseDistributionsMap() {
        return sectorsWorstCaseDistributionsMap;
    }

    public Map<String, GrowthStockSector> getSectorsGrowthStockMap() {
        return sectorsGrowthStockMap;
    }

    public void csvFirstSetStocks (){
        CSVReader reader;
        sectorsMap.clear();
        industriesStocks.clear();

        String sectorRead = null;
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
                        industriesStocks.putAll(symbolIndustry);
                        symbolIndustry.clear();
                        sectorRead = line[0];
                    }
                }else{
                    symbolIndustry.put("["+line[0]+"] "+line[1], line[3]);
                }

            }
            Sector sector = new Sector(symbolIndustry, sectorRead, job);
            sector = sectorRepository.save(sector);
            sectorsMap.put(sectorRead, sector);
            industriesStocks.putAll(symbolIndustry);
            symbolIndustry.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  List<String> csvGetOneColumn (int column){
        CSVReader reader;
        List<String> symbol = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                symbol.add(line[column]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return symbol;
    }

    public void csvFirstSetStocks2 (){
        CSVReader reader;
        sectorsWorstCaseDistributionsMap.clear();
        industriesStocks.clear();

        String sectorRead = null;
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
                        industriesStocks.putAll(symbolIndustry);
                        symbolIndustry.clear();
                        sectorRead = line[0];
                    }
                }else{
                    symbolIndustry.put("["+line[0]+"] "+line[1], line[3]);
                }

            }
            WorstCaseDistributionSector sector = new WorstCaseDistributionSector(symbolIndustry, sectorRead, job);
            sector = worstCaseDistributionSectorRepository.save(sector);
            sectorsWorstCaseDistributionsMap.put(sectorRead, sector);
            industriesStocks.putAll(symbolIndustry);
            symbolIndustry.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void csvFirstSetStocks3 (){
        CSVReader reader;
        sectorsGrowthStockMap.clear();
        industriesStocks.clear();

        String sectorRead = null;
        Map<String, String> symbolIndustry = new HashMap<>();

        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                if(line[1].equals("#")){
                    if(sectorRead == null){
                        sectorRead = line[0];
                    }else{
                        GrowthStockSector sector = new GrowthStockSector(symbolIndustry, sectorRead, job);
                        sector = growthStockSectorRepository.save(sector);
                        sectorsGrowthStockMap.put(sectorRead, sector);
                        industriesStocks.putAll(symbolIndustry);
                        symbolIndustry.clear();
                        sectorRead = line[0];
                    }
                }else{
                    symbolIndustry.put("["+line[0]+"] "+line[1], line[3]);
                }

            }
            GrowthStockSector sector = new GrowthStockSector(symbolIndustry, sectorRead, job);
            sector = growthStockSectorRepository.save(sector);
            sectorsGrowthStockMap.put(sectorRead, sector);
            industriesStocks.putAll(symbolIndustry);
            symbolIndustry.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
