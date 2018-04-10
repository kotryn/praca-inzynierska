package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context.Context;

public class StateEstimatingWorstCaseDistributionsSetup extends StateBase implements IState {

    @Override
    public void redirectToWebPage(Context context, MainController controller) {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " not yet implemented");
    }

    /*@Override
    public void handle(Context context, IWebData webData) {
        throw new UnsupportedOperationException(getClass().getSimpleName() + " not yet implemented");
    }*/
}
