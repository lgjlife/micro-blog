package com.microblog.blog.service.service.impl;

import com.microblog.blog.dao.mapper.BlogCommentMapper;
import com.microblog.blog.dao.mapper.CommentLikeMapper;
import com.microblog.blog.dao.model.BlogComment;
import com.microblog.blog.service.dto.CommentDto;
import com.microblog.blog.service.feign.UserFeignService;
import com.microblog.blog.service.service.CommentService;
import com.microblog.user.dao.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private BlogCommentMapper commentMapper;

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private CommentLikeMapper commentLikeMapper;


    @Override
    public void create(BlogComment comment) {
        Integer result = commentMapper.insert(comment);

        log.info("创建结果:"+result);
    }

    @Override
    public List<BlogComment> get() {

        return  commentMapper.selectAll();
    }

    /**
     *功能描述 
     * @author lgj
     * @Description  
     * @date 7/18/19
     * @param: blogId： 微博ID， page：第几页，pageCount每页的数目
     * @return:  
     *
    */
    @Override
    public List<CommentDto>  getComments(long blogId, long userId,int page, int pageCount){

        List<BlogComment>  parentComments = commentMapper.selectParent( blogId, page, pageCount);
        log.info("parentComments = " + parentComments);
        if(parentComments == null){
            return new ArrayList<CommentDto>();
        }

        List<Long> parentIds = new ArrayList<>();

        List<CommentDto> parentCommentDtos = new ArrayList<>();

        for(BlogComment comment:parentComments){
            if(comment.getReplyId() == 0){
                parentIds.add(comment.getId());
            }

            User user = userFeignService.queryUserInfo(comment.getUserId());

            Long likeCount = commentLikeMapper.selectCountByCommentId(comment.getId());
            if(likeCount == null){
                likeCount = 0L;
            }
            Long userLike = commentLikeMapper.selectCount(comment.getId(),userId);
            boolean isLike = (userLike == 1?true:false);


            CommentDto commentDto =  CommentDto
                    .builder()
                    .cid(comment.getId())
                    .blogId(comment.getBlogId())
                    .userId(comment.getUserId())
                    .nickName(user.getNickName())
                    .headerUrl(user.getHeaderUrl())
                    .replyId(comment.getReplyId())
                    .pid(comment.getPid())
                    .likeNum(likeCount)
                    .likeFlag(isLike)
                    .content(comment.getContent())
                    .ctime(comment.getPublishTime())
                    .build();

            parentCommentDtos.add(commentDto);
        }

        log.debug("parentIds = " + parentIds);

        if(parentIds.size() != 0){
            //有一级评论有子评论
            //查询它的二级评论
            List<BlogComment> childComments = commentMapper.selectChild(parentIds);
            log.debug("childComments[{}] -childComments=[{}]",parentIds,childComments);
            for(CommentDto parentCommentDto:parentCommentDtos){
                List<CommentDto> child = new ArrayList<>();

                for(BlogComment childComment:childComments){
                    if(childComment.getPid() == parentCommentDto.getCid()){

                        User user = userFeignService.queryUserInfo(childComment.getUserId());

                        Long likeCount = commentLikeMapper.selectCountByCommentId(childComment.getId());
                        if(likeCount == null){
                            likeCount = 0L;
                        }
                        Long userLike = commentLikeMapper.selectCount(childComment.getId(),userId);
                        boolean isLike = (userLike == 1?true:false);

                        CommentDto childCommentDto =  CommentDto
                                .builder()
                                .cid(childComment.getId())
                                .blogId(childComment.getBlogId())
                                .userId(childComment.getUserId())
                                .nickName(user.getNickName())
                                .headerUrl(user.getHeaderUrl())
                                .pid(childComment.getPid())
                                .likeNum(likeCount)
                                .likeFlag(isLike)
                                .replyId(childComment.getReplyId())
                                .content(childComment.getContent())
                                .ctime(childComment.getPublishTime())
                                .build();

                        //设置被回复评论的用户ID和用户昵称
                        if(childComment.getReplyId() != 0){
                            List<BlogComment> filterComment = childComments.stream().filter( (comment)->{
                                return childComment.getReplyId() == comment.getId();
                            }).collect(Collectors.toList());
                            if((filterComment!=null) && (!filterComment.isEmpty())){
                                childCommentDto.setReplyUserId(filterComment.get(0).getUserId());
                                //todo 需要优化
                                User user1 = userFeignService.queryUserInfo(filterComment.get(0).getUserId());
                                childCommentDto.setReplyUserNickName(user1.getNickName());
                            }

                        }

                        child.add(childCommentDto);
                    }
                }

                parentCommentDto.setChild(child);


            }
        }


        return parentCommentDtos;
    }

    /**
     *功能描述
     * @author lgj
     * @Description  创建评论
     * @date 7/18/19
     * @param:
     * @return:
     *
    */
    @Override
    public Integer createComment(BlogComment comment){

        comment.setPublishTime(new Date());
        log.info("写入的数据:"+comment);
        Integer result = commentMapper.insert(comment);

        return result;
    }


    @Override
    public Integer deleteComment(Long commentId) {
        return commentMapper.deleteComment(commentId);
    }
}


