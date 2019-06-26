package com.microblog.points.service.strategy;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *功能描述
 * @author lgj
 * @Description  签到历史工具类
 * @date 6/27/19
*/
@Slf4j
public class SignHistoryExcutor {


    public static String sign(String signHistory,int dayOfYear) throws IOException {

        byte[] decodeResult = signHistoryToByte(signHistory);
        int index = dayOfYear / 8;
        int offset = dayOfYear % 8;
        byte data = decodeResult[index];
        data =  (byte)(data|(1 << offset));
        decodeResult[index] = data ;

        String encodeResult =  new BASE64Encoder().encode(decodeResult);

        return encodeResult;

    }

    public static String defaultsignHistory(){
        byte[] encodeData = new byte[46];
        return new BASE64Encoder().encode(encodeData);
    }

    public static byte[] signHistoryToByte(String signHistory) throws IOException {
        return new BASE64Decoder().decodeBuffer(signHistory);
    }

    public static List<Integer> getSignHistoryByMonth(String signHistory, int year, int month)throws IOException{

        LocalDate localDate =  LocalDate.of(year,month,1);
        int start = localDate.getDayOfYear();

        int dayOfMonth = localDate.lengthOfMonth();
        log.info("dayOfMonth = {}",dayOfMonth);
        localDate = localDate.withDayOfMonth(dayOfMonth);
        int end = localDate.getDayOfYear();

        log.info("start={},end={}",start,end);
        Integer result = 0;

        byte[] data = signHistoryToByte(signHistory);

        List<Integer> signDayOfMonths = new ArrayList<>();

        int signDay = 0;
        for(int i = start; i< end ; i++){
            signDay++;
            if(isSign(data,i)){
                signDayOfMonths.add(signDay);
            }

        }

        return signDayOfMonths;

    }

    public static boolean isSign(byte[] data,int dayOfYear){

        int index = dayOfYear / 8;
        int offset = dayOfYear % 8;
        System.out.print(index+"-");
        int flag = data[index] & (1 << offset);

        return flag == 0?false:true;

    }


    public static void main(String args[]) throws Exception{
        String signHistory = defaultsignHistory();


        int month = 2;

        int dayOfYear0 = LocalDate.of(2019,month,1).getDayOfYear();
        System.out.println("对2019年的第[" + dayOfYear0 + "]天签到！");
        signHistory = sign(signHistory,dayOfYear0);
        int dayOfYear1 = LocalDate.of(2019,month,15).getDayOfYear();
        System.out.println("对2019年的第[" + dayOfYear1 + "]天签到！");
        signHistory = sign(signHistory,dayOfYear1);


        System.out.println("签到结果:");
        byte[] data = signHistoryToByte(signHistory);
        System.out.println("data.length = " + data.length);
        for(int i = 0; i< data.length; i++){

            System.out.print(data[i]);
        }

        System.out.println();

        log.info("第[{}]天是否签到:{}",dayOfYear0,isSign(data,dayOfYear0));
        log.info("第[{}]天是否签到:{}",dayOfYear1,isSign(data,dayOfYear1));

        log.info("第[{}]天是否签到:{}",15,isSign(data,16));


        List<Integer> signDayOfMonths = getSignHistoryByMonth(signHistory,2019,month);

        log.info("第[{}]月签到记录[{}]",month,signDayOfMonths);

       // getSignHistoryByMonth("",2019,2);

    }



}

