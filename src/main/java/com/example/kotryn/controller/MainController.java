package com.example.kotryn.controller;

import com.example.kotryn.entity.*;
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

    public MainController(JobRepository jobRepository, ProcessDescriptorRepository processDescriptorRepository, ContextRepository contextRepository, SectorRepository sectorRepository, WorstCaseDistributionSectorRepository worstCaseDistributionSectorRepository, GrowthStockSectorRepository growthStockSectorRepository) {
        this.jobRepository = jobRepository;
        this.processDescriptorRepository = processDescriptorRepository;
        this.contextRepository = contextRepository;
        AbstractProcessFactory.setFactory(new ProcessFactory(jobRepository, processDescriptorRepository, sectorRepository, worstCaseDistributionSectorRepository, growthStockSectorRepository));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void prevPage() {
        error = null;
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
        WebPagePromptUser page = new WebPagePromptUser();
        return page.show();
    }

    @RequestMapping(value = "/prompt_user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void promptUserPOST() {
        url = "/prompt_user";
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
                case ESTIMATING_GROWTH_STOCKS_IN_PROGRESS:
                    WebDataEstimatingGrowthStocksInProgress webData4 =
                            new WebDataEstimatingGrowthStocksInProgress(requestJob.getId());
                    webData4.setAction(Action.REFRESH);
                    processJob(webData4);
                    break;
                case ESTIMATING_WORST_CASE_COPULA_IN_PROGRESS:
                    WebDataEstimatingWorstCaseCopulaInProgress webData6 =
                            new WebDataEstimatingWorstCaseCopulaInProgress(requestJob.getId());
                    webData6.setAction(Action.REFRESH);
                    processJob(webData6);
                    break;
                case BUILDING_ROBUST_PORTFOLIO_IN_PROGRESS:
                    WebDataBuildingRobustPortfolioInProgress webData7 =
                            new WebDataBuildingRobustPortfolioInProgress(requestJob.getId());
                    webData7.setAction(Action.REFRESH);
                    processJob(webData7);
                    break;
                case CALCULATING_STATISTIC_IN_PROGRESS:
                    WebDataCalculatingStatisticInProgress webData8 =
                            new WebDataCalculatingStatisticInProgress(requestJob.getId());
                    webData8.setAction(Action.REFRESH);
                    processJob(webData8);
                    break;
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

    @RequestMapping(value = "/delete_job", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void deleteJobPOST() {
        url = "/delete_job";
    }

    @RequestMapping(value = "/delete_job", method = RequestMethod.GET)
    public Page deleteJobGET() {
        WebPageDeleteJob page = new WebPageDeleteJob(error);
        return page.show();
    }

    @RequestMapping(value = "/delete_job", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void jobsDELETE(@RequestBody Job requestJob) {
        if(requestJob.getId() == null || !contextRepository.exists(requestJob.getId())){
            error = "Job with id "+requestJob.getId()+" not exist";
            this.url = "/delete_job";
        }else{
            Long id = requestJob.getId();
            Context context = contextRepository.getOne(id);
            error = null;
            switch (context.getState()) {
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
                case ESTIMATING_GROWTH_STOCKS_IN_PROGRESS:
                    WebDataEstimatingGrowthStocksInProgress webData4 = new WebDataEstimatingGrowthStocksInProgress(id);
                    webData4.setAction(Action.INTERRUPT);
                    processJob(webData4);
                    break;
                case ESTIMATING_WORST_CASE_COPULA_IN_PROGRESS:
                    WebDataEstimatingWorstCaseCopulaInProgress webData6 = new WebDataEstimatingWorstCaseCopulaInProgress(id);
                    webData6.setAction(Action.INTERRUPT);
                    processJob(webData6);
                    break;
                case BUILDING_ROBUST_PORTFOLIO_IN_PROGRESS:
                    WebDataBuildingRobustPortfolioInProgress webData7 = new WebDataBuildingRobustPortfolioInProgress(id);
                    webData7.setAction(Action.INTERRUPT);
                    processJob(webData7);
                    break;
                case CALCULATING_STATISTIC_IN_PROGRESS:
                    WebDataCalculatingStatisticInProgress webData8 = new WebDataCalculatingStatisticInProgress(id);
                    webData8.setAction(Action.INTERRUPT);
                    processJob(webData8);
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
        WebPageSearchingForStocksCompleted page = new WebPageSearchingForStocksCompleted(id, jobRepository, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/stocks_search_failed/{id}", method = RequestMethod.GET)
    public Page searchingForStocksFailedGET(@PathVariable Long id) {
        WebPageSearchingForStocksFailed page = new WebPageSearchingForStocksFailed(id, processDescriptorRepository);
        return page.show();
    }

    @RequestMapping(value = "/stocks_search_in_progress/{id}", method = RequestMethod.GET)
    public Page searchingForStocksInProgressGET(@PathVariable Long id) {
        WebPageSearchingForStocksInProgress page = new WebPageSearchingForStocksInProgress(id);
        return page.show();
    }

    @RequestMapping(value = "/calculating_sample_count_setup/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void calculatingSampleCountSetupPOST(@PathVariable Long id, @RequestBody JobDTO jobDTO) {
        Job job = jobRepository.findOne(id);
        job.setSelectedStocks(jobDTO.getCheckbox());
        job = jobRepository.save(job);

        WebDataSearchingForStocksCompleted webData = new WebDataSearchingForStocksCompleted(job.getId());
        webData.setSelectedStocks(job.getSelectedStocks());

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/calculating_sample_count_setup/{id}", method = RequestMethod.GET)
    public Page calculatingSampleCountSetupGET(@PathVariable Long id) {
        WebPageCalculatingSampleCountSetup page = new WebPageCalculatingSampleCountSetup(jobRepository, id);
        return page.show();
    }

    @RequestMapping(value = "/calculating_sample_count_setup_back/{id}", method = RequestMethod.POST)
    public void calculatingSampleCountSetupBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataCalculatingSampleCountSetup webData = new WebDataCalculatingSampleCountSetup(id);
        webData.setAction(Action.PREVIOUS);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/calculating_sample_count/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void calculatingSampleCountPOST(@PathVariable Long id, @RequestBody Job jobRequest) {
        Job job = jobRepository.findOne(id);
        job.setStartInSampleDate(jobRequest.getStartInSampleDate());
        job.setEndInSampleDate(jobRequest.getEndInSampleDate());
        job.setStartOutOfSampleDate(jobRequest.getStartOutOfSampleDate());
        job.setEndOutOfSampleDate(jobRequest.getEndOutOfSampleDate());
        job.setPeriodicity(jobRequest.getPeriodicity());
        job = jobRepository.save(job);

        WebDataCalculatingSampleCountSetup webData = new WebDataCalculatingSampleCountSetup(job.getId());
        webData.setStartInSampleDate(job.getStartInSampleDate());
        webData.setEndInSampleDate(job.getEndInSampleDate());
        webData.setStartOutOfSampleDate(job.getStartOutOfSampleDate());
        webData.setEndOutOfSampleDate(job.getEndOutOfSampleDate());
        webData.setPeriodicity(job.getPeriodicity());

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
    @ResponseStatus(HttpStatus.OK)
    public void estimatingWorstCaseDistributionsSetupPOST(@PathVariable Long id) {
        WebDataCalculatingSampleCountCompleted webData = new WebDataCalculatingSampleCountCompleted(id);

        processJob(webData);
        url = this.jobsGET(id);
    }

    @RequestMapping(value = "/estimating_worst_case_distributions/{id}", method = RequestMethod.GET)
    public Page estimatingWorstCaseDistributionsGET(@PathVariable Long id) {
        WebPageEstimatingWorstCaseDistributionsSetup page = new WebPageEstimatingWorstCaseDistributionsSetup(jobRepository, id);
        return page.show();
    }

    @RequestMapping(value = "/estimating_worst_case_distributions/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void estimatingWorstCaseDistributionsPOST(@PathVariable Long id, @RequestBody Job jobRequest) {
        Job job = jobRepository.findOne(id);
        job.setWindowSize(jobRequest.getWindowSize());
        job.setGrowthRate(jobRequest.getGrowthRate());
        job = jobRepository.save(job);
        WebDataEstimatingWorstCaseDistributionsSetup webData = new WebDataEstimatingWorstCaseDistributionsSetup(id);
        //webData.setGrowthRate(job.getGrowthRate());

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

    /****************************/
    @RequestMapping(value = "/estimating_growth_stocks_setup/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void estimatingGrowthStocksSetupPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);

        WebDataEstimatingWorstCaseDistributionsCompleted webData = new WebDataEstimatingWorstCaseDistributionsCompleted(job.getId());

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/estimating_growth_stocks/{id}", method = RequestMethod.GET)
    public Page estimatingGrowthStocksGET(@PathVariable Long id) {
        WebPageEstimatingGrowthStocksSetup page = new WebPageEstimatingGrowthStocksSetup(jobRepository, id);
        return page.show();
    }

    @RequestMapping(value = "/estimating_growth_stocks/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void estimatingGrowthStocksPOST(@PathVariable Long id, @RequestBody Job jobRequest) {
        Job job = jobRepository.findOne(id);

        job.setMaxNumberSector(jobRequest.getMaxNumberSector());
        job.setMaxNumberIndustry(jobRequest.getMaxNumberIndustry());
        job.setMaxCoefficient(jobRequest.getMaxCoefficient());
        job = jobRepository.save(job);

        WebDataEstimatingGrowthStocksSetup webData = new WebDataEstimatingGrowthStocksSetup(job.getId());
        webData.setMaxNumberSector(job.getMaxNumberSector());
        webData.setMaxNumberIndustry(job.getMaxNumberIndustry());
        webData.setMaxCoefficient(job.getMaxCoefficient());

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

    @RequestMapping(value = "/estimating_growth_stocks_setup_back/{id}", method = RequestMethod.POST)
    public void estimatingGrowthStocksSetupBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataEstimatingGrowthStocksSetup webData = new WebDataEstimatingGrowthStocksSetup(id);
        webData.setAction(Action.PREVIOUS);
        processJob(webData);
        url = this.jobsGET(job.getId());
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
    /******************************/

    @RequestMapping(value = "/estimating_worst_case_copula_setup/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void estimatingWorstCaseCopulaSetupPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataEstimatingGrowthStocksCompleted webData = new WebDataEstimatingGrowthStocksCompleted(job.getId());
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/estimating_worst_case_copula/{id}", method = RequestMethod.GET)
    public Page estimatingWorstCaseCopulaGET(@PathVariable Long id) {
        WebPageEstimatingWorstCaseCopulaSetup page = new WebPageEstimatingWorstCaseCopulaSetup(jobRepository, id);
        return page.show();
    }

    @RequestMapping(value = "/estimating_worst_case_copula_setup_back/{id}", method = RequestMethod.POST)
    public void estimatingWorstCaseCopulaSetupBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataEstimatingWorstCaseCopulaSetup webData = new WebDataEstimatingWorstCaseCopulaSetup(id);
        webData.setAction(Action.PREVIOUS);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/estimating_worst_case_copula/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void estimatingWorstCaseCopulaPOST(@PathVariable Long id, @RequestBody Job jobRequest) {
        Job job = jobRepository.findOne(id);
        job.setCopulaWindowSize(jobRequest.getCopulaWindowSize());
        job.setCopulaType(jobRequest.getCopulaType());
        job = jobRepository.save(job);

        WebDataEstimatingWorstCaseCopulaSetup webData = new WebDataEstimatingWorstCaseCopulaSetup(job.getId());
        webData.setCopulaWindowSize(job.getCopulaWindowSize());
        webData.setCopulaType(job.getCopulaType());

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

    @RequestMapping(value = "/estimating_worst_case_copula_completed_back/{id}", method = RequestMethod.POST)
    public void estimatingWorstCaseCopulaCompletedBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataEstimatingWorstCaseCopulaCompleted webData = new WebDataEstimatingWorstCaseCopulaCompleted(id);
        webData.setAction(Action.PREVIOUS);
        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    /**/

    @RequestMapping(value = "/building_robust_portfolio_setup/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void buildingRobustPortfolioSetupPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);

        WebDataEstimatingWorstCaseCopulaCompleted webData = new WebDataEstimatingWorstCaseCopulaCompleted(job.getId());

        processJob(webData);
        url = this.jobsGET(job.getId());
    }

    @RequestMapping(value = "/building_robust_portfolio/{id}", method = RequestMethod.GET)
    public Page buildingRobustPortfolioGET(@PathVariable Long id) {
        WebPageBuildingRobustPortfolioSetup page = new WebPageBuildingRobustPortfolioSetup(jobRepository, id);
        return page.show();
    }

    @RequestMapping(value = "/building_robust_portfolio/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void buildingRobustPortfolioPOST(@PathVariable Long id, @RequestBody JobDTO jobDTO) {
        Job job = jobRepository.findOne(id);
        //job.setSelectedWorstCaseCopula(jobDTO.getCheckbox());
        //job = jobRepository.save(job);

        WebDataBuildingRobustPortfolioSetup webData = new WebDataBuildingRobustPortfolioSetup(job.getId());
       // webData.setSelectedWorstCaseCopula(job.getSelectedWorstCaseCopula());

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

    @RequestMapping(value = "/building_robust_portfolio_setup_back/{id}", method = RequestMethod.POST)
    public void buildingRobustPortfolioSetupBackPOST(@PathVariable Long id) {
        Job job = jobRepository.findOne(id);
        WebDataBuildingRobustPortfolioSetup webData = new WebDataBuildingRobustPortfolioSetup(id);
        webData.setAction(Action.INTERRUPT);
        processJob(webData);
        url = this.jobsGET(job.getId());
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

    @RequestMapping(value = "/calculating_statistic/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void calculatingStatisticPOST(@PathVariable Long id, @RequestBody JobDTO jobDTO) {
        Job job = jobRepository.findOne(id);
        job.setSelectedRobustPortfolio(jobDTO.getCheckbox());
        job = jobRepository.save(job);

        WebDataBuildingRobustPortfolioCompleted webData = new WebDataBuildingRobustPortfolioCompleted(job.getId());
        webData.setSelectedRobustPortfolio(job.getSelectedRobustPortfolio());

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
}
