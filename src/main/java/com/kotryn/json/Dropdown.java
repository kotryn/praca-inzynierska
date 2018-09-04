package com.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class Dropdown {
    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private String name;

    @JsonView(View.Summary.class)
    private List<Entity> entities;

    public Dropdown(String type, String name, List<Entity> entities) {
        this.type = type;
        this.name = name;
        this.entities = entities;
    }
}

