package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.processes.ProcessType;
import com.example.kotryn.repository.ContextRepository;
import com.example.kotryn.repository.ProcessDescriptorRepository;
import com.example.kotryn.web.data.IWebData;
import com.example.kotryn.web.data.WebDataEstimatingGrowthStocksSetup;

public class StateEstimatingGrowthStocksSetup extends StateBase implements IState {

    private ContextRepository contextRepository;
    private ProcessDescriptorRepository processDescriptorRepository;

    public StateEstimatingGrowthStocksSetup(ContextRepository contextRepository, ProcessDescriptorRepository processDescriptorRepository) {
        this.contextRepository = contextRepository;
        this.processDescriptorRepository = processDescriptorRepository;
    }

    @Override
    public String redirectToWebPage(Context context, MainController controller) {
        return "estimating_growth_stocks/"+context.getJobId();
    }

    @Override
    public void handle(Context context, IWebData webData) {
        WebDataEstimatingGrowthStocksSetup input = getInput(webData);
        switch (input.getAction()) {
            case NEXT:
                createProcessDescriptorAndSave(ProcessType.ESTIMATING_GROWTH_STOCKS, input.getJobId(), processDescriptorRepository);
                moveToNextStateAndSave(State.ESTIMATING_GROWTH_STOCKS_IN_PROGRESS, context, contextRepository);
                startProcess(input.getJobId());
                break;
            case PREVIOUS:
                moveToNextStateAndSave(State.ESTIMATING_WORST_CASE_DISTRIBUTIONS_COMPLETED, context, contextRepository);
                break;
            default:
                throw new RuntimeException("Undefined action");
        }
    }
}
