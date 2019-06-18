package com.microblog.filesystem.controller;


import com.microblog.filesystem.provider.FSProvider;
import com.microblog.filesystem.upload.UpLoadObject;
import com.microblog.filesystem.upload.UpLoadObjectBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

@Slf4j
//@RestController
//@RequestMapping("/fdfs")
public class FdfsController {

    @Autowired
    FSProvider fastdfsClient;


    //@GetMapping("/upload")
    public String upLoad() throws Exception {

        log.info("/fdfs/upload");
        File file = new File("pic/timg.jpg");
        log.info("path = " + file.getAbsolutePath());
        InputStream ins = new FileInputStream(file);

        UpLoadObject upLoadObject = new UpLoadObjectBuilder().name(file.getName())
                .path(file.getAbsolutePath()).size(ins.available()).inputStream(ins)
                .metaDate(new HashMap<>()).build();

        String path = fastdfsClient.upLoad(upLoadObject);

        log.info("保存的地址:"+ path);

        return path;

    }
}
