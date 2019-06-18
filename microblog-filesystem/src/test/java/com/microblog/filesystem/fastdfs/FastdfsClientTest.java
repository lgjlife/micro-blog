package com.microblog.filesystem.fastdfs;


import com.microblog.filesystem.FilesystemApplication;
import com.microblog.filesystem.provider.FSProvider;
import com.microblog.filesystem.upload.UpLoadObject;
import com.microblog.filesystem.upload.UpLoadObjectBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FilesystemApplication.class)
public class FastdfsClientTest {

    @Autowired
    FSProvider fastdfsClient;


    @Test
    public void upLoad() throws Exception {


        File file = new File("pic/timg.jpg");
        log.info("path = " + file.getAbsolutePath());
        InputStream ins = new FileInputStream(file);

        UpLoadObject upLoadObject = new UpLoadObjectBuilder().name(file.getName())
               .path(file.getAbsolutePath()).size(ins.available()).inputStream(ins)
                .metaDate(new HashMap<>()).build();

        String path = fastdfsClient.upLoad(upLoadObject);

        log.info("保存的地址:"+ path);

    }
}