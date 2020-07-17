package com.microblog.encry;

import java.util.Base64;

public class Base64Util {

    public static void main(String args[]){

        for(int i = 0; i< 100; i++){
            System.out.print(i + "-" + Integer.toHexString(i) + "  ");
        }

        System.out.println();

        byte[] data = new byte[]{0,1,2,3,4,5};

        printByte(data);

        byte[] encodeDate = byteToBase64(data);

        printByte(encodeDate);

        byte[] decodeData = base64ToByte(encodeDate);

        printByte(decodeData);

    }

    public static byte[] byteToBase64(byte[] data){

        Base64.Encoder encoder =  Base64.getEncoder();

        return encoder.encode(data);

    }

    public static byte[] base64ToByte(byte[] data){
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(data);
    }

    public static  void printByte(byte[] data){


        for(int  i= 0; i < data.length; i++){

            System.out.print(data[i]);
        }
        System.out.println();

        System.out.println(new String(data));

        System.out.println();





    }


}
