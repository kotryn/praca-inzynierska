package com.example.kotryn.csv;

import com.example.kotryn.entity.Job;
import com.example.kotryn.entity.Stock;
import com.example.kotryn.repository.StockRepository;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVMyReader {

    private String csvFile;
    private Map<String, Stock> stocksMap;
    private List<String> symbols;
    private Job job;

    private StockRepository stockRepository;

    public CSVMyReader(String csvFile, StockRepository stockRepository, Job job){
        this.csvFile = csvFile;
        this.stocksMap = new HashMap<>();
        this.symbols = new ArrayList<>();
        this.stockRepository = stockRepository;
        this.job = job;

    }

    public List<String> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<String> symbols) {
        this.symbols = symbols;
    }

    public Map<String, Stock> getStocksMap() {
        return stocksMap;
    }

    public void setStocksMap(Map<String, Stock> stocksMap) {
        this.stocksMap = stocksMap;
    }

    public void csvFirstSetStocks (){
        CSVReader reader;
        stocksMap.clear();
        symbols.clear();

        String sector = null;
        List<String> symbol = new ArrayList<>();

        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                if(line[1].equals("#")){
                    if(sector == null){
                        sector = line[0];
                    }else{
                        Stock stock = new Stock(symbol, sector, job);
                        stock = stockRepository.save(stock);
                        stocksMap.put(sector, stock);
                        symbols.addAll(symbol);
                        symbol.clear();
                        sector = line[0];
                    }
                }else{
                    symbol.add(line[0]);
                }

            }
            Stock stock = new Stock(symbol, sector, job);
            stock = stockRepository.save(stock);
            stocksMap.put(sector, stock);
            symbols.addAll(symbol);
            symbol.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
