package com.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class Entity<T> {

    @JsonView(View.Summary.class)
    private T entity;

    public Entity(T entity) {
        this.entity = entity;
    }
}
