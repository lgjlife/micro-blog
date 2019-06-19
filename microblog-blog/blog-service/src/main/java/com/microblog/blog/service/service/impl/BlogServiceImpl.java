package com.microblog.blog.service.service.impl;

import com.microblog.blog.dao.mapper.*;
import com.microblog.blog.dao.model.*;
import com.microblog.blog.service.dto.BlogInfoDto;
import com.microblog.blog.service.feign.UserFeignService;
import com.microblog.blog.service.service.BlogService;
import com.microblog.blog.service.utils.ImgMarkUtil;
import com.microblog.blog.service.utils.RedisKeyUtils;
import com.microblog.blog.service.utils.UserUtil;
import com.microblog.blog.service.utils.fastdfs.FastdfsUtil;
import com.microblog.common.code.BlogReturnCode;
import com.microblog.common.code.ReturnCode;
import com.microblog.filesystem.provider.FSProvider;
import com.microblog.filesystem.upload.UpLoadObject;
import com.microblog.filesystem.upload.UpLoadObjectBuilder;
import com.microblog.user.dao.model.User;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.*;


/**
 *功能描述
 * @author lgj
 * @Description 微博服务层
 * @date 4/7/19
*/
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedissonClient redissonClient;

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


    @Autowired
    UserFeignService userFeignService;

    @Autowired
    FastdfsUtil fastdfsUtil;

    @Autowired
    FSProvider fastdfsClient;


    /**
     *功能描述
     * @author lgj
     * @Description  查询 微博
     *             type： @See BlogType
     *             userId: user id
     *             page: 分页参数  第几页 1--n
     *             count: 每页数据
     *
     *             1.查询个人公开微博
     *               queryBlog("ALL",1,0,10)
     *             2.查询个人私有微博
     *               queryBlog("PRIVATE",1,0,10)
     *             3.查询所有关注者微博
      *               queryBlog(null,1,0,10)
      *            2.查询任何个人有微博
      *               queryBlog(null,1,0,10)
     * @date 4/4/19
     * @param:
     * @return:
     *
    */
    @Override
    public List<BlogInfoDto> queryBlog(String type,long userId, int page , int count) {
        List<BlogInfoDto>  blogInfoDtos = new LinkedList<>();

        log.debug("type ={},userId = {},page={},count={}",type,userId,page,count);
        List<Blog> blogs = blogMapper.selectByTypeAndUserId(type,userId,(page-1)*count,count);
        log.debug("blogs = " + blogs);
        blogs.forEach((blog)->{
            long blogId = blog.getBlogId();
            List<BlogImg> blogImgs = blogImgMapper.selectBlogId(blogId);
            List<String> imgs =  new ArrayList<>();
            blogImgs.forEach((img)->{
                imgs.add(img.getImgUrl());
            });
            long collectCount =  blogCollectMapper.selectCountByBlogId(blogId);
            long likeCount =   blogLikeMapper.selectCountByBlogId(blogId);
            long repostCount =   blogRepostMapper.selectCountByBlogId(blogId);
            long commentCount =   blogCommentMapper.selectCountByBlogId(blogId);

            User user = userFeignService.queryUserInfo(blog.getUserId());
            BlogInfoDto dto = null;
            if(user != null){
                 dto = BlogInfoDto.builder()
                        .blogId(blogId)
                        .blogImg(imgs)
                        .headerUrl(user.getHeaderUrl())
                        .nickName(user.getNickName())
                        .content(blog.getContent())
                        .collectNum(collectCount)
                        .likeNum(likeCount)
                        .repostNum(repostCount)
                        .commentNum(commentCount)
                        .build();
                blogInfoDtos.add(dto);
                log.debug("blogInfoDtos = " + blogInfoDtos);
            }


        });

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
        blog.setType(blogType);
        blog.setIsOriginal("true");
        blog.setUserId(UserUtil.getUserId(request));
        long blogId = blogMapper.insert(blog);

        log.debug("blogId = {}",blogId);


        //取得request中的所有文件名
        fileList = multiRequest.getFiles("blog-img");
        if (fileList == null || fileList.size() <= 0) {
           return BlogReturnCode.BLOG_SUBMIT_SUCCESS;
        }
        List<BlogImg> blogImgs = new LinkedList<BlogImg>();
        Map<String,MultipartFile> savePaths =  new HashMap<String,MultipartFile>();
        //处理图片

        byte  fileCount = 0;
        for(MultipartFile file:fileList) {

            //最多9张
            try{
                //添加水印
                OutputStream os = new ByteArrayOutputStream();
                ((ByteArrayOutputStream) os).size();
                ImgMarkUtil.markImageByString(file.getInputStream(),os,"@"+UserUtil.getNickName(request),"jpg");

                //upload to fastdfs
                UpLoadObject upLoadObject = new UpLoadObjectBuilder().name( file.getOriginalFilename())
                        .size(((ByteArrayOutputStream) os).size())
                        .inputStream(new ByteArrayInputStream(((ByteArrayOutputStream) os).toByteArray()))
                        .metaDate(new HashMap<>()).build();
                String dfsImgPath =  fastdfsClient.upLoad(upLoadObject);

                //数据保存
                BlogImg blogImg = new BlogImg();
                blogImg.setBlogId(blog.getBlogId());
                blogImg.setImgUrl(dfsImgPath);
                blogImgs.add(blogImg);
            }
            catch(Exception ex){
                log.error(ex.getMessage());
            }
            if((fileCount++) > 9){
                break;
            }
        }
        blogImgMapper.insertList(blogImgs);
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

    /**
     *功能描述
     * @author lgj
     * @Description 点赞操作
     * @date 5/14/19
     * @param:
     * @return:
     *
    */
    @Override
    public ReturnCode like(long blogId) {

        BlogLike blogLike = new BlogLike();
        blogLike.setBlogId(blogId);
        blogLike.setUserId(UserUtil.getUserId(request));
        blogLike.setCreateTime(new Date());
        redisTemplate.opsForValue().increment(RedisKeyUtils.getBlogLikeCount(blogId,new Date().getDate()));


        //blogLikeMapper.insert(blogLike);


        return BlogReturnCode.BLOG_COLLECT_SUCCESS;
    }

    /**
     *功能描述
     * @author lgj
     * @Description 取消点赞操作
     * @date 5/14/19
     * @param:
     * @return:
     *
    */
    @Override
    public ReturnCode unLike(long blogId) {
        return null;
    }

    private String  getRandomFileName(){
        String random = String.valueOf(new Random().nextInt(10000));
        String timestamp = String.valueOf(new Date().getTime());

        return  random+"-"+timestamp;

    }




}
