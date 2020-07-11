package com.microblog.test.jmx.config;

import lombok.extern.slf4j.Slf4j;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@Slf4j
public class JmxTask {

    public static void init(){

       try{

           MBeanServer server = ManagementFactory.getPlatformMBeanServer();
           ObjectName name = new ObjectName("serverInfoMBean:name=serverInfo");
           server.registerMBean(new ServerInfo(), name);
       }
       catch(Exception ex){
           log.error(ex.getMessage());
       }

        run();
    }

    public static void run(){

        Runnable task = new Runnable(){
            @Override
            public void run() {
                while (true){
                    DataUtil.count++;
                    try{

                        Thread.sleep(1000);
                    }
                    catch(Exception ex){
                        log.error(ex.getMessage());
                    }
                }
            }

        };

        new Thread(task).start();

    }
}
