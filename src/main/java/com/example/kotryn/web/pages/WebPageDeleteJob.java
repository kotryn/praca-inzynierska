package com.example.kotryn.web.pages;

import com.example.kotryn.json.*;

import java.util.ArrayList;
import java.util.List;

public class WebPageDeleteJob {

    private String error;

    public WebPageDeleteJob(String error) {
        this.error = error;
    }

    public Page show() {
        List<Entity> body = new ArrayList<>();
        List<Entity> header = new ArrayList<>();

        header.add(new Entity<>(new Button("button-home", "http://192.168.31.106:8081//start_page", "Start page")));

        body.add(new Entity<>(new Title("title", "h3", "Delete job")));
        if(error != null){
            body.add(new Entity<>(new Text("text", error)));
        }
        body.add(new Entity<>(new Input("input", "integer", new String[]{"id"}, new String[]{"Supply job ID:"})));
        body.add(new Entity<>(new Button("button-back", "http://192.168.31.106:8081//start_page", "Back")));
        body.add(new Entity<>(new Button("button-delete", "http://192.168.31.106:8081//delete_job", "Delete")));

        return new Page(new Header(header), new Body(body));
    }
}
