package com.demo.log;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyThread extends  Thread{



    @Override
    public void run() {

        int i = 0;
        while (true){
            System.out.println("loging......");
            log.info("MyThread ..." + i ++);
            try{
                Thread.sleep(4000);
            }
            catch(Exception ex){
                log.error(ex.getMessage());
            }

        }
    }
}