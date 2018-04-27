package com.example.kotryn.controller;

import com.example.kotryn.entity.Context;
import com.example.kotryn.entity.Job;
import com.example.kotryn.entity.ProcessDescriptor;
import com.example.kotryn.json.Page;
import com.example.kotryn.processes.AbstractProcessFactory;
import com.example.kotryn.processes.ProcessFactory;
import com.example.kotryn.repository.*;
import com.example.kotryn.web.data.*;
import com.example.kotryn.web.pages.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import static com.example.kotryn.states.State.*;

@RestController
public class MainController {

    private JobRepository jobRepository;
    private ProcessDescriptorRepository processDescriptorRepository;
    private ContextRepository contextRepository;

    private String url = "/prompt_user";
    private String error = null;

    public MainController(JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository, ContextRepository contextRepository, StockRepository stockRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.contextRepository = contextRepository;
        AbstractProcessFactory.setFactory(new ProcessFactory(jobRepository, processDescriptorRepository, stockRepository));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void prevPage() {
        error = null;
        url = "/prompt_user";
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void startPageDELETE() {
        error = null;
        this.url = "/prompt_user";
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
        WebPagePromptUser page = new WebPagePromptUser();
        return page.show();
    }

    /*2*/
    @RequestMapping(value = "/connect_to_job", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void connectToJobPOST() {
        url = "/connect_to_job";
    }

    /*3*/
    @RequestMapping(value = "/connect_to_job", method = RequestMethod.GET)
    public Page connectToJobGET() {
        WebPageConnectToJob page = new WebPageConnectToJob(error);
        return page.show();
    }


    /* 2 */
    @RequestMapping(value = "/begin_job", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void beginJobPOST() {
        Job job = new Job();
        job = jobRepository.save(job);
        Context context = new Context(job.getId());
        context = contextRepository.save(context);
        processDescriptorRepository.save(new ProcessDescriptor(job.getId()));
        url = "/begin_job/"+job.getId();
    }

    /* 3 */
    @RequestMapping(value = "/begin_job/{id}", method = RequestMethod.GET)
    public Page beginJobGET(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebPageBeginJob page = new WebPageBeginJob(job.getId());
        return page.show();
    }

    /* 4 */
    @RequestMapping(value = "/jobsPOST", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void jobsPOST(@RequestBody Job requestJob) {
        if(requestJob.getId() == null || !contextRepository.exists(requestJob.getId())){
            error = "Job with id "+requestJob.getId()+" not exist";
            url = "/connect_to_job";
        }else{
            Context context = contextRepository.getOne(requestJob.getId());
            error = null;

            switch (context.getState()){
                case SEARCHING_FOR_STOCKS_IN_PROGRESS:
                    WebDataSearchingForStocksInProgress webData =
                            new WebDataSearchingForStocksInProgress(requestJob.getId());
                    webData.setAction(Action.REFRESH);
                    processJob(webData);
                    break;
                case CALCULATING_SAMPLE_COUNT_IN_PROGRESS: /*AAAAAA*/
                    WebDataCalculatingSampleCountInProgress webData2 =
                            new WebDataCalculatingSampleCountInProgress(requestJob.getId());
                    webData2.setAction(Action.REFRESH);
                    processJob(webData2);
                    break;
                default:
                    context.setState(OBTAINING_PERIOD_OF_ANALYSIS);
                    contextRepository.save(context);
            }

            url = context.redirectToWebPage(this,
                    jobRepository, contextRepository, processDescriptorRepository);
        }
    }

    @RequestMapping(value = "/jobsPOST/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void jobsPOST(@PathVariable Long id) {
        Context context = contextRepository.getOne(id);
        context.setState(OBTAINING_PERIOD_OF_ANALYSIS);
        contextRepository.save(context);
        url = context.redirectToWebPage(this, jobRepository, contextRepository, processDescriptorRepository);
    }

    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void jobsDELETE(@PathVariable Long id) {
        jobRepository.delete(id);
        jobRepository.flush();
        contextRepository.delete(id);
        contextRepository.flush();
        processDescriptorRepository.delete(id);
        processDescriptorRepository.flush();
        this.url = "/prompt_user";
    }


    @RequestMapping(value = "/jobSetDate/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void jobSetDatePOST(@PathVariable Long id, @RequestBody Job addJobRequest) {
        Job job = jobRepository.findOne(id);
        job.setStartDate(addJobRequest.getStartDate());
        job.setEndDate(addJobRequest.getEndDate());
        jobRepository.save(job);
    }

    private String jobsGET(Long id) {
        Context context = contextRepository.getOne(id);
        return context.redirectToWebPage(this, jobRepository, contextRepository, processDescriptorRepository);

    }

    /* 6 */
    @RequestMapping(value = "/period_of_analysis/{id}", method = RequestMethod.GET)
    public Page obtainingPeriodOfAnalysisGET(@PathVariable Long id) {
        WebPageObtainingPeriodOfAnalysis page = new WebPageObtainingPeriodOfAnalysis(id, jobRepository);
        return page.show();
    }

    /* 5 */
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

    @RequestMapping(value = "/stocks_search_in_progress/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void searchingForStocksInProgressPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataSearchingForStocksInProgress webData = new WebDataSearchingForStocksInProgress(id);
        webData.setAction(Action.REFRESH);

        processJob(webData);

        // once 201 is received for POST, browser connects:
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/stocks_search_completed/{id}", method = RequestMethod.GET)
    public Page searchingForStocksCompletedGET(@PathVariable Long id) {
        WebPageStocksSearchCompleted page = new WebPageStocksSearchCompleted(id, jobRepository, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/stocks_search_failed/{id}", method = RequestMethod.GET)
    public Page searchingForStocksFailedGET(@PathVariable Long id) {
        WebPageStocksSearchFailed page = new WebPageStocksSearchFailed(id, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/stocks_search_in_progress/{id}", method = RequestMethod.GET)
    public Page searchingForStocksInProgressGET(@PathVariable Long id) {
        WebPageStocksSearchInProgress page = new WebPageStocksSearchInProgress(id);
        return page.show();
    }




    /*********************/
    @RequestMapping(value = "/calculating_sample_count/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void calculatingSampleCountPOST(@PathVariable Long id, @RequestBody Job addSelectedStocksRequest) {
        Job job = jobRepository.findOne(id);
        System.out.println(addSelectedStocksRequest+" "+addSelectedStocksRequest.getSelectedStocks());
        job.setSelectedStocks(addSelectedStocksRequest.getSelectedStocks());
        job = jobRepository.save(job);

        WebDataSearchingForStocksCompleted webData = new WebDataSearchingForStocksCompleted(job.getId());
        webData.setSelectedStocks(job.getSelectedStocks());

        processJob(webData);
        // once 201 is received for POST, browser connects:
        url = this.jobsGET(job.getId());
        //url = "/prompt_user";
    }

    /*@RequestMapping(value = "/period_of_analysis/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void obtainingPeriodOfAnalysisPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataObtainingPeriodOfAnalysis webData = new WebDataObtainingPeriodOfAnalysis(job.getId());
        webData.setStartDate(job.getStartDate());
        webData.setEndDate(job.getEndDate());

        processJob(webData);
        // once 201 is received for POST, browser connects:
        url = this.jobsGET(job.getId());
    }*/

    /*@RequestMapping(value = "/calculating_sample_count/{id}", method = RequestMethod.GET)
    public Page calculatingSampleCountGET(@PathVariable Long id) {
        WebPageObtainingPeriodOfAnalysis page = new WebPageObtainingPeriodOfAnalysis(id, jobRepository);
        return page.show();
    }*/

    @RequestMapping(value = "/calculating_sample_count_in_progress/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void calculatingSampleCountInProgressPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataCalculatingSampleCountInProgress webData = new WebDataCalculatingSampleCountInProgress(id);
        webData.setAction(Action.REFRESH);

        processJob(webData);

        // once 201 is received for POST, browser connects:
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/calculating_sample_count_completed/{id}", method = RequestMethod.GET)
    public Page calculatingSampleCountInCompletedGET(@PathVariable Long id) {
        WebPageCalculatingSampleCountCompleted page = new WebPageCalculatingSampleCountCompleted(id, jobRepository, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/calculating_sample_count_failed/{id}", method = RequestMethod.GET)
    public Page calculatingSampleCountFailedGET(@PathVariable Long id) {
        WebPageCalculatingSampleCountFailed page = new WebPageCalculatingSampleCountFailed(id, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/calculating_sample_count_in_progress/{id}", method = RequestMethod.GET)
    public Page calculatingSampleCountInProgressGET(@PathVariable Long id) {
        WebPageCalculatingSampleCountInProgress page = new WebPageCalculatingSampleCountInProgress(id);
        return page.show();
    }
}
