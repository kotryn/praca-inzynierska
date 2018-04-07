package com.example.kotryn.entity.Job;

import com.example.kotryn.json.*;

public class OldJob implements JobState{

    public Page getJobId() {
        Button btnForm = new Button("button", "/setJob", "submit");
        Input inputForm = new Input(new String[]{"id"}, new String[]{"Supply job ID:"});
        Form form = new Form("form", inputForm, btnForm);

        Item<Form> item = new Item<>(form);

        Body body = new Body(item);

        return new Page(1L, body);
    }

    public Page getConnectPage(Long id) {
        System.out.println("PAGE OLD : "+id);

        Text text = new Text("text", "Job id: "+ id);
        Button btnConnect = new Button("button", "/connectJob/"+id, "connect");
        Button btnBack = new Button("button-back", "/", "back");
        Item<Text> item = new Item<>(text);
        Item<Button> item2 = new Item<>(btnConnect);
        Item<Button> item3 = new Item<>(btnBack);
        Body body = new Body(item, item2, item3);

        return new Page(id, body);
    }

}
