package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.entity.Job;
import com.example.kotryn.processes.ProcessType;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.JobRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataObtainingStocks;

public class StateObtainingStocks extends StateBase implements IState {

    private JobRepository jobRepository;
    private ContextRepository contextRepository;
    private ProcessDescriptorRepository processDescriptorRepository;

    public StateObtainingStocks(JobRepository jobRepository, ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.jobRepository = jobRepository;
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "calculating_sample_count_in_progress/"+context.getJobId();
    }

    private void saveStocks(WebDataObtainingStocks input) {
        Job job = jobRepository.getOne(input.getJobId());
        job.setSelectedStocks(input.getSelectedStocks());
        jobRepository.saveAndFlush(job);
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataObtainingStocks input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                saveStocks(input);
                createProcessDescriptorAndSave(ProcessType.CALCULATING_SAMPLE_COUNT, input.getJobId(),
                        processDescriptorRepository);
                // always set the state first
                moveToNextStateAndSave(State.CALCULATING_SAMPLE_COUNT_IN_PROGRESS, context, contextRepository);
                // then start the process
                // (launching should be done in a separate thread so as not to block UI)
                startProcess(input.getJobId());
                break;
            case PREVIOUS:
                //saveSelectedStocks(input);
                moveToNextStateAndSave(State.OBTAINING_RETURN_PERIOD, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
