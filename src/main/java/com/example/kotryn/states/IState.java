package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context.Context;

public interface IState {
    void redirectToWebPage(Context context, MainController controller);
    //void handle(Context context, IWebData webData);
}
