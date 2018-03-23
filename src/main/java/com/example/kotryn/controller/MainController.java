package com.example.kotryn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Random;

@RestController
public class MainController {

    @Autowired
    public MainController() {

    }

   /* private BodyService bodyService;

    @JsonView(View.Summary.class)
    @RequestMapping("/body")
    public List<Body> getAllBodies() {
        return bodyService.getAll();
    }*/

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void prevPage() {

    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    RedirectView redirect()  {
        RedirectView redirectView = new RedirectView();
        /*if (userRepository.count() > 0) {
            redirectView.setUrl("/students");
        } else {
            redirectView.setUrl("/lecture");
        }*/
        //Random rand = new Random();
        //int  n = rand.nextInt(2) + 1;
        //switch(n){
            //case 1:
               // redirectView.setUrl("/page/1");
               // break;
            /*case 2:
                redirectView.setUrl("/usersconfig.json");
                break;
            case 3:
                redirectView.setUrl("/bookconfig.json");
                break;*/
            //default:
                //redirectView.setUrl("/page/0");
        //}
        redirectView.setUrl("/page/2");
        return redirectView;
    }
}
