package com.microblog.points.service.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class SignHistoryUtilTest {

    @Test
    public void sign() throws Exception{

        String signHistory = SignHistoryUtil.defaultsignHistory();


        int signMonth = 1;
        int signDay = 1;
        int dayOfYear0 = LocalDate.of(2019,signMonth,signDay).getDayOfYear();
        log.info("对2019-"+ signMonth + "-"+signDay+",第[" + dayOfYear0 + "]天签到！");
        signHistory = SignHistoryUtil.sign(signHistory,dayOfYear0);


        signMonth = 1;
        signDay = 6;
        int dayOfYear1 = LocalDate.of(2019,signMonth,signDay).getDayOfYear();
        log.info("对2019-"+ signMonth + "-"+signDay+",第[" + dayOfYear1 + "]天签到！");
        signHistory = SignHistoryUtil.sign(signHistory,dayOfYear1);



        byte[] data = SignHistoryUtil.signHistoryToByte(signHistory);


        System.out.println();

        for(int i = 0; i< 10; i++){
            log.info("第[{}]天是否签到:{}",i,SignHistoryUtil.isSign(data,i));
        }

        System.out.println();
        log.info("第[{}]天是否签到:{}",dayOfYear0,SignHistoryUtil.isSign(data,dayOfYear0));
        log.info("第[{}]天是否签到:{}",dayOfYear1,SignHistoryUtil.isSign(data,dayOfYear1));

        log.info("第[{}]天是否签到:{}",15,SignHistoryUtil.isSign(data,16));


        log.info("签到结果:");
        log.info("数组长度 = " + data.length);
        for(int i = 0; i< data.length; i++){

            System.out.print(data[i]);
        }
        System.out.println();
        log.info("signHistory 长度:[{}],VALUE=[{}]",signHistory.length(),signHistory);
        List<Integer> signDayOfMonths = SignHistoryUtil.getSignHistoryByMonth(signHistory,2019,signMonth);

        log.info("第[{}]月签到记录[{}]",signMonth,signDayOfMonths);
    }

    @Test
    public void getMaxContinuitySignDay()throws Exception {

        String signHistory = SignHistoryUtil.defaultsignHistory();

        int curMonth = LocalDate.now().getMonth().getValue();
        int curDay = LocalDate.now().getDayOfMonth();

        int signDayCount = 0;
        int maxCount = 5;
        while(signDayCount < maxCount){
            LocalDate localDate  = LocalDate.of(2019,curMonth,curDay-signDayCount);
            log.info("[{}]签到",localDate);
            signHistory = SignHistoryUtil.sign(signHistory,localDate.getDayOfYear());
            signDayCount++;
        }

        LocalDate localDate  = LocalDate.of(2019,curMonth,curDay-signDayCount-1);
        log.info("[{}]签到",localDate);
        signHistory = SignHistoryUtil.sign(signHistory,localDate.getDayOfYear());


       int  maxContinuitySignDay = SignHistoryUtil.getMaxContinuitySignDay(signHistory);
        log.info("连续签到[{}]天！",maxContinuitySignDay);



    }
}