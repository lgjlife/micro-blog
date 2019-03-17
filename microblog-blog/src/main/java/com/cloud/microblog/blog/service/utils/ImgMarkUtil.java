package com.cloud.microblog.blog.service.utils;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


/**
 *功能描述
 * @author lgj
 * @Description  图片添加水印工具类
 * @date 3/17/19
*/
@Slf4j
public class ImgMarkUtil {



    /**
     * @param args
     */
    public static void main(String[] args) {

        String srcImgPath = "img/index.png";
        String iconPath = "img/login.png";
        String targerPath = "img/target.png" ;
        // 给图片添加水印
        long start = System.currentTimeMillis();
        markImageByIcon(iconPath,srcImgPath,"img/target1.png");
        ImgMarkUtil.markImageByString(srcImgPath, targerPath,"@小王子");
        long end = System.currentTimeMillis();
        log.debug("time = " + (end-start));


    }
    /**
     * 给图片添加水印
     * @param iconPath 水印图片路径
     * @param srcImgPath 源图片路径
     * @param targerPath 目标图片路径
     */
    public static void markImageByIcon(String iconPath, String srcImgPath,
                                       String targerPath) {
        markImageByIcon(iconPath, srcImgPath, targerPath, null) ;
    }
    /**
     * 给图片添加水印、可设置水印图片旋转角度
     * @param iconPath 水印图片路径
     * @param srcImgPath 源图片路径
     * @param targerPath 目标图片路径
     * @param degree 水印图片旋转角度
     */
    public static void markImageByIcon(String iconPath, String srcImgPath,
                                       String targerPath, Integer degree) {
        OutputStream os = null;
        try {
            Image srcImg = ImageIO.read(new File(srcImgPath));
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
            // 得到画笔对象
            // Graphics g= buffImg.getGraphics();
            Graphics2D g = buffImg.createGraphics();

            // 设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg
                    .getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);

            if (null != degree) {
                // 设置水印旋转
                g.rotate(Math.toRadians(degree),
                        (double) buffImg.getWidth() / 2, (double) buffImg
                                .getHeight() / 2);
            }
            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
            ImageIcon imgIcon = new ImageIcon(iconPath);
            // 得到Image对象。
            Image img = imgIcon.getImage();
            float alpha = 0.1f; // 透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            // 表示水印图片的位置
            g.drawImage(img, 150, 300, null);

            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            g.dispose();
            os = new FileOutputStream(targerPath);
            // 生成图片
            ImageIO.write(buffImg, "PNG", os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *功能描述
     * @author lgj
     * @Description    给图片添加文字水印
     * @date 3/17/19
     * @param:
     * @return:
     *
    */
    public static void markImageByString(String srcImgPath,String targerPath,String text){

        try{

            File srcfile = new File(srcImgPath);

            //获取图片格式（后缀名）
            int index = srcImgPath.lastIndexOf(".");
            String format =  srcImgPath.substring(index+1,srcImgPath.length());
          //  log.debug("format = " + format);

           // log.debug("img name = {}",srcfile.getName());
            Image srcImg = ImageIO.read(srcfile);

            int width = srcImg.getWidth(null);
            int height = srcImg.getHeight(null);

         //   log.debug("width = {} ,height = {}",width,height);
            BufferedImage bufferedImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = (Graphics2D) bufferedImage.createGraphics();

            //写入原图
            graphics2D.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg
                    .getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);

            //字体设置
            graphics2D.setColor(Color.BLACK);
            graphics2D.setFont(new Font("宋体",Font.BOLD,14));
            //位置自适应，图片右下角
            graphics2D.drawString(text,width - 9*getStrLength(text),height-20);

            ImageIO.write(bufferedImage, format, new FileOutputStream(targerPath));
        }
        catch(Exception ex){
            log.error(ex.getMessage());
        }

    }

    /**
     *功能描述
     * @author lgj
     * @Description  获取字符串的长度   aaa -> 3 ,"啊啊啊" -> 6
     * @date 3/17/19
     * @param:
     * @return:
     *
    */
    public  static int getStrLength(String str){

        int len = 0;

       // str.getBytes();
        char[] strs =  str.toCharArray();
        for(char s:strs){
            if(s < 0xFF){
                len++;
            }
            else {
                len += 2;
            }
        }
        return len;
    }





}
