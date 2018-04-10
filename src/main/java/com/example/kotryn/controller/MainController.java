package com.example.kotryn.controller;

import com.example.kotryn.entity.Context.Context;
import com.example.kotryn.entity.Job.Job;
import com.example.kotryn.entity.Process.ProcessDescriptor;
import com.example.kotryn.json.Page;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
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
        Context context = contextRepository.getOne(jobId);
        context.setState(State.OBTAINING_PERIOD_OF_ANALYSIS);
        contextRepository.save(context);
    }

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

    @RequestMapping(value = "/begin_job/{id}", method = RequestMethod.GET)
    public Page beginJobGET(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebPageBeginJob page = new WebPageBeginJob(job.getId(), this);
        return page.show();
    }

    public void jobsGET(long jobId) {
        Context context = contextRepository.getOne(jobId);
        if (context != null) {
            context.redirectToWebPage(this);
        } else {
            //connectToJobGET(); // redirect to
        }
    }

    @RequestMapping(value = "/period_of_analysis", params = "jobid", method = RequestMethod.GET)
    public Page obtainingPeriodOfAnalysisGET(Long jobId) {
        WebPageObtainingPeriodOfAnalysis page = new WebPageObtainingPeriodOfAnalysis(jobId, this, jobRepository);
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
