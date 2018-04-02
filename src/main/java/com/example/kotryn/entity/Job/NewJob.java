package com.example.kotryn.entity.Job;

import com.example.kotryn.json.*;
import com.example.kotryn.states.JobState;

import java.util.ArrayList;
import java.util.List;

public class NewJob implements JobState {
    private Job job;

    private List<Page> pages = new ArrayList<>();

    public NewJob(){
    }

    public NewJob(Job job){
        this.job=job;
    }

    public Page getConnectPage(Long id) {
        Text text = new Text("text", "New job id: "+ id);
        Button btnConnect = new Button("button", "/connectNewJob/"+id, "connect");
        Button btnBack = new Button("button-back", "/", "back");
        Item<Text> item = new Item<>(text);
        Item<Button> item2 = new Item<>(btnConnect);
        Item<Button> item3 = new Item<>(btnBack);
        Body body = new Body(item, item2, item3);
        Page page = new Page(id, body);
        this.pages.add(page);
        return this.pages.get(0);
    }

    public Page getSupplyPeriodPage(Job job) {
        Text text = new Text("text", "Supply the period of analysis");

        Text text1 = new Text("text", "Previous start date: " + job.getStartDate());
        Text text2 = new Text("text", "Previous end date: " + job.getEndDate());

        Button btnForm = new Button("button", "/job", "submit");
        Input inputForm = new Input(new String[]{"startDate", "endDate"}, new String[]{"Enter Start Date:", "Enter end date:"});
        Form form = new Form("form", inputForm, btnForm);

        Item<Text> item = new Item<>(text);
        Item<Text> item2 = new Item<>(text1);
        Item<Text> item3 = new Item<>(text2);
        Item<Form> item4 = new Item<>(form);

        Body body = new Body(item, item2, item3, item4);
        Page page = new Page(job.getId(), body);
        this.pages.add(page);
        return this.pages.get(0);
    }

    public String connect(Job job) {
        return "/jobConnectPage/"+job.getId();
    }

}
