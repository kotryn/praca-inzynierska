package com.kotryn.states;

import com.kotryn.controller.MainController;
import com.kotryn.entity.Context;
import com.kotryn.web.data.IWebData;

public interface IState {
    String redirectToWebPage(Context context, MainController controller);
    void handle(Context context, IWebData webData);
}
