package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Arrays;
import java.util.List;

public class Body {

    @JsonView(View.Summary.class)
    private List<Entity> entities;

    public Body(Entity... entities) {
        this.entities = Arrays.asList(entities);
    }

    public Body(List<Entity> entities) {
        this.entities = entities;
    }
}
