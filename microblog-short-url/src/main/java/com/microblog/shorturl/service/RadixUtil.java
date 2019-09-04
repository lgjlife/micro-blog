package com.microblog.shorturl.service;


import lombok.extern.slf4j.Slf4j;

import static java.lang.Math.pow;

/**
 *功能描述
 * @author lgj
 * @Description  进制转换工具类.支持最大62进制
 * @date 9/4/19
*/

@Slf4j
public class RadixUtil {

    /*
     *功能描述
     * @author lgj
     * @Description 十进制转换成其他进制
     * @date 9/4/19
     * @param: tenRadix: 十进制数，radix：需要转换的进制
     * @return:  java.lang.String
     *
    */
    public static String numberToString(long tenRadix, int radix)
    {
        // 进制编码支持10+26+26=62进制
        String code = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder buf = new StringBuilder();
        int remainder = 0;
        while (tenRadix != 0)
        {
            remainder = (int)(tenRadix % radix);// 求余数
            tenRadix = tenRadix / radix;// 除以基数
            buf.append(code.charAt(remainder));// 保存余数，记得要倒叙排列
        }
        buf.reverse();// 倒叙排列
        return buf.toString();
    }

    /*
     *功能描述
     * @author lgj
     * @Description  其他进制转换成十进制
     * @date 9/4/19
     * @param: otherRadixStr:其他进制数  ；radix：需要转换的进制
     * @return:  int
     *
    */
    public static int stringToNumber(String otherRadixStr, int radix)
    {
        StringBuilder stringBuilder = new StringBuilder(otherRadixStr);
        stringBuilder.reverse();// 反转字符，为了把权重最大的放在最右边，便于下面从左到右遍历，根据下标求权重。
        // 如果不反转，从右向左遍历(从字符串下标大的一端)也可以
        char bitCh;
        int result = 0;
        for (int i = 0; i < stringBuilder.length(); i++)
        {
            bitCh = stringBuilder.charAt(i);
            if (bitCh >= '0' && bitCh <= '9')
            {
                // '0'对应的ASCII码整数：48
                result += (int) (bitCh - '0') * pow(radix, i);
            } else if (bitCh >= 'A' && bitCh <= 'Z')
            {
                // 减去'A'的ASCII码值(65),再加上10
                result += ((int) (bitCh - 'A') + 10) * pow(radix, i);
            } else if (bitCh >= 'a' && bitCh <= 'z')
            {
                // 减去'a'的ASCII码值(97),再加上10
                result += ((int) (bitCh - 'a') + 10) * pow(radix, i);
            }
        }
        return result;
    }


    public static void main(String args[]){

        int number = 100000000;
        int radix = 36;
        String result = RadixUtil.numberToString(number,radix);
        log.info("[{}]转换成[{}]进制为[{}]",number,radix,result);

       int origin =  RadixUtil.stringToNumber(result,radix);

       log.info("原始数据[{}]",origin);


    }

}
