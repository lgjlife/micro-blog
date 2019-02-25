package com.cloud.microblog.gateway.service.blog;

import com.cloud.microblog.common.code.BlogReturnCode;
import com.cloud.microblog.common.code.ReturnCode;
import com.cloud.microblog.common.utils.SessionUtils;
import com.cloud.microblog.gateway.dao.mapper.*;
import com.cloud.microblog.gateway.dao.model.*;
import com.cloud.microblog.gateway.dto.BlogInfoDto;
import com.cloud.microblog.gateway.service.blog.service.BlogService;
import com.cloud.microblog.gateway.service.blog.type.BlogType;
import com.cloud.microblog.gateway.service.utils.UserSessionKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.*;

@Slf4j
@Service
public class IBlogService implements BlogService {


    @Autowired
    BlogMapper blogMapper;

    @Autowired
    BlogImgMapper blogImgMapper;

    @Autowired
    BlogCollectMapper  blogCollectMapper;

    @Autowired
    BlogLikeMapper blogLikeMapper;

    @Autowired
    BlogRepostMapper blogRepostMapper;

    @Autowired
    BlogCommentMapper blogCommentMapper;


    @Override
    public List<BlogInfoDto> queryBlog(String type,int start , int limit) {
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

        User  user = (User)SessionUtils.get(UserSessionKeyUtil.CURRENT_LOGIN_USER_KEY);
        //写入数据库
        Blog blog = new Blog();
        blog.setContent(blogText);
        blog.setCreateTime(new Date());
        blog.setPublishTime(new Date());
        blog.setType(blogType);
        blog.setIsOriginal("true");
        blog.setUserId(user.getUserId());
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
            String imgPath = "/img/blog/" + user.getUserId();
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

        blogImgMapper.insert(blogImgs);

        return  BlogReturnCode.BLOG_SUBMIT_SUCCESS;



    }

    @Override
    public ReturnCode collect(long blogId) {

        User user = (User) SessionUtils.get(UserSessionKeyUtil.CURRENT_LOGIN_USER_KEY);
        BlogCollect blogCollect = new BlogCollect();
        blogCollect.setBlogId(blogId);
        blogCollect.setUserId(user.getUserId());
        blogCollect.setCreateTime(new Date());
        blogCollectMapper.insert(blogCollect);


        return BlogReturnCode.BLOG_COLLECT_SUCCESS;
    }

    @Override
    public ReturnCode repost(long blogId,String content) {
        User user = (User) SessionUtils.get(UserSessionKeyUtil.CURRENT_LOGIN_USER_KEY);
        BlogRepost blogRepost = new BlogRepost();
        blogRepost.setBlogId(blogId);
        blogRepost.setUserId(user.getUserId());
        blogRepost.setCreateTime(new Date());
        blogRepost.setContent(content);
        blogRepostMapper.insert(blogRepost);


        return BlogReturnCode.BLOG_COLLECT_SUCCESS;
    }

    @Override
    public ReturnCode comment(long blogId,String content) {
        User user = (User) SessionUtils.get(UserSessionKeyUtil.CURRENT_LOGIN_USER_KEY);
        BlogComment blogComment = new BlogComment();
        blogComment.setBlogId(blogId);
        blogComment.setUserId(user.getUserId());
        blogComment.setContent(content);
        blogComment.setPublishTime(new Date());
        blogCommentMapper.insert(blogComment);


        return BlogReturnCode.BLOG_COLLECT_SUCCESS;
    }

    @Override
    public ReturnCode like(long blogId) {
        User user = (User) SessionUtils.get(UserSessionKeyUtil.CURRENT_LOGIN_USER_KEY);
        BlogLike blogLike = new BlogLike();
        blogLike.setBlogId(blogId);
        blogLike.setUserId(user.getUserId());
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
