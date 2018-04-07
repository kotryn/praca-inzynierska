package com.example.kotryn.entity.Job;

import com.example.kotryn.json.*;

public class NewJob implements JobState {
    private Job job;

    public NewJob(){
    }

    public NewJob(Job job){
        this.job=job;
    }

    public Page getJobId(){
        return null;
    }

    public Page getConnectPage(Long id) {
        System.out.println("PAGE NEW : "+id);

        Text text = new Text("text", "New job id: "+ id);
        Button btnConnect = new Button("button", "/connectJob/"+id, "connect");
        Button btnBack = new Button("button-back", "/", "back");
        Item<Text> item = new Item<>(text);
        Item<Button> item2 = new Item<>(btnConnect);
        Item<Button> item3 = new Item<>(btnBack);
        Body body = new Body(item, item2, item3);

        return new Page(id, body);
    }

}
