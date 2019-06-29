package com.microblog.blog.web.controller;

import com.alibaba.fastjson.JSON;
import com.microblog.blog.dao.model.BlogComment;
import com.microblog.blog.web.MicroblogBlogApplication;
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

import java.util.Date;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = MicroblogBlogApplication.class)
@AutoConfigureMockMvc
public class BlogCommentControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    private String basePath = "/blog/comment";
    @Before
    public void setupMockMvc() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void queryOneLevelComment() throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get(basePath+"/query/1")
                .requestAttr("userId",3L)
                .param("blogId","37")
                .param("pageStart","1")
                .param("pageCount","50"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        log.info("执行结果:signature status=[{}]，result = [{}] ",
                result.getResponse().getStatus(),
                result.getResponse().getContentAsString());


    }

    @Test
    public void queryTwoLevelComment() throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get(basePath+"/query/2")
                .requestAttr("userId",3L)
                .param("blogId","37")
                .param("pId","1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        log.info("执行结果:signature status=[{}]，result = [{}] ",
                result.getResponse().getStatus(),
                result.getResponse().getContentAsString());
    }

    @Test
    public void create() throws Exception{

        BlogComment comment = new BlogComment();
        comment.setContent("哈哈是");
        comment.setBlogId(3L);
        comment.setPublishTime(new Date());
        comment.setUserId(3L);
        comment.setPid(0L);
        comment.setReplyId(0L);

        String reqData = JSON.toJSONString(comment);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post(basePath)
                .requestAttr("userId",3L).contentType(MediaType.APPLICATION_JSON_UTF8).content(reqData)
                //.param("comment",reqData)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


        log.info("执行结果:signature status=[{}]，result = [{}] ",
                result.getResponse().getStatus(),
                result.getResponse().getContentAsString());
    }

    @Test
    public void delete() throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .delete(basePath)
                .requestAttr("userId",3L)
                .param("commentId","1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        log.info("执行结果:signature status=[{}]，result = [{}] ",
                result.getResponse().getStatus(),
                result.getResponse().getContentAsString());


    }
}