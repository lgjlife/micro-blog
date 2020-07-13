package com.microblog.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/gateway/eureka")
public class EurekaController {

    //导入的包是import org.springframework.cloud.client.discovery.DiscoveryClient;
    @Autowired
    private DiscoveryClient discoveryClient;


    @RequestMapping("/list")
    public List<List<ServiceInstance>> getServiceList(){

        List<List<ServiceInstance>> servicesList = new ArrayList<>();
        //获取服务名称
        List<String> serviceNames = discoveryClient.getServices();
        for (String serviceName : serviceNames) {
            //获取服务中的实例列表
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
            servicesList.add(serviceInstances);
        }

        return servicesList;

    }
}
