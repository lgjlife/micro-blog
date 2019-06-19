package com.microblog.blog.service.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

@Slf4j
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ImgMarkUtilTest {

    public static void main(String args[]) throws Exception{

        Options ops = new OptionsBuilder().include(ImgMarkUtilTest.class.getSimpleName())
                .forks(1).build();
        new Runner(ops).run();
    }


    @Test
    //@Benchmark
    public void markImageByString() {
        String srcImgPath = "img/timg.jpeg";
        String iconPath = "img/login.png";
        String targerPath = "img/target.jpeg" ;
        // 给图片添加水印
        //long start = System.currentTimeMillis();
        //ImgMarkUtil.markImageByIcon(iconPath,srcImgPath,"img/target1.png");
        ImgMarkUtil.markImageByString(srcImgPath, targerPath,"@小王子");
        //long end = System.currentTimeMillis();
        //log.debug("time = " + (end-start));
    }

    @Test
    public void markImageByString1() throws Exception{
        String srcImgPath = "img/timg.jpeg";
        String targerPath = "img/target-new.jpeg" ;

        OutputStream os = new FileOutputStream(new File(targerPath));

        ImgMarkUtil.markImageByString(new File(srcImgPath), os,"@苦了阿达","jpeg");

        log.info(" ssss ");
        os.flush();



    }

}