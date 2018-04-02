package com.example.kotryn.controller;

import com.example.kotryn.entity.Job.NewJob;
import com.example.kotryn.entity.Job.Job;
import com.example.kotryn.entity.Job.OldJob;
import com.example.kotryn.json.Page;
import com.example.kotryn.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    private JobRepository jobRepository;
    protected String url = null;

    @Autowired
    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @RequestMapping(value = "/jobConnectPage/{id}", method = RequestMethod.GET)
    public Page getJobConnectPage(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        //return job.getConnectPage();
        return null;
    }

    @RequestMapping(value = "/jobConfirmConnectPage/{id}", method = RequestMethod.GET)
    public Page getJobConfirmPage(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        //return job.getSupplyPeriodPage();
        return null;
    }

    @RequestMapping(value = "/newJob", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void beginNewJob() {
        Job job = new Job(/*new NewJob()*/);
        jobRepository.save(job);
        //job.connect(); connectNewJob
        this.url = "/jobConnectPage/"+job.getId();
    }

    @RequestMapping(value = "/connectNewJob/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void connectNewJob(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        //job.connect(); connectNewJob
        this.url = "/jobConfirmConnectPage/"+job.getId();
    }

    @RequestMapping(value = "/oldJob", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void connectToJob() {
        Job job = jobRepository.findOne(1L);
        //job.setState(new OldJob());
        //this.url = job.connect();
        this.url = "/jobConnectPage/"+job.getId();
    }

    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public List<Job> findAllJob() {
        return jobRepository.findAll();
    }

    @RequestMapping(value = "/job/{id}", method = RequestMethod.GET)
    public Job getJobById(@PathVariable Long id) {
        return jobRepository.findOne(id);
    }

    @RequestMapping(value = "/job", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewJob(@RequestBody Job addJobRequest) {
        Job job = new Job();
        job.setStartDate(addJobRequest.getStartDate());
        job.setEndDate(addJobRequest.getEndDate());
        jobRepository.save(job);
    }

    @RequestMapping(value = "/jobs", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void addNewListOfJobs(@RequestBody List<Job> addJobRequest) {
        for (Job addJob : addJobRequest) {
            System.out.println(addJobRequest);
            Job job = new Job();
            job.setStartDate(addJob.getStartDate());
            job.setEndDate(addJob.getEndDate());
            jobRepository.save(job);
        }
    }

   /* @RequestMapping(value = "/data", method = RequestMethod.GET)
    private RedirectView redirect()  {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/page/2");
        if(this.url != null){
            redirectView.setUrl(this.url);
        }
        return redirectView;
    }*/
}
