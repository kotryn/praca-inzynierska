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

    @RequestMapping(value = "/prompt_user", method = RequestMethod.GET)
    public Page promptUserGET() {
        WebPagePromptUser page = new WebPagePromptUser();
        return page.show();
    }

    @RequestMapping(value = "/connect_to_job", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void connectToJobPOST() {
        url = "/connect_to_job";
    }

    @RequestMapping(value = "/connect_to_job", method = RequestMethod.GET)
    public Page connectToJobGET() {
        WebPageConnectToJob page = new WebPageConnectToJob(error);
        return page.show();
    }

    @RequestMapping(value = "/begin_job", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void beginJobPOST() {
        Job job = new Job();
        job = jobRepository.save(job);
        Context context = new Context(job.getId());
        contextRepository.save(context);
        processDescriptorRepository.save(new ProcessDescriptor(job.getId()));
        url = "/begin_job/"+job.getId();
    }

    @RequestMapping(value = "/begin_job/{id}", method = RequestMethod.GET)
    public Page beginJobGET(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebPageBeginJob page = new WebPageBeginJob(job.getId());
        return page.show();
    }

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
                case CALCULATING_SAMPLE_COUNT_IN_PROGRESS:
                    WebDataCalculatingSampleCountInProgress webData2 =
                            new WebDataCalculatingSampleCountInProgress(requestJob.getId());
                    webData2.setAction(Action.REFRESH);
                    processJob(webData2);
                    break;
                case ESTIMATING_WORST_CASE_DISTRIBUTIONS_IN_PROGRESS:
                    WebDataEstimatingWorstCaseDistributionsInProgress webData3 =
                            new WebDataEstimatingWorstCaseDistributionsInProgress(requestJob.getId());
                    webData3.setAction(Action.REFRESH);
                    processJob(webData3);
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
        Context context = contextRepository.getOne(id);
        switch (context.getState()){
            case SEARCHING_FOR_STOCKS_IN_PROGRESS:
                WebDataSearchingForStocksInProgress webData = new WebDataSearchingForStocksInProgress(id);
                webData.setAction(Action.INTERRUPT);
                processJob(webData);
                break;
            case CALCULATING_SAMPLE_COUNT_IN_PROGRESS:
                WebDataCalculatingSampleCountInProgress webData2 = new WebDataCalculatingSampleCountInProgress(id);
                webData2.setAction(Action.INTERRUPT);
                processJob(webData2);
                break;
            case ESTIMATING_WORST_CASE_DISTRIBUTIONS_IN_PROGRESS:
                WebDataEstimatingWorstCaseDistributionsInProgress webData3 = new WebDataEstimatingWorstCaseDistributionsInProgress(id);
                webData3.setAction(Action.INTERRUPT);
                processJob(webData3);
                break;
        }

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

    @RequestMapping(value = "/period_of_analysis/{id}", method = RequestMethod.GET)
    public Page obtainingPeriodOfAnalysisGET(@PathVariable Long id) {
        WebPageObtainingPeriodOfAnalysis page = new WebPageObtainingPeriodOfAnalysis(id, jobRepository);
        return page.show();
    }

    @RequestMapping(value = "/period_of_analysis/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void obtainingPeriodOfAnalysisPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataObtainingPeriodOfAnalysis webData = new WebDataObtainingPeriodOfAnalysis(job.getId());
        webData.setStartDate(job.getStartDate());
        webData.setEndDate(job.getEndDate());

        processJob(webData);
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
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/stocks_search_in_progress_back/{id}", method = RequestMethod.POST)
    public void searchingForStocksInProgressBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataSearchingForStocksInProgress webData = new WebDataSearchingForStocksInProgress(id);
        webData.setAction(Action.INTERRUPT);
        processJob(webData);
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

    @RequestMapping(value = "/calculating_sample_count/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void calculatingSampleCountPOST(@PathVariable Long id, @RequestBody Job addSelectedStocksRequest) {
        Job job = jobRepository.findOne(id);
        job.setSelectedStocks(addSelectedStocksRequest.getSelectedStocks());
        job = jobRepository.save(job);

        WebDataSearchingForStocksCompleted webData = new WebDataSearchingForStocksCompleted(job.getId());
        webData.setSelectedStocks(job.getSelectedStocks());

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/calculating_sample_count_in_progress/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void calculatingSampleCountInProgressPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataCalculatingSampleCountInProgress webData = new WebDataCalculatingSampleCountInProgress(id);
        webData.setAction(Action.REFRESH);

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/calculating_sample_count_in_progress/{id}", method = RequestMethod.GET)
    public Page calculatingSampleCountInProgressGET(@PathVariable Long id) {
        WebPageCalculatingSampleCountInProgress page = new WebPageCalculatingSampleCountInProgress(id);
        return page.show();
    }

    @RequestMapping(value = "/calculating_sample_count_completed/{id}", method = RequestMethod.GET)
    public Page calculatingSampleCountCompletedGET(@PathVariable Long id) {
        WebPageCalculatingSampleCountCompleted page = new WebPageCalculatingSampleCountCompleted(id, jobRepository, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/calculating_sample_count_failed/{id}", method = RequestMethod.GET)
    public Page calculatingSampleCountFailedGET(@PathVariable Long id) {
        WebPageCalculatingSampleCountFailed page = new WebPageCalculatingSampleCountFailed(id, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/calculating_sample_count_in_progress_back/{id}", method = RequestMethod.POST)
    public void calculatingSampleCountInProgressBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataCalculatingSampleCountInProgress webData = new WebDataCalculatingSampleCountInProgress(id);
        webData.setAction(Action.INTERRUPT);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/calculating_sample_count_completed_back/{id}", method = RequestMethod.POST)
    public void calculatingSampleCountCompletedBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataCalculatingSampleCountCompleted webData = new WebDataCalculatingSampleCountCompleted(id);
        webData.setAction(Action.PREVIOUS);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/estimating_worst_case_distributions_setup/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void estimatingWorstCaseDistributionsSetupPOST(@PathVariable Long id, @RequestBody Job addSelectedCalculatingSampleRequest) {
        Job job = jobRepository.findOne(id);
        job.setSelectedCalculatingSample(addSelectedCalculatingSampleRequest.getSelectedCalculatingSample());
        job = jobRepository.save(job);

        WebDataCalculatingSampleCountCompleted webData = new WebDataCalculatingSampleCountCompleted(job.getId());
        webData.setSelectedCalculatingSample(job.getSelectedCalculatingSample());

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/estimating_worst_case_distributions/{id}", method = RequestMethod.GET)
    public Page estimatingWorstCaseDistributionsGET(@PathVariable Long id) {
        WebPageEstimatingWorstCaseDistributionsSetup page = new WebPageEstimatingWorstCaseDistributionsSetup(id);
        return page.show();
    }

    @RequestMapping(value = "/estimating_worst_case_distributions/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void estimatingWorstCaseDistributionsPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataEstimatingWorstCaseDistributionsSetup webData = new WebDataEstimatingWorstCaseDistributionsSetup(id);

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/estimating_worst_case_distributions_in_progress/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void estimatingWorstCaseDistributionsInProgressPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataEstimatingWorstCaseDistributionsInProgress webData = new WebDataEstimatingWorstCaseDistributionsInProgress(id);
        webData.setAction(Action.REFRESH);

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/estimating_worst_case_distributions_in_progress/{id}", method = RequestMethod.GET)
    public Page estimatingWorstCaseDistributionsInProgressGET(@PathVariable Long id) {
        WebPageEstimatingWorstCaseDistributionsInProgress page = new WebPageEstimatingWorstCaseDistributionsInProgress(id);
        return page.show();
    }

    @RequestMapping(value = "/estimating_worst_case_distributions_completed/{id}", method = RequestMethod.GET)
    public Page estimatingWorstCaseDistributionsCompletedGET(@PathVariable Long id) {
        WebPageEstimatingWorstCaseDistributionsCompleted page = new WebPageEstimatingWorstCaseDistributionsCompleted(id, jobRepository, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/estimating_worst_case_distributions_failed/{id}", method = RequestMethod.GET)
    public Page estimatingWorstCaseDistributionsFailedGET(@PathVariable Long id) {
        WebPageEstimatingWorstCaseDistributionsFailed page = new WebPageEstimatingWorstCaseDistributionsFailed(id, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/estimating_worst_case_distributions_setup_back/{id}", method = RequestMethod.POST)
    public void estimatingWorstCaseDistributionsSetupBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataEstimatingWorstCaseDistributionsSetup webData = new WebDataEstimatingWorstCaseDistributionsSetup(id);
        webData.setAction(Action.PREVIOUS);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/estimating_worst_case_distributions_completed_back/{id}", method = RequestMethod.POST)
    public void estimatingWorstCaseDistributionsCompletedBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataEstimatingWorstCaseDistributionsCompleted webData = new WebDataEstimatingWorstCaseDistributionsCompleted(id);
        webData.setAction(Action.PREVIOUS);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/estimating_worst_case_distributions_in_progress_back/{id}", method = RequestMethod.POST)
    public void estimatingWorstCaseDistributionsInProgressBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);

        WebDataEstimatingWorstCaseDistributionsInProgress webData = new WebDataEstimatingWorstCaseDistributionsInProgress(id);
        webData.setAction(Action.INTERRUPT);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    /****/
    @RequestMapping(value = "/estimating_growth_stocks/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void estimatingGrowthStocksPOST(@PathVariable Long id, @RequestBody Job addSelectedStocksRequest) {
        Job job = jobRepository.findOne(id);
        //job.setSelectedStocks(addSelectedStocksRequest.getSelectedStocks());
        job = jobRepository.save(job);

        WebDataEstimatingWorstCaseDistributionsCompleted webData = new WebDataEstimatingWorstCaseDistributionsCompleted(job.getId());
        //webData.setSelectedStocks(job.getSelectedStocks());

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/estimating_growth_stocks_in_progress/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void estimatingGrowthStocksInProgressPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataEstimatingGrowthStocksInProgress webData = new WebDataEstimatingGrowthStocksInProgress(id);
        webData.setAction(Action.REFRESH);

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/estimating_growth_stocks_in_progress/{id}", method = RequestMethod.GET)
    public Page estimatingGrowthStocksInProgressGET(@PathVariable Long id) {
        WebPageEstimatingGrowthStocksInProgress page = new WebPageEstimatingGrowthStocksInProgress(id);
        return page.show();
    }

    @RequestMapping(value = "/estimating_growth_stocks_completed/{id}", method = RequestMethod.GET)
    public Page estimatingGrowthStocksCompletedGET(@PathVariable Long id) {
        WebPageEstimatingGrowthStocksCompleted page = new WebPageEstimatingGrowthStocksCompleted(id, jobRepository, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/estimating_growth_stocks_failed/{id}", method = RequestMethod.GET)
    public Page estimatingGrowthStocksFailedGET(@PathVariable Long id) {
        WebPageEstimatingGrowthStocksFailed page = new WebPageEstimatingGrowthStocksFailed(id, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/estimating_growth_stocks_in_progress_back/{id}", method = RequestMethod.POST)
    public void estimatingGrowthStocksInProgressBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataEstimatingGrowthStocksInProgress webData = new WebDataEstimatingGrowthStocksInProgress(id);
        webData.setAction(Action.INTERRUPT);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/estimating_growth_stocks_completed_back/{id}", method = RequestMethod.POST)
    public void estimatingGrowthStocksCompletedBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataEstimatingGrowthStocksCompleted webData = new WebDataEstimatingGrowthStocksCompleted(id);
        webData.setAction(Action.PREVIOUS);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    /***/


    /****/
    @RequestMapping(value = "/estimating_non_correlated_stocks/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void estimatingNonCorrelatedStocksPOST(@PathVariable Long id, @RequestBody Job addSelectedStocksRequest) {
        Job job = jobRepository.findOne(id);
        //job.setSelectedStocks(addSelectedStocksRequest.getSelectedStocks());
        job = jobRepository.save(job);

        WebDataEstimatingGrowthStocksCompleted webData = new WebDataEstimatingGrowthStocksCompleted(job.getId());
        //webData.setSelectedStocks(job.getSelectedStocks());

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/estimating_non_correlated_stocks_in_progress/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void estimatingNonCorrelatedStocksInProgressPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataEstimatingNonCorrelatedStocksInProgress webData = new WebDataEstimatingNonCorrelatedStocksInProgress(id);
        webData.setAction(Action.REFRESH);

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/estimating_non_correlated_stocks_in_progress/{id}", method = RequestMethod.GET)
    public Page estimatingNonCorrelatedStocksInProgressGET(@PathVariable Long id) {
        WebPageEstimatingNonCorrelatedStocksInProgress page = new WebPageEstimatingNonCorrelatedStocksInProgress(id);
        return page.show();
    }

    @RequestMapping(value = "/estimating_non_correlated_stocks_completed/{id}", method = RequestMethod.GET)
    public Page estimatingNonCorrelatedStocksCompletedGET(@PathVariable Long id) {
        WebPageEstimatingNonCorrelatedStocksCompleted page = new WebPageEstimatingNonCorrelatedStocksCompleted(id, jobRepository, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/estimating_non_correlated_stocks_failed/{id}", method = RequestMethod.GET)
    public Page estimatingNonCorrelatedStocksFailedGET(@PathVariable Long id) {
        WebPageEstimatingNonCorrelatedStocksFailed page = new WebPageEstimatingNonCorrelatedStocksFailed(id, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/estimating_non_correlated_stocks_in_progress_back/{id}", method = RequestMethod.POST)
    public void estimatingNonCorrelatedStocksInProgressBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataEstimatingNonCorrelatedStocksInProgress webData = new WebDataEstimatingNonCorrelatedStocksInProgress(id);
        webData.setAction(Action.INTERRUPT);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

   @RequestMapping(value = "/estimating_non_correlated_stocks_completed_back/{id}", method = RequestMethod.POST)
    public void estimatingNonCorrelatedStocksCompletedBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
       WebDataEstimatingNonCorrelatedStocksCompleted webData = new WebDataEstimatingNonCorrelatedStocksCompleted(id);
        webData.setAction(Action.PREVIOUS);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    /****/
    //ESTIMATING_WORST_CASE_COPULA
    @RequestMapping(value = "/estimating_worst_case_copula/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void estimatingWorstCaseCopulaPOST(@PathVariable Long id, @RequestBody Job addSelectedStocksRequest) {
        Job job = jobRepository.findOne(id);
        //job.setSelectedStocks(addSelectedStocksRequest.getSelectedStocks());
        job = jobRepository.save(job);

        WebDataEstimatingNonCorrelatedStocksCompleted webData = new WebDataEstimatingNonCorrelatedStocksCompleted(job.getId());
        //webData.setSelectedStocks(job.getSelectedStocks());

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/estimating_worst_case_copula_in_progress/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void estimatingWorstCaseCopulaInProgressPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataEstimatingWorstCaseCopulaInProgress webData = new WebDataEstimatingWorstCaseCopulaInProgress(id);
        webData.setAction(Action.REFRESH);

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/estimating_worst_case_copula_in_progress/{id}", method = RequestMethod.GET)
    public Page estimatingWorstCaseCopulaInProgressGET(@PathVariable Long id) {
        WebPageEstimatingWorstCaseCopulaInProgress page = new WebPageEstimatingWorstCaseCopulaInProgress(id);
        return page.show();
    }

    @RequestMapping(value = "/estimating_worst_case_copula_completed/{id}", method = RequestMethod.GET)
    public Page estimatingWorstCaseCopulaCompletedGET(@PathVariable Long id) {
        WebPageEstimatingWorstCaseCopulaCompleted page = new WebPageEstimatingWorstCaseCopulaCompleted(id, jobRepository, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/estimating_worst_case_copula_failed/{id}", method = RequestMethod.GET)
    public Page estimatingWorstCaseCopulaFailedGET(@PathVariable Long id) {
        WebPageEstimatingWorstCaseCopulaFailed page = new WebPageEstimatingWorstCaseCopulaFailed(id, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/estimating_worst_case_copula_in_progress_back/{id}", method = RequestMethod.POST)
    public void estimatingWorstCaseCopulaInProgressBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataEstimatingWorstCaseCopulaInProgress webData = new WebDataEstimatingWorstCaseCopulaInProgress(id);
        webData.setAction(Action.INTERRUPT);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/estimating_worst_case_copula_in_progress_completed_back/{id}", method = RequestMethod.POST)
    public void estimatingWorstCaseCopulaCompletedBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataEstimatingWorstCaseCopulaCompleted webData = new WebDataEstimatingWorstCaseCopulaCompleted(id);
        webData.setAction(Action.PREVIOUS);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    /***/
    /****/
    //BUILDING_ROBUST_PORTFOLIO
    @RequestMapping(value = "/building_robust_portfolio/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void buildingRobustPortfolioPOST(@PathVariable Long id, @RequestBody Job addSelectedStocksRequest) {
        Job job = jobRepository.findOne(id);
        //job.setSelectedStocks(addSelectedStocksRequest.getSelectedStocks());
        job = jobRepository.save(job);

        WebDataEstimatingWorstCaseCopulaCompleted webData = new WebDataEstimatingWorstCaseCopulaCompleted(job.getId());
        //webData.setSelectedStocks(job.getSelectedStocks());

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/building_robust_portfolio_in_progress/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void buildingRobustPortfolioInProgressPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataBuildingRobustPortfolioInProgress webData = new WebDataBuildingRobustPortfolioInProgress(id);
        webData.setAction(Action.REFRESH);

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/building_robust_portfolio_in_progress/{id}", method = RequestMethod.GET)
    public Page buildingRobustPortfolioInProgressGET(@PathVariable Long id) {
        WebPageBuildingRobustPortfolioInProgress page = new WebPageBuildingRobustPortfolioInProgress(id);
        return page.show();
    }

    @RequestMapping(value = "/building_robust_portfolio_completed/{id}", method = RequestMethod.GET)
    public Page buildingRobustPortfolioCompletedGET(@PathVariable Long id) {
        WebPageBuildingRobustPortfolioCompleted page = new WebPageBuildingRobustPortfolioCompleted(id, jobRepository, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/building_robust_portfolio_failed/{id}", method = RequestMethod.GET)
    public Page buildingRobustPortfolioFailedGET(@PathVariable Long id) {
        WebPageBuildingRobustPortfolioFailed page = new WebPageBuildingRobustPortfolioFailed(id, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/building_robust_portfolio_in_progress_back/{id}", method = RequestMethod.POST)
    public void buildingRobustPortfolioInProgressBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataBuildingRobustPortfolioInProgress webData = new WebDataBuildingRobustPortfolioInProgress(id);
        webData.setAction(Action.INTERRUPT);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/building_robust_portfolio_in_progress_completed_back/{id}", method = RequestMethod.POST)
    public void buildingRobustPortfolioCompletedBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataBuildingRobustPortfolioCompleted webData = new WebDataBuildingRobustPortfolioCompleted(id);
        webData.setAction(Action.PREVIOUS);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    /***/
    /****/
    //CALCULATING_STATISTIC
    @RequestMapping(value = "/calculating_statistic/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void calculatingStatisticPOST(@PathVariable Long id, @RequestBody Job addSelectedStocksRequest) {
        Job job = jobRepository.findOne(id);
        //job.setSelectedStocks(addSelectedStocksRequest.getSelectedStocks());
        job = jobRepository.save(job);

        WebDataBuildingRobustPortfolioCompleted webData = new WebDataBuildingRobustPortfolioCompleted(job.getId());
        //webData.setSelectedStocks(job.getSelectedStocks());

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/calculating_statistic_in_progress/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void calculatingStatisticInProgressPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataCalculatingStatisticInProgress webData = new WebDataCalculatingStatisticInProgress(id);
        webData.setAction(Action.REFRESH);

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/calculating_statistic_in_progress/{id}", method = RequestMethod.GET)
    public Page calculatingStatisticInProgressGET(@PathVariable Long id) {
        WebPageCalculatingStatisticInProgress page = new WebPageCalculatingStatisticInProgress(id);
        return page.show();
    }

    @RequestMapping(value = "/calculating_statistic_completed/{id}", method = RequestMethod.GET)
    public Page calculatingStatisticCompletedGET(@PathVariable Long id) {
        WebPageCalculatingStatisticCompleted page = new WebPageCalculatingStatisticCompleted(id, jobRepository, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/calculating_statistic_failed/{id}", method = RequestMethod.GET)
    public Page calculatingStatisticFailedGET(@PathVariable Long id) {
        WebPageCalculatingStatisticFailed page = new WebPageCalculatingStatisticFailed(id, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/calculating_statistic_in_progress_back/{id}", method = RequestMethod.POST)
    public void calculatingStatisticInProgressBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataCalculatingStatisticInProgress webData = new WebDataCalculatingStatisticInProgress(id);
        webData.setAction(Action.INTERRUPT);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/calculating_statistic_in_progress_completed_back/{id}", method = RequestMethod.POST)
    public void calculatingStatisticCompletedBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataCalculatingStatisticCompleted webData = new WebDataCalculatingStatisticCompleted(id);
        webData.setAction(Action.PREVIOUS);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    /***/

}
