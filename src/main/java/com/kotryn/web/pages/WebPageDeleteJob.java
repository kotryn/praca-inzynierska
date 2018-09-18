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

        header.add(new Entity<>(new Button("button-home", "http://kotryn.localtunnel.me/start_page", "Start page")));

        body.add(new Entity<>(new Title("title", "h3", "Delete job")));
        if(error != null){
            body.add(new Entity<>(new Text("text", error)));
        }
        body.add(new Entity<>(new Input("input", "integer", new String[]{"id"}, new String[]{"Supply job ID:"})));
        body.add(new Entity<>(new Button("button-back", "http://kotryn.localtunnel.me/start_page", "Back")));
        body.add(new Entity<>(new Button("button-delete", "http://kotryn.localtunnel.me/delete_job", "Delete")));

        return new Page(new Header(header), new Body(body));
    }
}
