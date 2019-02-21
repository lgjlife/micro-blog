package com.cloud.microblog.gateway.service.blog;

import com.cloud.microblog.gateway.dto.BlogInfoDto;
import com.cloud.microblog.gateway.service.blog.service.BlogService;
import com.cloud.microblog.gateway.service.blog.type.BlogType;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class IBlogService implements BlogService {


    @Override
    public List<BlogInfoDto> queryBlog(String type,int start , int end) {
        List<BlogInfoDto>  blogInfoDtos = new LinkedList<>();

        if(BlogType.ALL.name().equals(type)){
            for(long i = 0 ; i < 10 ; i++){
                BlogInfoDto blogInfoDto = BlogInfoDto.build()
                                            .blogId(i)
                                            .collectNum(new Random().nextInt(100))
                                            .likeNum(new Random().nextInt(100))
                                            .repostNum(new Random().nextInt(100))
                                            .commentNum(new Random().nextInt(100))
                                            .nickName("星星-"+i).headerUrl("/img/header/4891-1550724910758.png")
                                            .content("<span style='color:blue;'>#深圳#</span>" + "  今天天气真好阿");

                blogInfoDtos.add(blogInfoDto);
            }

        }

        return blogInfoDtos;
    }
}
