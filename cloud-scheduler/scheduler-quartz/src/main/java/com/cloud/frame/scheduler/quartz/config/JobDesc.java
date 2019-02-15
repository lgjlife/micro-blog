package com.cloud.frame.scheduler.quartz.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-21 02:57
 **/

@ToString
@Setter
@Getter
public class JobDesc implements Serializable {

    private String jobClass;
    private String description;

    public JobDesc() {
    }

    public JobDesc(String jobClass, String description) {
        this.jobClass = jobClass;
        this.description = description;
    }


}
