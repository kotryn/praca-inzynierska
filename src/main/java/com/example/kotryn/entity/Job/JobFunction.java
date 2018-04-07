package com.example.kotryn.entity.Job;

import com.example.kotryn.json.*;

import java.util.ArrayList;
import java.util.List;

public class JobFunction {

    private Job job;
    private JobState state;

    public JobFunction(){
        this.job = null;
        this.state = null;
    }

    public JobFunction(Job job){
        this.job = job;
        this.state = new NewJob();
    }

    public JobFunction(String state, Job job){
        this.job = job;
        this.setState(state);
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Job getJob() {
        return job;
    }

    public void setState(String state) {
        switch(state){
            case "new":
                this.state = new NewJob();
                break;
            case "old":
                this.state = new OldJob();
                break;
            default:
                this.state = null;
        }
    }

    public JobState getState() {
        return state;
    }

    /*public Page connect() {
        if (state != null) {
            return state.connect();
        } else {
            System.out.println("status unknown");
            return null;
        }
    }*/

    public Page getConnectPage(Job job){
        if (state != null) {
            return state.getConnectPage(job.getId());
        } else {
            return null;
        }
    }

    public Page getJobId(){
        if (state != null) {
            return state.getJobId();
        } else {
            return null;
        }
    }

    public Page getSupplyPeriodPage(Job job) {
        List<Page> pages = new ArrayList<>();

        Text text = new Text("text", "Supply the period of analysis");

        Text text1 = new Text("text", "Previous start date: " + job.getStartDate());
        Text text2 = new Text("text", "Previous end date: " + job.getEndDate());

        Button btnForm = new Button("button", "/job/"+job.getId(), "submit");
        Input inputForm = new Input(new String[]{"startDate", "endDate"}, new String[]{"Enter Start Date:", "Enter end date:"});
        Form form = new Form("form", inputForm, btnForm);

        Button btnNext = new Button("button", "/", "back");
        Button btnBack = new Button("button-back", "/", "back");

        Item<Text> item = new Item<>(text);
        Item<Text> item2 = new Item<>(text1);
        Item<Text> item3 = new Item<>(text2);
        Item<Form> item4 = new Item<>(form);
        Item<Button> item5 = new Item<>(btnBack);

        Body body = new Body(item, item2, item3, item4, item5);
        Page page = new Page(job.getId(), body);
        pages.add(page);
        return pages.get(0);
    }

}