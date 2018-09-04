package com.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Arrays;
import java.util.List;

public class Header {

    @JsonView(View.Summary.class)
    private List<Entity> entities;

    public Header(Entity... entities) {
        this.entities = Arrays.asList(entities);
    }

    public Header(List<Entity> entities) {
        this.entities = entities;
    }
}
