package com.microblog.points.service.strategy;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

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
public class SignHistoryUtil {




    /**
     *功能描述
     * @author lgj
     * @Description   签到
     * @date 6/27/19
     * @param:   signHistory: 原始签到字符串
     *           dayOfYear: 需要签到的那一天(那年的第几天)
     * @return:  最新生成的签到历史字符串
     *
    */
    public static String sign(String signHistory,int dayOfYear) throws Exception {

        if(signHistory == null){
            throw new SignException("SignHistory can not be null!");
        }
        checkOutofDay(dayOfYear);

        byte[] decodeResult = signHistoryToByte(signHistory);
        int index = dayOfYear / 8;
        int offset = dayOfYear % 8;
        byte data = decodeResult[index];
        data =  (byte)(data|(1 << (7-offset)));
        decodeResult[index] = data ;

        String encodeResult =  new BASE64Encoder().encode(decodeResult);

        return encodeResult;

    }

    /**
     *功能描述
     * @author lgj
     * @Description 获取默认的签到历史字符串，也就是没有任何一天签到的签到历史
     * @date 6/27/19
     * @param:
     * @return:
     *
    */
    public static String defaultsignHistory(){
        byte[] encodeData = new byte[46];
        return new BASE64Encoder().encode(encodeData);
    }

    /**
     *功能描述
     * @author lgj
     * @Description 签到历史字符串转换成字节数组
     * @date 6/27/19
     * @param:
     * @return:
     *
    */
    public static byte[] signHistoryToByte(String signHistory) throws Exception {
        if(signHistory == null){
            throw new SignException("SignHistory can not be null!");
        }

        return new BASE64Decoder().decodeBuffer(signHistory);
    }

    /**
     *功能描述
     * @author lgj
     * @Description   获取某年某月的签到数据
     * @date 6/27/19
     * @param:    List<Integer>，如果当月的第一天和第三天签到，返回[1,3]
     * @return:
     *
    */
    public static List<Integer> getSignHistoryByMonth(String signHistory, int year, int month)throws Exception{

        if(signHistory == null){
            throw new SignException("SignHistory can not be null!");
        }
        checkOutofMonth(month);

        LocalDate localDate =  LocalDate.of(year,month,1);
        int start = localDate.getDayOfYear();

        int dayOfMonth = localDate.lengthOfMonth();
        //log.info("dayOfMonth = {}",dayOfMonth);
        localDate = localDate.withDayOfMonth(dayOfMonth);
        int end = localDate.getDayOfYear();

        //log.info("start={},end={}",start,end);
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

    /**
     *功能描述
     * @author lgj
     * @Description  测试某年的第n天是否签到
     * @date 6/27/19
     * @param:  true： 该天签到 false：没有签到
     * @return:
     *
    */
    public static boolean isSign(byte[] data,int dayOfYear) throws Exception{

        checkOutofDay(dayOfYear);
        int index = dayOfYear / 8;
        int offset = dayOfYear % 8;
        //System.out.print(index+"-");
        int flag = data[index] & (1 << (7-offset));

        return flag == 0?false:true;

    }


    /**
     *功能描述
     * @author lgj
     * @Description   获取过去几天的连续签到的次数
     * @date 6/27/19
     * @param:
     * @return:   今天 6.27 签到， 同时 6.26 ，6.25 也签到 ，6.24 未签到 ，返回 3
     *            今天 6.27 未签到， 同时 6.26 ，6.25 也签到 ，6.24 未签到 ，返回 2
     *
    */
    public static int  getMaxContinuitySignDay(String signHistory) throws Exception{

        int maxContinuitySignDay = 0;

        if(signHistory == null){
            throw new SignException("SignHistory can not be null!");
        }

        LocalDate localDate =LocalDate.now();
        int curDayOfYear = localDate.getDayOfYear();

        byte[] data = signHistoryToByte(signHistory);

        int checkDayOfYear = curDayOfYear-1;
        while (checkDayOfYear > 0){
            if(isSign(data,checkDayOfYear)){
                checkDayOfYear--;
                maxContinuitySignDay++;
            }
            else {
                break;
            }

        }

        if(isSign(data,curDayOfYear)){
            maxContinuitySignDay +=1;
        }
        return maxContinuitySignDay;
    }


    private static void checkOutofDay(int dayOfYear) throws Exception{
        LocalDate localDate =LocalDate.now();
        int maxDay = localDate.isLeapYear()?366:365;
        if((dayOfYear <= 0)&&( dayOfYear > maxDay)){
            throw  new SignException("The param dayOfYear["+dayOfYear+"] is out of [0-"+ maxDay+"]");
        }
    }
    private static void checkOutofMonth(int month) throws Exception{
        if((month <= 0)&&( month > 12)){
            throw  new SignException("The param month["+month+"] is out of [0-"+ 12+"]");
        }
    }



   public static void main(String args[]){

       byte[] data = new byte[1];
       for(int i = 0; i< 127; i++){
           data[0] = (byte)i;
           String str =  new String(data);
           System.out.println(data[0] + "---" + str);
       }

       data[0] = -13;
       String str =  new String(data);
       System.out.println(data[0] + "---" + str + "----");

       //获取2018/6/11 位于该年第几天
       LocalDate localDate  = LocalDate.of(2018,6,11);
       localDate.getDayOfYear();

       //获取今天 位于当年第几天
       LocalDate localDate1  = LocalDate.now();
       localDate.getDayOfYear();

   }



}

