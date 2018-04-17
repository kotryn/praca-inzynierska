package com.example.kotryn.states;

import com.example.kotryn.controller.MainController;
import com.example.kotryn.entity.Context;
import com.example.kotryn.web.data.IWebData;

public interface IState {
    String redirectToWebPage(Context context, MainController controller);
    void handle(Context context, IWebData webData);
}
