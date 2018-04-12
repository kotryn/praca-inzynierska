package com.example.kotryn.controller;

import com.example.kotryn.entity.Context.Context;
import com.example.kotryn.entity.Job.Job;
import com.example.kotryn.entity.Process.ProcessDescriptor;
import com.example.kotryn.json.Page;
import com.example.kotryn.processes.AbstractProcessFactory;
import com.example.kotryn.processes.ProcessFactory;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataObtainingPeriodOfAnalysis;
import com.example.kotryn.web.pages.*;
import com.example.kotryn.states.State;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class MainController {

    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private ContextRepository contextRepository;

    private String url = "/prompt_user";

    public MainController(JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository, ContextRepository contextRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.contextRepository = contextRepository;
        AbstractProcessFactory.setFactory(new ProcessFactory(jobRepository, processDescriptorRepository));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void prevPage() {
        url = "/prompt_user";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    RedirectView redirect()  {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        return redirectView;
    }



    /* 1 */
    @RequestMapping(value = "/prompt_user", method = RequestMethod.GET)
    public Page promptUserGET() {
        WebPagePromptUser page = new WebPagePromptUser(this);
        return page.show();
    }

    @RequestMapping(value = "/connect_to_job", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void connectToJobPOST() {
        url = "/connect_to_job";
    }

    @RequestMapping(value = "/connect_to_job", method = RequestMethod.GET)
    public Page connectToJobGET() {
        WebPageConnectToJob page = new WebPageConnectToJob(this);
        return page.show();
    }

    private void setInitialState(Long jobId) {
        Context context = contextRepository.findOne(jobId);
        context.setState(State.OBTAINING_PERIOD_OF_ANALYSIS);
        contextRepository.save(context);
    }

    /* 2 */
    @RequestMapping(value = "/begin_job", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void beginJobPOST() {
        Job job = new Job();
        job = jobRepository.save(job);
        contextRepository.save(new Context(job.getId()));
        processDescriptorRepository.save(new ProcessDescriptor(job.getId()));
        setInitialState(job.getId());
        url = "/begin_job/"+job.getId();
    }

    /* 3 */
    @RequestMapping(value = "/begin_job/{id}", method = RequestMethod.GET)
    public Page beginJobGET(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebPageBeginJob page = new WebPageBeginJob(job.getId(), this);
        return page.show();
    }

    /* 4 */
    @RequestMapping(value = "/jobsPOST", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void jobsPOST(@RequestBody Job requestJob) {
        Context context = contextRepository.getOne(requestJob.getId());
        url = context.redirectToWebPage(this, jobRepository, contextRepository, processDescriptorRepository);
    }

    @RequestMapping(value = "/jobsPOST/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void jobsPOST(@PathVariable Long id) {
        Context context = contextRepository.getOne(id);
        url = context.redirectToWebPage(this, jobRepository, contextRepository, processDescriptorRepository);
    }

    /*@RequestMapping(value = "/jobsGET/{id}", method = RequestMethod.GET)
    public void jobsGET(@PathVariable Long id) {
        Context context = contextRepository.getOne(id);
        url = context.redirectToWebPage(this, jobRepository, contextRepository, processDescriptorRepository);
        System.out.println(url);
    }*/

    private String jobsGET(Long id) {
        Context context = contextRepository.getOne(id);
        return context.redirectToWebPage(this, jobRepository, contextRepository, processDescriptorRepository);

    }

    /* 5 */
    @RequestMapping(value = "/period_of_analysis/{id}", method = RequestMethod.GET)
    public Page obtainingPeriodOfAnalysisGET(@PathVariable Long id) {
        WebPageObtainingPeriodOfAnalysis page = new WebPageObtainingPeriodOfAnalysis(id, this, jobRepository);
        return page.show();
    }

    /* 6 */
    @RequestMapping(value = "/period_of_analysis/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void obtainingPeriodOfAnalysisPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataObtainingPeriodOfAnalysis webData = new WebDataObtainingPeriodOfAnalysis(job.getId());
        webData.setStartDate(job.getStartDate());
        webData.setEndDate(job.getEndDate());

        processJob(webData);
        // once 201 is received for POST, browser connects:
        url = this.jobsGET(job.getId());
    }

    private void processJob(IWebData webData) {
        Context context = contextRepository.getOne(webData.getJobId());
        if (context != null) {
            context.handle(webData, jobRepository, contextRepository, processDescriptorRepository);
        } else {
            throw new RuntimeException("Invalid jobId");
        }
    }


    @RequestMapping(value = "/stocks_search_completed", params = "jobid", method = RequestMethod.GET)
    public Page searchingForStocksCompletedGET(Long jobId) {
        WebPageStocksSearchCompleted page = new WebPageStocksSearchCompleted(jobId, this, jobRepository, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/stocks_search_failed", params = "jobid", method = RequestMethod.GET)
    public Page searchingForStocksFailedGET(Long jobId) {
        WebPageStocksSearchFailed page = new WebPageStocksSearchFailed(jobId,this, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/stocks_search_in_progress/{id}", method = RequestMethod.GET)
    public Page searchingForStocksInProgressGET(@PathVariable Long id) {
        WebPageStocksSearchInProgress page = new WebPageStocksSearchInProgress(id, this);
        return page.show();
    }
}
