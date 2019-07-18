package com.microblog.comment.web.controller;

import com.alibaba.fastjson.JSON;
import com.microblog.comment.web.MicroblogCommentWebApplication;
import com.microblog.commment.dao.model.BlogComment;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = MicroblogCommentWebApplication.class)
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @Before
    public void setupMockMvc() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void getComments() throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/comment/list")
                //.requestAttr("userId",3L)
                .param("blogId","1")
                .param("page","0")
                .param("pageCount","10")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        log.info("执行结果:signature status=[{}]，result = [{}] ",
                result.getResponse().getStatus(),
                result.getResponse().getContentAsString());

    }

    @Test
    public void createComment() throws Exception{

        List<BlogComment> comments =  comments();

        for(BlogComment comment:comments){

            String requestBody = JSON.toJSONString(comment);
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                    .post("/comment/create").content(requestBody).contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(MockMvcResultMatchers.status().isOk()
                    )
                    .andReturn();
            log.info("执行结果:signature status=[{}]，result = [{}] ",
                    result.getResponse().getStatus(),
                    result.getResponse().getContentAsString());
        }

    }


    private List<BlogComment> comments(){

        List<BlogComment> comments = new ArrayList<>();


        //1
        BlogComment comment1 = new BlogComment();
        comment1.setBlogId(1L);
        comment1.setReplyId(0L);
        comment1.setUserId(1L);
        comment1.setContent("真好笑！");
        comments.add(comment1);
        //1-1
        BlogComment comment11 = new BlogComment();
        comment11.setBlogId(1L);
        comment11.setReplyId(1L);
        comment11.setUserId(1L);
        comment11.setContent("真好笑+1！");
        comments.add(comment11);
        //1-2
        BlogComment comment12 = new BlogComment();
        comment12.setBlogId(1L);
        comment12.setReplyId(1L);
        comment12.setUserId(1L);
        comment12.setContent("真好笑+2！");
        comments.add(comment12);

        //2
        BlogComment comment2 = new BlogComment();
        comment2.setBlogId(1L);
        comment2.setReplyId(0L);
        comment2.setUserId(1L);
        comment2.setContent("很有趣！");
        comments.add(comment2);
        //2-1
        BlogComment comment21 = new BlogComment();
        comment21.setBlogId(1L);
        comment21.setReplyId(4L);
        comment21.setUserId(1L);
        comment21.setContent("很有趣+1！");
        comments.add(comment21);
        //2-2
        BlogComment comment22 = new BlogComment();
        comment22.setBlogId(1L);
        comment22.setReplyId(4L);
        comment22.setUserId(1L);
        comment22.setContent("很有趣+2！");
        comments.add(comment22);





        return comments;

    }
}