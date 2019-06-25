package com.microblog.user.service.utils;

import org.springframework.util.FileCopyUtils;

import java.io.File;

/**
 *功能描述
 * @author lgj
 * @Description  重命名文件
 *
 * @date 4/6/19
*/
public class HearderFileUtil {

    public static void main(String args[]){

        String rootPath = "pic/hearder/";
        File file  = new File(rootPath);

        System.out.println(file.getAbsolutePath());
        File[] files = file.listFiles();

        int i = 0;

        for(File f:files)
        {

            System.out.println(f.getName());
            String name = f.getName();

            File source = new File(rootPath+name);
            int index = name.lastIndexOf(".");
            String imgType = name.substring(index);
            System.out.println(imgType);

            File dest = new File(rootPath+i+imgType);


            try{
                FileCopyUtils.copy(source,dest);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }

            i++;
            f.delete();


        }

    }
}
