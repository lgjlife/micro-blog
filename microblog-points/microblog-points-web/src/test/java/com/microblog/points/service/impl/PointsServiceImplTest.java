package com.microblog.points.service.impl;

import com.microblog.common.result.BaseResult;
import com.microblog.points.dao.mapper.PointsMapper;
import com.microblog.points.dao.model.Points;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class PointsServiceImplTest {

    @Mock
    PointsMapper pointsMapper;

    @InjectMocks
    PointsServiceImpl pointsService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void handlePoints() {

        Long userId = 3L;

        Points points = new Points();
        points.setUserId(3L);
        points.setPoints(12l);
        points.setPointsId(1L);

        Mockito.when(pointsMapper.selectByUserId(userId)).thenReturn(points);
        BaseResult result = pointsService.handlePoints(userId,10);
        log.info("result = "+result.toString());


    }

    @Test
    public void queryPoints() {

        Long userId = 3L;

        Points points = new Points();
        points.setUserId(3L);
        points.setPoints(12l);
        points.setPointsId(1L);

        Mockito.when(pointsMapper.selectByUserId(userId)).thenReturn(points);

        long result =  pointsService.queryPoints(userId);
        log.info("result = "+result);
    }
}