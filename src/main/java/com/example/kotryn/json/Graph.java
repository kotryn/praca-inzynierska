package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;
import java.util.Map;

public class Graph {
    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private String[] name;

    @JsonView(View.Summary.class)
    private List<Map<String,Double>> data;

    public Graph(String type, String[] name, List<Map<String,Double>> data) {
        this.type = type;
        this.name = name;
        this.data = data;
    }
}
