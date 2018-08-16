package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

import java.util.ArrayList;
import java.util.List;

public class WebPageSearchingForStocksInProgress {

    private final Long jobId;

    public WebPageSearchingForStocksInProgress(Long jobId) {
        this.jobId = jobId;
    }

    public Page show() {
        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://192.168.31.106:8081//start_page", "Start page")));
        header.add(new Entity<>(new Text("text-header", "Job ID: "+jobId)));

        body.add(new Entity<>(new Title("title", "h3","Searching for stocks in progress")));
        body.add(new Entity<>(new Button("button-back", "http://192.168.31.106:8081//stocks_search_in_progress_back/"+jobId, "Back")));
        body.add(new Entity<>(new Button("button", "http://192.168.31.106:8081//stocks_search_in_progress/"+jobId, "Refresh")));

        return new Page(new Header(header), new Body(body));
    }
}
