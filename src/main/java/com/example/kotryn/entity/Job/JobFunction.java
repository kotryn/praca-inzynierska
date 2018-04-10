package com.example.kotryn.entity.Job;

import com.example.kotryn.json.*;

public class JobFunction {

    private Job job;
    private JobState state;

    public JobFunction(){
        this.job = null;
        this.state = null;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Job getJob() {
        return job;
    }

    public void setState(State state) {
        switch(state){
            case NEW:
                this.state = new NewJob();
                break;
            case OLD:
                this.state = new OldJob();
        }
    }

    public JobState getState() {
        return state;
    }

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

    public Page getSupplyPeriodPage() {

        Text text = new Text("text", "Supply the period of analysis");

        Text text1 = new Text("text", "Previous start date: " + job.getStartDate());
        Text text2 = new Text("text", "Previous end date: " + job.getEndDate());

        Button btnForm = new Button("button", "/job/"+job.getId(), "submit");
        Input inputForm = new Input(new String[]{"startDate", "endDate"}, new String[]{"Enter Start Date:", "Enter end date:"});
        Form form = new Form("form", inputForm, btnForm);

        Button btnBack = new Button("button-back", "/", "back");
        Button btnNext= new Button("button", "/getJobParameters/"+job.getId(), "Next");

        Item<Text> item = new Item<>(text);
        Item<Text> item2 = new Item<>(text1);
        Item<Text> item3 = new Item<>(text2);
        Item<Form> item4 = new Item<>(form);
        Item<Button> item5 = new Item<>(btnBack);
        Item<Button> item6 = new Item<>(btnNext);

        Body body = new Body(item, item2, item3, item4, item5, item6);

        return new Page(body);
    }

    public Page getParametersPage() {

        Button back = new Button("button-back", "/", "back");
        Button delete = new Button("button-delete", "/job/"+job.getId(), "Delete job");
        Button next = new Button("button", "/searchJobInfo/"+job.getId(), "Next");


        Item<Button> item = new Item<>(back);
        Item<Button> item2 = new Item<>(delete);
        Item<Button> item3 = new Item<>(next);

        Body body = new Body(item, item2, item3);

        return new Page(body);
    }

    public Page searchJobInfoPage() {

        Text text = new Text("text", "Searching for stocks");

        Button back = new Button("button-back", "/", "back");
        Button refresh = new Button("button", "/searchJobInfo/"+job.getId(), "Refresh");


        Item<Button> item = new Item<>(back);
        Item<Button> item2 = new Item<>(refresh);
        Item<Text> item3 = new Item<>(text);

        Body body = new Body(item3, item, item2);

        return new Page(body);
    }

}