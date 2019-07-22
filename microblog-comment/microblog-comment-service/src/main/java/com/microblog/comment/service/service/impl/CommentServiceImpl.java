package com.microblog.comment.service.service.impl;

import com.microblog.comment.service.dto.CommentDto;
import com.microblog.comment.service.feign.UserFeignService;
import com.microblog.comment.service.service.CommentService;
import com.microblog.commment.dao.mapper.BlogCommentMapper;
import com.microblog.commment.dao.model.BlogComment;
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
    private  BlogCommentMapper commentMapper;

    @Autowired
    private UserFeignService userFeignService;

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
    public List<CommentDto>  getComments(long blogId,int page,int pageCount){

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
            CommentDto commentDto =  CommentDto
                    .builder()
                    .cid(comment.getId())
                    .blogId(comment.getBlogId())
                    .userId(comment.getUserId())
                    .nickName(user.getNickName())
                    .headerUrl(user.getHeaderUrl())
                    .replyId(comment.getReplyId())
                    .content(comment.getContent())
                    .ctime(comment.getPublishTime())
                    .build();

            parentCommentDtos.add(commentDto);
        }

        log.info("parentIds = " + parentIds);

        if(parentIds.size() != 0){
            //有一级评论有子评论
            //查询它的二级评论
            List<BlogComment> childComments = commentMapper.selectChild(parentIds);

            for(CommentDto parentCommentDto:parentCommentDtos){
                List<CommentDto> child = new ArrayList<>();

                for(BlogComment childComment:childComments){
                    if(childComment.getReplyId() == parentCommentDto.getCid()){

                        User user = userFeignService.queryUserInfo(childComment.getUserId());
                        CommentDto childCommentDto =  CommentDto
                                .builder()
                                .cid(childComment.getId())
                                .blogId(childComment.getBlogId())
                                .userId(childComment.getUserId())
                                .nickName(user.getNickName())
                                .headerUrl(user.getHeaderUrl())
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
                                childCommentDto.setReplyUserNickName(filterComment.get(0).getUserId());
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


