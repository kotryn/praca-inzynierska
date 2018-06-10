package com.example.kotryn.json;

import com.fasterxml.jackson.annotation.JsonView;

public class RadioButton {
    @JsonView(View.Summary.class)
    private String type;

    @JsonView(View.Summary.class)
    private String value;

    @JsonView(View.Summary.class)
    private String[] values;

    @JsonView(View.Summary.class)
    private String[] names;

    public RadioButton(String type, String[] values, String[] names, String value) {
        this.type = type;
        this.values = values;
        this.names = names;
        this.value = value;
    }
}