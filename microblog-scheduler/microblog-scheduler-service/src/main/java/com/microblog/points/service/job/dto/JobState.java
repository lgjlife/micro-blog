package com.microblog.points.service.job.dto;


import lombok.Data;

@Data
public class JobState {

    private String key;
    private String name;
    private String group;
    private String state;

    public String getKey(){
        return group+"."+name;
    }
}
