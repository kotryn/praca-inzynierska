package com.example.kotryn.entity;

import javax.persistence.*;
import java.util.List;

public class JobDTO {

    @ElementCollection
    private List<String> checkbox;


    public JobDTO(){
    }

    public List<String> getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(List<String> checkbox) {
        this.checkbox = checkbox;
    }
}
