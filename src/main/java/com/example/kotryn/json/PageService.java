package com.example.kotryn.json;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PageService {
    private final List<Page> pages = new ArrayList<>();

    public PageService() {
        Text text = new Text("text", "tulipany");
        Image img = new Image("image", "/tulipan.jpg");
        Button btnBack = new Button("button-back", "/", "back");

        Item<Text> item = new Item<>(text);
        Item<Image> item6 = new Item<>(img);
        Item<Button> item7 = new Item<>(btnBack);

        Body graph = new Body(item, item6, item7);
        Page graphPage = new Page(0L, graph);

        pages.add(graphPage);
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
