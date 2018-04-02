package com.example.kotryn.json;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PageService {
    private final List<Page> pages = new ArrayList<>();

    public PageService() {
        /*Graph*/
        Text text = new Text("text", "tulipany");
        Image img = new Image("image", "/tulipan.jpg");
        Button btnBack = new Button("button-back", "/", "back");

        Item<Text> item = new Item<>(text);
        Item<Image> item6 = new Item<>(img);
        Item<Button> btnBackI = new Item<>(btnBack);

        Body graph = new Body(item, item6, btnBackI);
        Page graphPage = new Page(0L, graph);

        pages.add(graphPage);

        /*Form*/
        Text titleText = new Text("text", "Users list");

        Table tableForm = new Table("table", new String[]{"Name", "Surname", "Age"}, "/users");

        Button btnForm = new Button("button", "/user", "next");
        Input inputForm = new Input(new String[]{"name", "surname", "age"}, new String[]{"Name:", "Surname:", "Age:"});
        Form form = new Form("form", inputForm, btnForm);

        Item<Text> formTitle = new Item<>(titleText);
        Item<Form> formItem1 = new Item<>(form);
        Item<Table> formTable = new Item<>(tableForm);
        Body formBody = new Body(formTitle, formTable, formItem1, btnBackI);
        Page formPage = new Page(1L, formBody);

        pages.add(formPage);

        /*Login*/
        Button newJob = new Button("button", "/newJob", "Begin a new job");
        Button connectJob = new Button("button", "/oldJob", "Connect to a job");

        Item<Button> newJobI = new Item<>(newJob);
        Item<Button> connectJobI = new Item<>(connectJob);

        Body loginBody = new Body(newJobI, connectJobI);
        Page loginPage = new Page(2L, loginBody);

        pages.add(loginPage);

        /*New Job*/
        //Text jobId = new Text("text", "Your job ID is: "+ 1);


    }

    public List<Page> getAllPages() {
        return pages;
    }

    public Page create(Page pages) {
        this.pages.add(pages);
        return pages;
    }

    public Page getPage(Long id) {
        return this.pages.stream().filter((m) -> m.getId() == id).findFirst().get();
    }
}
