package com.cloud.microblog.blog.service.impl;

import com.cloud.microblog.blog.dao.mapper.*;
import com.cloud.microblog.blog.dao.model.*;
import com.cloud.microblog.blog.service.BlogService;
import com.cloud.microblog.blog.service.dto.BlogInfoDto;
import com.cloud.microblog.blog.service.type.BlogType;
import com.cloud.microblog.blog.service.utils.UserUtil;
import com.cloud.microblog.common.code.BlogReturnCode;
import com.cloud.microblog.common.code.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Slf4j
@Service
public class IBlogService implements BlogService {

    @Autowired
    HttpServletRequest  request;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    BlogImgMapper blogImgMapper;

    @Autowired
    BlogCollectMapper blogCollectMapper;

    @Autowired
    BlogLikeMapper blogLikeMapper;

    @Autowired
    BlogRepostMapper blogRepostMapper;

    @Autowired
    BlogCommentMapper blogCommentMapper;


    @Override
    public List<BlogInfoDto> queryBlog(String type, int start , int limit) {
        List<BlogInfoDto>  blogInfoDtos = new LinkedList<>();

        if(BlogType.ALL.name().equals(type)){
            for(long i = start ; i < (start + limit) ; i++){
                BlogInfoDto blogInfoDto = BlogInfoDto.build()
                                            .blogId(i)
                                            .collectNum(new Random().nextInt(100))
                                            .likeNum(new Random().nextInt(100))
                                            .repostNum(new Random().nextInt(100))
                                            .commentNum(new Random().nextInt(100))
                                            .nickName("星星-"+i).headerUrl("/img/header/"  +  new Random().nextInt(7)+ ".jpg")
                                            .content("<span style='color:blue;'>#深圳#</span>" + "  今天天气真好阿");

                blogInfoDtos.add(blogInfoDto);
            }

        }

        return blogInfoDtos;
    }

    /**
     *功能描述
     * @author lgj
     * @Description  博客提交
     * @date 2/22/19
     * @param:
     * @return:
     *
    */
    @Override
    public ReturnCode submit(MultipartHttpServletRequest multiRequest) {
        List<MultipartFile> fileList = new ArrayList<>();

        //博客内容
        String blogText = multiRequest.getParameter("blog-text");
        //博客类型
        String blogType = multiRequest.getParameter("blog-type");

      //  User  user = (User)SessionUtils.get(UserSessionKeyUtil.CURRENT_LOGIN_USER_KEY);

        //写入数据库
        Blog blog = new Blog();
        blog.setContent(blogText);
      //  blog.setCreateTime(new Date());
       // blog.setPublishTime(new Date());
       // blog.setType(blogType);
        blog.setIsOriginal("true");
        blog.setUserId(UserUtil.getUserId(request));
        long blogId = blogMapper.insert(blog);



        //取得request中的所有文件名
        fileList = multiRequest.getFiles("blog-img");
        if (fileList == null || fileList.size() <= 0) {

           return BlogReturnCode.BLOG_SUBMIT_SUCCESS;
        }

        List<BlogImg> blogImgs = new LinkedList<BlogImg>();
        //处理图片

        byte  fileCount = 0;
        for(MultipartFile file:fileList) {

            //最多9张
            if(fileCount++ > 9){
                break;
            }

            log.debug("{}--{}", file.getName(), file.getOriginalFilename());

            String staticPath = "/nginx/microblog/static";
            String imgPath = "/img/blog/" + UserUtil.getUserId(request);
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            log.debug("OriginalFilename = {},suffix={}",originalFilename,suffix );


            //创建文件夹
            File saveFile = new File(staticPath+imgPath);
            if (!saveFile.exists()) {
                log.debug("path:{} not exists,create dir",staticPath+imgPath);
                saveFile.mkdir();
                log.debug("saveFile.exists? {}", saveFile.exists());
            }

            String subPath = imgPath + "/" + getRandomFileName() + suffix;
            String savePath = staticPath + subPath;

            //数据保存

            BlogImg blogImg = new BlogImg();
            blogImg.setBlogId(blogId);
            blogImg.setImgUrl(subPath);
            blogImgs.add(blogImg);


            try{
                File newfile = new File(savePath);
                file.transferTo(newfile);
            }
            catch(Exception ex){
                ex.printStackTrace();
                return  BlogReturnCode.BLOG_SUBMIT_FAIL;
            }
        }


        return  BlogReturnCode.BLOG_SUBMIT_SUCCESS;



    }

    @Override
    public ReturnCode collect(long blogId) {

 
        BlogCollect blogCollect = new BlogCollect();
        blogCollect.setBlogId(blogId);
        blogCollect.setUserId(UserUtil.getUserId(request));
        blogCollect.setCreateTime(new Date());
        blogCollectMapper.insert(blogCollect);


        return BlogReturnCode.BLOG_COLLECT_SUCCESS;
    }

    @Override
    public ReturnCode repost(long blogId,String content) {

        BlogRepost blogRepost = new BlogRepost();
        blogRepost.setBlogId(blogId);
        blogRepost.setUserId(UserUtil.getUserId(request));
        blogRepost.setCreateTime(new Date());
        blogRepost.setContent(content);
        blogRepostMapper.insert(blogRepost);


        return BlogReturnCode.BLOG_COLLECT_SUCCESS;
    }

    @Override
    public ReturnCode comment(long blogId,String content) {

        BlogComment blogComment = new BlogComment();
        blogComment.setBlogId(blogId);
        blogComment.setUserId(UserUtil.getUserId(request));
        blogComment.setContent(content);
        blogComment.setPublishTime(new Date());
        blogCommentMapper.insert(blogComment);


        return BlogReturnCode.BLOG_COLLECT_SUCCESS;
    }

    @Override
    public ReturnCode like(long blogId) {

        BlogLike blogLike = new BlogLike();
        blogLike.setBlogId(blogId);
        blogLike.setUserId(UserUtil.getUserId(request));
        blogLike.setCreateTime(new Date());
        blogLikeMapper.insert(blogLike);


        return BlogReturnCode.BLOG_COLLECT_SUCCESS;
    }

    private String  getRandomFileName(){
        String random = String.valueOf(new Random().nextInt(10000));
        String timestamp = String.valueOf(new Date().getTime());

        return  random+"-"+timestamp;

    }



}
