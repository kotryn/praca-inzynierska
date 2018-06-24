package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Arrays;
import java.util.List;

public class Navbar {

    @JsonView(View.Summary.class)
    private List<Entity> entities;

    public Navbar(Entity... entities) {
        this.entities = Arrays.asList(entities);
    }

    public Navbar(List<Entity> entities) {
        this.entities = entities;
    }
}
