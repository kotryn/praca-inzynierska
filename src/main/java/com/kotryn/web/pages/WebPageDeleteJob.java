package com.kotryn.web.pages;

import com.kotryn.json.*;

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

        header.add(new Entity<>(new Button("button-home", "http://localhost:8080/start_page", "Start page")));

        body.add(new Entity<>(new Title("title", "h3", "Delete job")));
        if(error != null){
            body.add(new Entity<>(new Text("text", error)));
        }
        body.add(new Entity<>(new Input("input", "integer", new String[]{"id"}, new String[]{"Supply job ID:"})));
        body.add(new Entity<>(new Button("button-back", "http://localhost:8080/start_page", "Back")));
        body.add(new Entity<>(new Button("button-delete", "http://localhost:8080/delete_job", "Delete")));

        return new Page(new Header(header), new Body(body));
    }
}
