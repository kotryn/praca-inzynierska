package com.example.kotryn.controller;

import com.example.kotryn.json.Page;
import com.example.kotryn.json.PageService;
import com.example.kotryn.json.View;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PageController {
    @Autowired
    private PageService pageService;

    @JsonView(View.Summary.class)
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public List<Page> getAllPages() {
        return pageService.getAllPages();
    }

    @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
    public Page getPage(@PathVariable Long id) {
        return this.pageService.getPage(id);
    }
}
