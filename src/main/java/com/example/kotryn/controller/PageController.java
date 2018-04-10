package com.example.kotryn.controller;

import com.example.kotryn.json.Page;
import com.example.kotryn.json.PageService;
import com.example.kotryn.json.View;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PageController {

    private PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @JsonView(View.Summary.class)
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public List<Page> getAllPages() {
        return pageService.getAllPages();
    }

}
