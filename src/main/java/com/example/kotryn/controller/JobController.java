package com.example.kotryn.controller;

import com.example.kotryn.entity.Job;
import com.example.kotryn.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    private JobRepository jobRepository;

    @Autowired
    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public List<Job> findAllUsers() {
        return jobRepository.findAll();
    }

    @RequestMapping(value = "/job/{id}", method = RequestMethod.GET)
    public Job getJobById(@PathVariable long id) {
        //System.out.println(jobRepository.getOne(id));
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

    /*@RequestMapping(value = "/data", method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }*/
}
