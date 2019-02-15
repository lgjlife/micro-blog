package com.cloud.frame.center.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContext;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import com.netflix.eureka.registry.PeerAwareInstanceRegistryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListIterator;

/**
 * @program: top-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-11-18 18:06
 **/

@Service
public class CenterService {

    private static final Logger log = LoggerFactory.getLogger(CenterService.class);

    @Autowired
    private EurekaClient eurekaClient;



    public String getServiceList(){

        log.info("访问 CenterService--->getServiceList");
        Applications applications =  eurekaClient.getApplications();
        ListIterator<Application> listIterator = applications.getRegisteredApplications().listIterator();

        PeerAwareInstanceRegistryImpl registry = (PeerAwareInstanceRegistryImpl)this.getRegistry();

        Applications appss = registry.getApplications();// applications.getRegisteredApplications();
        List<Application> apps = appss.getRegisteredApplications();
        log.info("list len = " + apps.size());
        for(Application app:apps){
            log.info("app name = " + app.getName() );

            List<InstanceInfo> instanceInfos = app.getInstances();

            for(InstanceInfo  instanceInfo:instanceInfos ){
               ;
                log.info("port = " +  instanceInfo.getPort()
                        + "  HomePageUrl = " + instanceInfo.getHomePageUrl()
                        + "   IPAddr = " + instanceInfo.getIPAddr()
                );
            }

        }
            return  "OK";
    }


    private PeerAwareInstanceRegistry getRegistry() {
        return this.getServerContext().getRegistry();
    }

    private EurekaServerContext getServerContext() {
        return EurekaServerContextHolder.getInstance().getServerContext();
    }




}
