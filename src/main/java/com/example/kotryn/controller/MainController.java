package com.example.kotryn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class MainController {

    private JobController jobController;

    @Autowired
    public MainController(JobController jobController) {
        this.jobController = jobController;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void prevPage() {
        jobController.url = "/page/2";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    RedirectView redirect()  {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/page/2");
        if(jobController.url != null){
            redirectView.setUrl(jobController.url);
        }
        return redirectView;
    }
}
