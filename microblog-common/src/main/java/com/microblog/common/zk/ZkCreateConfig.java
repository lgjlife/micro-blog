package com.microblog.common.zk;


import lombok.Builder;
import lombok.Data;
import org.apache.curator.framework.imps.Backgrounding;
import org.apache.zookeeper.CreateMode;


@Data
@Builder
public class ZkCreateConfig {

    private String path;
    private CreateMode createMode;
    private Backgrounding backgrounding;

}
