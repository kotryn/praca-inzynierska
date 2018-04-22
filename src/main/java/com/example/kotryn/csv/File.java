package com.example.kotryn.csv;

public class File  {

    public static String getFile(String file) {
        switch(file) {
            case "FIRST_STOCK":
                return "/home/kotryn/IdeaProjects/praca-inzynierska/src/main/resources/static/dane2.csv";
            default:
                throw new RuntimeException("Unknown file");
        }
    }
}