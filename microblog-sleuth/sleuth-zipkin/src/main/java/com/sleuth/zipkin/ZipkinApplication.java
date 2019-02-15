package com.sleuth.zipkin;

/**
 * @program: cloud-parent
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-12-13 16:31
 **/


public class ZipkinApplication {

    public static void main(String[] args) {


        try {

            Process process ;// =  Runtime.getRuntime().exec("ls -l");
            byte[] in  = new byte[100];
            int len = 0;

            /*Process process =  Runtime.getRuntime().exec("ls -l");
            byte[] in  = new byte[100];
            int len = 0;*/
           /* while((len = process.getInputStream().read(in)) != -1){

                System.out.print(new String(in));
            }*/

            pwd();
            System.out.println("-------------------cd zipkin-server");




            process =  Runtime.getRuntime().exec(new String[] {"/bin/sh", null, "cd zipkin-server"});
            while((len = process.getInputStream().read(in)) != -1){

                System.out.print(new String(in));
            }
            pwd();
            System.out.println("-------------------ls -l");
            process =  Runtime.getRuntime().exec("ls -l");
            while((len = process.getInputStream().read(in)) != -1){

                System.out.print(new String(in));
            }

            System.out.println("-------------------./zipkin-start.sh");
            process =  Runtime.getRuntime().exec("./zipkin-start.sh");
            while((len = process.getInputStream().read(in)) != -1){

                System.out.print(new String(in));
            }




        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }

    }


    static  void pwd(){

        Process process ;// =  Runtime.getRuntime().exec("ls -l");
        byte[] in  = new byte[100];
        int len = 0;
        try {
            process =  Runtime.getRuntime().exec("pwd");
            String currentPath = "";
            while((len = process.getInputStream().read(in)) != -1){

                System.out.print(new String(in));
                currentPath += new String(in);
            }
            currentPath =   currentPath.trim();
            System.out.println("currentPath = " + currentPath);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }

    }
}
