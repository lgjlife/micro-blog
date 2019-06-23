package com.microblog.points.web.controller;

import com.microblog.points.web.MicroblogPointsWebApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;



@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = MicroblogPointsWebApplication.class)
@AutoConfigureMockMvc
public class PointsControllerTest {


    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @Before
    public void setupMockMvc() throws Exception {
        MockitoAnnotations.initMocks(this);

         mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
/*
        mockMvc = MockMvcBuilders
                .standaloneSetup(userDemoController)
                .setRemoveSemicolonContent(false)
                .build();*/

    }


    @Test
    @Transactional
    public void signature() throws Exception{


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/points/signature")
                .requestAttr("userId",3L)
                .param("type","100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        log.info("执行结果:signature status=[{}]，result = [{}] ",
                result.getResponse().getStatus(),
                result.getResponse().getContentAsString());
    }

    @Test
    public void queryPoints()throws Exception {

        MvcResult result  =  mockMvc.perform(MockMvcRequestBuilders
                .get("/points/query")
                .param("userId","3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

       log.info("执行结果:queryPoints status=[{}] ;result = [{}] ",
               result.getResponse().getStatus(),
               result.getResponse().getContentAsString());

    }
}