package com.example.kotryn.entity;

public class Application {
    private String url;

    public Application(String url) {
        this.url = url;
    }

    public String getAppUrl() {
        return url;
    }

    public void setAppUrl(String url) {
        this.url = url;
    }
}
