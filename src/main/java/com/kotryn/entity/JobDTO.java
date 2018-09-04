package com.kotryn.entity;

import javax.persistence.ElementCollection;
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
