package com.example.kotryn.controller;

import com.example.kotryn.entity.Context.Context;
import com.example.kotryn.entity.Job.Job;
import com.example.kotryn.entity.Process.ProcessDescriptor;
import com.example.kotryn.json.Page;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.states.IState;
import com.example.kotryn.states.StateFactory;
import com.example.kotryn.web.pages.*;
import com.example.kotryn.states.State;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class MainController {

    private JobController jobController;
    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private ContextRepository contextRepository;

    private String url = "/prompt_user";

    public MainController(JobController jobController, JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository, ContextRepository contextRepository) {
        this.jobController = jobController;
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.contextRepository = contextRepository;
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

    private void setInitialState(long jobId) {
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
        url = context.redirectToWebPage(this);
    }

    @RequestMapping(value = "/jobsPOST/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void jobsPOST(@PathVariable Long id) {
        Context context = contextRepository.getOne(id);
        url = context.redirectToWebPage(this);
    }

    /* 5 */
    @RequestMapping(value = "/period_of_analysis/{id}", method = RequestMethod.GET)
    public Page obtainingPeriodOfAnalysisGET(@PathVariable Long id) {
        WebPageObtainingPeriodOfAnalysis page = new WebPageObtainingPeriodOfAnalysis(id, this, jobRepository);
        return page.show();
    }




    @RequestMapping(value = "/stocks_search_completed", params = "jobid", method = RequestMethod.GET)
    public Page searchingForStocksCompletedGET(Long jobId) {
        WebPageStocksSearchCompleted page = new WebPageStocksSearchCompleted(jobId, this, jobRepository, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/stocks_search_failed", params = "jobid", method = RequestMethod.GET)
    public Page searchingForStocksFailedGET(long jobId) {
        WebPageStocksSearchFailed page = new WebPageStocksSearchFailed(jobId,this, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/stocks_search_in_progress", params = "jobid", method = RequestMethod.GET)
    public Page searchingForStocksInProgressGET(long jobId) {
        WebPageStocksSearchInProgress page = new WebPageStocksSearchInProgress(jobId, this);
        return page.show();
    }
}
