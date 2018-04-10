package com.example.kotryn.controller;

import com.example.kotryn.entity.Job.JobFunction;
import com.example.kotryn.entity.Job.Job;
import com.example.kotryn.entity.Job.State;
import com.example.kotryn.json.Page;
import com.example.kotryn.repository.JobRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    private JobRepository jobRepository;

    private String url = null;

    private JobFunction jobFunction = new JobFunction();

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    protected void setUrl(String url){
        this.url = url;
    }

    protected String getUrl(){
        return url;
    }

    @RequestMapping(value = "/newJob", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void beginNewJob() {
        Job job = new Job();
        job = jobRepository.save(job);
        jobFunction.setState(State.NEW);
        this.url = "/jobConnectPage/"+job.getId();
    }

    @RequestMapping(value = "/oldJob", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void connectToJob() {
        jobFunction.setState(State.OLD);
        this.url = "/getJobId";
    }

    @RequestMapping(value = "/getJobId", method = RequestMethod.GET)
    public Page getJobConnectPage() {
        return jobFunction.getJobId();
    }

    @RequestMapping(value = "/setJob", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void setJob(@RequestBody Job requestJob) {
        Job job = jobRepository.findOne(requestJob.getId());
        //job.setState(jobFunction.getEnumState());
        job = jobRepository.save(job);
        this.url = "/jobConnectPage/"+job.getId();
    }

    @RequestMapping(value = "/jobConnectPage/{id}", method = RequestMethod.GET)
    public Page getJobConnectPage(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        jobFunction.setJob(job);
        //jobFunction.setState(job.getState());
        job = jobRepository.save(job);
        return jobFunction.getConnectPage(job);
    }

    //TODO SPYTAC!!!
    @RequestMapping(value = "/jobConfirmConnectPage/{id}", method = RequestMethod.GET)
    public Page getJobConfirmPage(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        if(job == null){
            new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
            return null;
            //return jobFunction.getSupplyPeriodPage();
        }
        jobFunction.setJob(job);
        //jobFunction.setState(job.getState());
        return jobFunction.getSupplyPeriodPage();
    }

    @RequestMapping(value = "/connectJob/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void connectJob(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        this.url = "/jobConfirmConnectPage/"+job.getId();
    }

    @RequestMapping(value = "/getJobParameters/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void getJobParameters(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        this.url = "/jobParametersPage/"+job.getId();
    }

    @RequestMapping(value = "/jobParametersPage/{id}", method = RequestMethod.GET)
    public Page getJobParametersPage(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        jobFunction.setJob(job);
        //jobFunction.setState(job.getState());
        return jobFunction.getParametersPage();
    }

    @RequestMapping(value = "/searchJobInfo/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void searchJobInfo(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        long n = 1;
        //Thread.sleep(100);
        this.url = "/searchJobInfoPage/"+job.getId();
    }

    @RequestMapping(value = "/searchJobInfoPage/{id}", method = RequestMethod.GET)
    public Page searchJobInfoPage(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        jobFunction.setJob(job);
        //jobFunction.setState(job.getState());
        return jobFunction.searchJobInfoPage();
    }

    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public List<Job> findAllJob() {
        return jobRepository.findAll();
    }

    @RequestMapping(value = "/job/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void modifyJob(@PathVariable Long id, @RequestBody Job addJobRequest) {
        Job job = jobRepository.findOne(id);
        job.setStartDate(addJobRequest.getStartDate());
        job.setEndDate(addJobRequest.getEndDate());
        jobRepository.save(job);
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
            Job job = new Job();
            job.setStartDate(addJob.getStartDate());
            job.setEndDate(addJob.getEndDate());
            jobRepository.save(job);
        }
    }

    @RequestMapping(value="job/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteJob(@PathVariable Long id) {
        jobRepository.delete(id);
        this.url = "/pages/2";
    }

}
