package com.microblog.common.backup;


import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *功能描述 
 * @author lgj
 * @Description   数据库备份
 * @date 3/30/19
*/
@Slf4j
public class MysqlBackupUtil {



    //MySQL数据库所在服务器地址IP
    private  String hostIP  = "localhost";
    //进入数据库所需要的用户名
    private String userName = "root";
    //进入数据库所需要的密码
    private String password = "mysqlBackup";
    //数据库导出文件保存路径,本工程目录下的mysql/all
    private String savePath = "./mysql/all";
    //数据库导出文件文件名
    private String fileName;
    //要导出的数据库名
    private String databaseName = "microblog";

    /**
     *功能描述 
     * @author lgj
     * @Description    备份操作
     * @date 3/30/19
     * @param: 
     * @return: 
     *
    */
    public  boolean exportDatabaseTool() throws InterruptedException {


        File saveFile = new File(savePath);
        if (!saveFile.exists()) {// 如果目录不存在
            saveFile.mkdirs();// 创建文件夹
        }
        if(!savePath.endsWith(File.separator)){
            savePath = savePath + File.separator;
        }

        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        fileName =  getFileName();
        log.debug("save path = " + savePath + fileName);
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));
            Process process = Runtime.getRuntime().exec(" mysqldump -h" + hostIP + " -u" + userName + " -p" + password + " --set-charset=UTF8 " + databaseName);

            InputStream in = process.getInputStream();
            byte[] result = new byte[50];

            int len  = 0;
            while ((len = in.read(result,0,result.length)) != -1){
                System.out.println(new String(result));
            }
            if(process.waitFor() == 0){//0 表示线程正常终止。
                return true;
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 执行备份
     */
    public void backup() {

        try {
            if(exportDatabaseTool())
            {
                log.debug("数据库备份成功");
            }
            else {
                log.debug("数据库备份失败");
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            log.debug("数据库备份失败");
            e.printStackTrace();
        }
    }
    /**
     * 根据当前时间获取备份文件
     * @return
     */
    public String getFileName() {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String  date = format.format(new  Date());
        String fileName = "microblog-sql-all-" + date  + ".sql";

        return fileName;
    }


    public static void main(String args[]){
        MysqlBackupUtil mysqlBackupUtil = new MysqlBackupUtil();
        mysqlBackupUtil.backup();
    }

}

